package at.gleb.sber.data.source;

import java.util.List;

import at.gleb.sber.data.Valute;

/**
 * Main entry point for accessing valutes data.
 * <p/>
 * Created by gleb on 02.08.16.
 */
public interface ValutesDataSource {


    interface LoadValutesCallback {
        void onValutesLoaded(List<Valute> valutes);

        void onDataNotAvailable();
    }


    void setLoadedValutesCallback(LoadValutesCallback callback);

    void startLoadValutes();

    void setValutes();
}