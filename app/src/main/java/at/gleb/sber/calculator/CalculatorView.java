package at.gleb.sber.calculator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import at.gleb.sber.R;

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
}
