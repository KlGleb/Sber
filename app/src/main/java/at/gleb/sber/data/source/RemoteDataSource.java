package at.gleb.sber.data.source;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import at.gleb.sber.R;
import at.gleb.sber.data.ValCurs;

/**
 * Сервис, заргужающий данные и записывающий их в кэш
 * <p/>
 * Created by gleb on 02.08.16.
 */
public class RemoteDataSource extends IntentService {

    public static final String TAG = "RemoteDataSource";
    public static final String ACTION_VALUTES_UPDATE = "ActionValutesUpdate";
    public static final String ACTION_VALUTES_ERROR = "ActionValutesError";

    public RemoteDataSource() {
        super("ValutesSourceService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (!(networkInfo != null && networkInfo.isConnected())) {
                // fetch data
                throw new Throwable("Connection error");
            }


            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 500;
            String contentAsString = null;
            try {
                URL url = new URL(getString(R.string.valutes_url));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(5000 /* milliseconds */);
                conn.setConnectTimeout(7000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(TAG, "The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                contentAsString = readIt(is);


                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } catch (Throwable throwable) {
                Log.w(TAG, throwable);
                throw new Throwable("Request failed");

            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Throwable throwable) {
                        Log.w(TAG, throwable);
                    }
                }
            }

            if (contentAsString != null) {
                Reader reader = new StringReader(contentAsString);
                Persister serializer = new Persister();

                ValCurs valutes = serializer.read(ValCurs.class, reader, false);
                if (valutes != null) {
                    LocalDataSource localDataSource = new LocalDataSource(this);

                    boolean b = localDataSource.setValutes(new ArrayList<>(valutes.valutes));

                    if (b) {
                        onReady();
                    } else {
                        throw new Throwable("Cache error occurred");
                    }

                } else {
                    throw new Throwable("valutes is empty");
                }

            } else {
                throw new Throwable("Content is empty");
            }


        } catch (Throwable e) {
            onError();
        }

    }

    private void onError() {
        Intent intent = new Intent(ACTION_VALUTES_ERROR);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void onReady() {
        Intent intent = new Intent(ACTION_VALUTES_UPDATE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    // Reads an InputStream and converts it to a String.
    private String readIt(InputStream stream) throws IOException {
        java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


}
