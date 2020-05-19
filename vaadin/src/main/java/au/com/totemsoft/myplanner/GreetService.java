package au.com.totemsoft.myplanner;

import org.springframework.stereotype.Service;

@Service
public class GreetService /*implements java.io.Serializable*/ {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }

}
