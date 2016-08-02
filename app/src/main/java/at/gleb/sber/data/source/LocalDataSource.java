package at.gleb.sber.data.source;

import android.content.Context;
import android.os.AsyncTask;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import at.gleb.sber.data.Valute;

/**
 * Local cache for all valutes.
 * <p/>
 * Created by gleb on 02.08.16.
 */
public class LocalDataSource {

    private final Context mContext;

    private static final String FILE_NAME = "valutes.txt";

    public LocalDataSource(Context context) {
        this.mContext = context;
    }


    public void getValutesAsync(final ValutesDataSource.LoadValutesCallback callback) {
        AsyncTask<Void, Void, ArrayList<Valute>> task = new AsyncTask<Void, Void, ArrayList<Valute>>() {
            @Override
            protected ArrayList<Valute> doInBackground(Void... voids) {
                return getValutes(mContext);
            }

            @Override
            protected void onPostExecute(ArrayList<Valute> valutes) {
                if (valutes != null) {
                    callback.onValutesLoaded(valutes);
                } else {
                    callback.onDataNotAvailable();
                }
            }
        };

        task.execute();

    }

    public boolean setValutes(ArrayList<Valute> valutes) {
        try {
            if (valutes != null) {
                FileOutputStream fos = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(valutes);
                os.close();
                fos.close();

                return true;
            } else {
                return false;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Valute> getValutes(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            ArrayList<Valute> simpleClass = (ArrayList<Valute>) is.readObject();
            is.close();
            fis.close();

            return simpleClass;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
