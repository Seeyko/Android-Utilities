package com.tomandrieu.utilities;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern ACCENTS_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    private static final Pattern NORMALIZE_PATTERN = Pattern.compile("\\'|\\ |\\-");
    public static String firstCharUppercase(String str) {
        if (str != null && !str.isEmpty()) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str;
        }
    }

    public static String reduceString(String str, int newLength) {
        String reduceString = str.substring(0, Math.min(str.length(), newLength));
        return reduceString;
    }

    public static boolean isMailAdressValid(String emailS) {
        if (TextUtils.isEmpty(emailS)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailS).matches();
        }
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param key
     * @param needle
     * @return true if needle is in key, false otherwise
     */
    public static boolean containsNormalize(String key, String needle) {
        return containsNormalize(NORMALIZE_PATTERN, key, needle);
    }

    public static boolean containsNormalize(Pattern p, String key, String needle) {
        return normalize(p, key).contains(normalize(p, needle));
    }
    /**
     * containsNormalize for ArrayList of string
     *
     * @param keys
     * @param needle
     * @return
     */
    public static boolean containsNormalize(ArrayList<String> keys, String needle) {
        for (int i = 0; i < keys.size(); i++) {
            if (containsNormalize(NORMALIZE_PATTERN, keys.get(i), needle)) {
                return true;
            }
        }
        return false;
    }


    public static String normalize(String string) {
        return normalize(NORMALIZE_PATTERN, string);
    }

    public static String normalize(Pattern p, String string) {
        return replaceAll(p, string, "").toLowerCase();
    }

    public static String replaceAll(Pattern p, String input, String replacement) {
        return Normalizer.normalize(p.matcher(input).replaceAll(replacement), Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }


    public static String removeAccents(String string) {
        return ACCENTS_PATTERN.matcher(Normalizer.normalize(string, Normalizer.Form.NFD)).replaceAll("").replaceAll("'", "");
    }

    public static String removeDash(String string) {
        return string.replaceAll("-", "");
    }

    public static String removeWhiteSpace(String string) {
        return string.replaceAll(" ", "");
    }

}
