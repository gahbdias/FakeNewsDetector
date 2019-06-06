package br.ufrn.imd.modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// extraído de http://www.sha1-online.com/sha1-java/

public class SHAConverter {
	public static String stringToSha1( String input ) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
}
