package at.gleb.sber.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import at.gleb.sber.R;

public class CalculatorActivity extends AppCompatActivity {


    private CalculatorView mCalculatorView;
    private CalculatorPresenter mCalculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        mCalculatorView = (CalculatorView) findViewById(R.id.calc_view);


//        Injection.provideTasksRepository(getApplicationContext()),
        // Create the presenter
        mCalculatorPresenter = new CalculatorPresenter(mCalculatorView, this);
        mCalculatorPresenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mCalculatorPresenter.stop();
    }
}
