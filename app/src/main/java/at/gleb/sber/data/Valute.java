package at.gleb.sber.data;

/**
 * Immutable model class for a Valute.
 *
 * Created by gleb on 01.08.16.
 */
public class Valute {

    private String mId;
    private int mNumCode;
    private String mCharCode;
    private int mNominal;
    private String mName;
    private float mValue;

    public Valute(String id, int numCode, String charCode,
                  int nominal, String name, float value) {
        this.mId = id;
        this.mNumCode = numCode;
        this.mCharCode = charCode;
        this.mNominal = nominal;
        this.mName = name;
        this.mValue = value;
    }

    public String getId() {
        return mId;
    }

    public int getNumCode() {
        return mNumCode;
    }

    public void setNumCode(int mNumCode) {
        this.mNumCode = mNumCode;
    }

    public String getCharCode() {
        return mCharCode;
    }

    public void setCharCode(String mCharCode) {
        this.mCharCode = mCharCode;
    }

    public int getNominal() {
        return mNominal;
    }

    public void setNominal(int mNominal) {
        this.mNominal = mNominal;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float mValue) {
        this.mValue = mValue;
    }
}
