package com.tomandrieu.utilities;

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
}
