package com.tomandrieu.utilities.password;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class PasswordUtils {
    private static final String REGEX_DIGIT = "^(?=.*[0-9])$";
    private static final String REGEX_MAJ = "^(?=.*[A-Z])$";
    private static final String REGEX_MIN = "^(?=.*[a-z])$";
    private static final String REGEX_MIN_NB_CHAR = "^(?=.{0,8}$).*";
    private static final String REGEX_NO_WHITESPACE = ".*\\s.*";

    private static final String PASSWORD_COMPLEXITY_REGEX_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    public static boolean isPasswordValid(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        } else {
            return password.matches(PASSWORD_COMPLEXITY_REGEX_PATTERN);
        }
    }

    public static boolean hasWhiteSpace(String password){
        return password.matches(REGEX_NO_WHITESPACE);
    }

    public static PasswordCheck getPasswordMatchComplexityLevel(String password) {
        List<PasswordRequirment> passwordRequirmentList = new ArrayList<>();

        if (!password.matches(REGEX_DIGIT)) passwordRequirmentList.add(PasswordRequirment.DIGIT);
        if (!password.matches(REGEX_MAJ)) passwordRequirmentList.add(PasswordRequirment.MAJ);
        if (!password.matches(REGEX_MIN)) passwordRequirmentList.add(PasswordRequirment.MIN);
        if (password.matches(REGEX_MIN_NB_CHAR)) passwordRequirmentList.add(PasswordRequirment.MIN_NB_CHAR);

        PasswordCheck passwordCheck = null;
        switch (passwordRequirmentList.size()) {
            case 0:
                passwordCheck = new PasswordCheck(PasswordComplexityLevel.GOOD, passwordRequirmentList);
                break;
            case 1:
            case 2:
                passwordCheck = new PasswordCheck(PasswordComplexityLevel.INTERMEDIATE, passwordRequirmentList);
                break;
            case 3:
            case 4:
                passwordCheck = new PasswordCheck(PasswordComplexityLevel.WEAK, passwordRequirmentList);
                break;
        }
        return passwordCheck;
    }

    //endregion
}

