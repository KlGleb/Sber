package at.gleb.sber.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Immutable model class for a Valute.
 *
 * Created by gleb on 01.08.16.
 */
@Root(name = "Valute")
public class Valute implements Serializable {

    private String mId;
    private int mNumCode;
    private String mCharCode;
    private int mNominal;
    private String mName;
    private String mValue;


    public Valute() {
    }

    public Valute(String id, int numCode, String charCode,
                  int nominal, String name, String value) {
        this.mId = id;
        this.mNumCode = numCode;
        this.mCharCode = charCode;
        this.mNominal = nominal;
        this.mName = name;
        this.mValue = value;
    }

    @Attribute(name = "ID")
    public void setId(String id) {
        this.mId = id;
    }

    @Attribute(name = "ID")
    public String getId() {
        return mId;
    }

    @Element(name = "NumCode")
    public int getNumCode() {
        return mNumCode;
    }

    @Element(name = "NumCode")
    public void setNumCode(int mNumCode) {
        this.mNumCode = mNumCode;
    }

    @Element(name = "CharCode")
    public String getCharCode() {
        return mCharCode;
    }

    @Element(name = "CharCode")
    public void setCharCode(String mCharCode) {
        this.mCharCode = mCharCode;
    }

    @Element(name = "Nominal")
    public int getNominal() {
        return mNominal;
    }

    @Element(name = "Nominal")
    public void setNominal(int mNominal) {
        this.mNominal = mNominal;
    }

    @Element(name = "Name")
    public String getName() {
        return mName;
    }

    @Element(name = "Name")
    public void setName(String mName) {
        this.mName = mName;
    }

    @Element(name = "Value")
    public String getValue() {
        return mValue;
    }

    @Element(name = "Value")
    public void setValue(String mValue) {
        this.mValue = mValue;
    }
}
