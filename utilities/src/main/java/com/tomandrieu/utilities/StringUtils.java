package com.tomandrieu.utilities;

import android.text.TextUtils;

public class StringUtils {
    private static final String PASSWORD_COMPLEXITY_REGEX_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
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

    public static boolean isPasswordValid(String password){
        if (TextUtils.isEmpty(password)) {
            return false;
        } else {
            return password.matches(PASSWORD_COMPLEXITY_REGEX_PATTERN);
        }
    }
}
