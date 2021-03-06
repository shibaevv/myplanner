/*
 * Digest.java
 *
 * Created on 4 August 2004, 08:27
 */

package au.com.totemsoft.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import au.com.totemsoft.util.StringUtils;

/**
 *
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 */
public class Digest {
    
    public static final String MD5 = "MD5";


    /** Creates a new instance of Digest */
    private Digest() {}

    public static String digest( String value ) 
	{ 
		try {
            return digest( value, MD5 );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
	}

    public static String digest( String value, String algorithm ) 
        throws
            NoSuchAlgorithmException
    { 
        MessageDigest md = MessageDigest.getInstance( algorithm );
        
        byte [] output = md.digest( value.getBytes() );
        md.reset();
        
        //logger.info(value + ":" + toHexString( output ));
        return StringUtils.toHexString( output );
    
    }

}
