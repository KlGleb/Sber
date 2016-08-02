package at.gleb.sber.calculator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

import at.gleb.sber.R;
import at.gleb.sber.data.Valute;

/**
 * Main UI for the calculator screen.
 */
public class CalculatorView extends LinearLayout implements CalculatorContract.View {


    private CalculatorContract.Presenter mPresenter;


    public CalculatorView(Context context) {
        super(context);
        init();
    }

    public CalculatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.calculator_view_content, this);
    }

    @Override
    public void setPresenter(CalculatorContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setValutes(List<Valute> valutes) {
        Log.d("MyTag", valutes.toString());
    }
}
