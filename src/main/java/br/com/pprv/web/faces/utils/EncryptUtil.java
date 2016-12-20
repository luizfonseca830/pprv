/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.faces.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ioliveira
 */
public class EncryptUtil {

    /**
     * Encrypt a string using sha256 algorithm base 64
     *
     * @param text
     * @return
     */
    public static String sha256(String text) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalAccessError();
        }

        messageDigest.reset();
        try {
            messageDigest.update(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalAccessError();
        }

        byte[] output = messageDigest.digest();
        return String.format("%0" + (output.length * 2) + "X",
                new BigInteger(1, output)).toLowerCase();
    }
}
