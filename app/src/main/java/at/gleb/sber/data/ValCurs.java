package at.gleb.sber.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Immutable model class for a response .
 * <p/>
 * Created by gleb on 02.08.16.
 */
@Root(name = "ValCurs")
public class ValCurs implements Serializable {
    @Attribute(name = "Date")
    public String date = "";

    @Attribute(name = "name")
    public String name = "";

    @ElementList(inline = true, name = "Valute")
    public List<Valute> valutes;

    public ValCurs() {
    }
}
