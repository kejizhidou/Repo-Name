package com.fq.miblog.core.util;

import com.google.common.base.Strings;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jifang
 * @since 16/3/13 下午1:07.
 */
public class PasswordUtil {

    private static String ENCRYPT_PREFIX = "Ω≈≈ç√˜≤≥Ωæ…qsdcns";

    private static String ENCRYPT_SUFFIX = "sdjflksjdf©˙œ∑´†¥˙˚¬∆¥ˆ";

    /**
     * 字符串 md5 加密
     *
     * @param inputString
     * @return
     */
    public static String encode(String inputString) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(md5.digest(new StringBuilder(ENCRYPT_PREFIX).
                    append(inputString).
                    append(ENCRYPT_SUFFIX).
                    toString().getBytes("utf-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断 sourceString 是否与 encodedString 相等
     *
     * @param sourceString
     * @param encodedString
     * @return
     */
    public static boolean checkEqual(String sourceString, String encodedString) {
        if (!Strings.isNullOrEmpty(sourceString)
                && !Strings.isNullOrEmpty(encodedString)
                && encode(sourceString).equals(encodedString)) {
            return true;
        }
        return false;
    }
}
