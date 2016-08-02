/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.gleb.sber.calculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;

import java.util.List;

import at.gleb.sber.data.Valute;
import at.gleb.sber.data.source.LocalDataSource;
import at.gleb.sber.data.source.RemoteDataSource;
import at.gleb.sber.data.source.ValutesDataSource;

/**
 * Listens to user actions from the UI ({@link CalculatorView}), retrieves the data and updates the
 * UI as required.
 */
public class CalculatorPresenter implements CalculatorContract.Presenter {


    private final CalculatorContract.View mCalculatorView;

    @NonNull
    private final Context mContext;


    public CalculatorPresenter(@NonNull CalculatorContract.View calculatorView,
                               @NonNull Context context) {
        mCalculatorView = calculatorView;
        mContext = context;

        mCalculatorView.setPresenter(this);
    }

    @Override
    public void start() {
        mContext.startService(new Intent(mContext, RemoteDataSource.class));

        LocalBroadcastManager.getInstance(mContext).registerReceiver(
                receiverError,
                new IntentFilter(RemoteDataSource.ACTION_VALUTES_ERROR)
        );

        LocalBroadcastManager.getInstance(mContext).registerReceiver(
                receiverComplete,
                new IntentFilter(RemoteDataSource.ACTION_VALUTES_UPDATE)
        );
    }

    @Override
    public void stop() {
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiverError);
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiverComplete);
    }

    private BroadcastReceiver receiverComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            loadValutesFromLocalStorage();
        }
    };

    private BroadcastReceiver receiverError = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            loadValutesFromLocalStorage();
        }
    };

    private void loadValutesFromLocalStorage() {
        LocalDataSource localDataSource = new LocalDataSource(mContext);
        localDataSource.getValutesAsync(new ValutesDataSource.LoadValutesCallback() {
            @Override
            public void onValutesLoaded(List<Valute> valutes) {
                mCalculatorView.setValutes(valutes);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

}
