package at.gleb.sber.calculator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import at.gleb.sber.R;
import at.gleb.sber.data.Valute;

/**
 * Main UI for the calculator screen.
 */
public class CalculatorView extends LinearLayout implements CalculatorContract.View {


    private CalculatorContract.Presenter mPresenter;
    private Spinner mSpinnerFrom;
    private Spinner mSpinnerTo;
    private EditText mEditText;
    private TextView mResultText;


    public CalculatorView(Context context) {
        super(context);
        init();
    }

    public CalculatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.calculator_view_content, this);

        mSpinnerFrom = (Spinner) findViewById(R.id.spinner_from);
        mSpinnerTo = (Spinner) findViewById(R.id.spinner_to);

        mEditText = (EditText) findViewById(R.id.count_text);
        mResultText = (TextView) findViewById(R.id.result_text);

        view.findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.calculate(mSpinnerFrom.getSelectedItemPosition(), mSpinnerTo.getSelectedItemPosition(),
                        mEditText.getText().toString());

            }
        });
    }

    @Override
    public void setPresenter(CalculatorContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setValutes(List<Valute> valutes) {
        ArrayAdapter<Valute> valuteArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, valutes);

        valuteArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        mSpinnerFrom.setAdapter(valuteArrayAdapter);
        mSpinnerTo.setAdapter(valuteArrayAdapter);

    }

    @Override
    public void showResult(float result, String shortName) {
        mResultText.setText(String.format(Locale.ENGLISH, "%.2f %s", result, shortName));
        mResultText.animate().scaleY(1).start();
    }
}
