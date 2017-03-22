package JSON;

import javax.xml.bind.annotation.XmlElement;


/**
 * Created by Kees on 22/03/2017.
 */
public class UserJSON {

    @XmlElement public String email;
    @XmlElement public String name;
    @XmlElement public String password;
}
