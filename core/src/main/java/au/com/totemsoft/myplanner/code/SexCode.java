/*
 * SexCode.java
 *
 * Created on 23 July 2000, 12:09
 */

package au.com.totemsoft.myplanner.code;

/**
 * 
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 * @version
 */

import java.util.Map;
import java.util.TreeMap;

public final class SexCode extends Code {

    public final static Integer FEMALE = new Integer(1);

    public final static Integer MALE = new Integer(2);

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

        codeMap.put("Female", FEMALE);
        codeMap.put("Male", MALE);
    }

}
