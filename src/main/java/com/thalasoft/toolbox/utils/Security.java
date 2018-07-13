package com.thalasoft.toolbox.utils;

import java.nio.charset.Charset;
import java.util.Base64;

public class Security {

    public static byte[] encodeBase64(String username, String password) {
        String usernamePassword = username + ":" + password;
        return Base64.getEncoder().encode(usernamePassword.getBytes());
    }

    public static String phpCompatibleMD5(String str) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes(Charset.forName("UTF8")));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

}
