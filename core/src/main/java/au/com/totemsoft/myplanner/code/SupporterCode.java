/*
 * SupporterCode.java
 *
 * Created on 22 August 2001, 16:12
 */

package au.com.totemsoft.myplanner.code;

/**
 * 
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 * @version
 */

import java.util.Map;
import java.util.TreeMap;

public class SupporterCode extends Code {

    public final static Integer CLIENT = new Integer(1);

    public final static Integer NOT_CLIENT = new Integer(2);

    private static Map codeMap;

    protected Map getCodeMap() {
        if (codeMap == null) {
            codeMap = new TreeMap();
            initCodeMap();
        }
        return codeMap;
    }

    private static void initCodeMap() {
        codeMap.clear();
        codeMap.put(NONE, VALUE_NONE);

        codeMap.put("ClientView", CLIENT);
        codeMap.put("Not ClientView", NOT_CLIENT);
    }

}
