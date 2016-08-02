package at.gleb.sber.calculator;

import java.util.List;

import at.gleb.sber.BasePresenter;
import at.gleb.sber.BaseView;
import at.gleb.sber.data.Valute;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface CalculatorContract {

    interface View extends BaseView<Presenter> {
        void setValutes(List<Valute> valutes);

        void showResult(float result, String shortName);
    }

    interface Presenter extends BasePresenter {
        void stop();

        void calculate(int selectedItemPosition, int selectedItemPosition1, String s);
    }
}
