package com.tomandrieu.utilities;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;

public class StringUtils {
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

    public static boolean globalEquals(String a, String b) {
        a = a.replaceAll("\\s", "").replaceAll("-", "");
        b = b.replaceAll("\\s", "").replaceAll("-", "");

        Collator collate = java.text.Collator.getInstance();
        collate.setStrength(java.text.Collator.PRIMARY);
        collate.setDecomposition(java.text.Collator.CANONICAL_DECOMPOSITION);
        return collate.equals(a, b);
    }

}
