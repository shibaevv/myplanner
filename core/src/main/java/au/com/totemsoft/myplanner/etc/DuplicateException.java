/*
 * DuplicateException.java
 *
 * Created on June 25, 2002, 10:48 AM
 */

package au.com.totemsoft.myplanner.etc;

/**
 * 
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 * @version
 */
public class DuplicateException extends java.lang.Exception {

    /**
     * Creates new <code>DuplicateException</code> without detail message.
     */
    public DuplicateException() {
    }

    /**
     * Constructs an <code>DuplicateException</code> with the specified detail
     * message.
     * 
     * @param msg
     *            the detail message.
     */
    public DuplicateException(String msg) {
        super(msg);
    }
}
