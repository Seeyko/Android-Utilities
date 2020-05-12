package com.tomandrieu.utilities.password;

import java.util.List;

public class PasswordCheck {
    private PasswordComplexityLevel passwordComplexityLevel;
    private List<PasswordRequirment> passwordRequirmentsNotPass;

    public PasswordCheck(PasswordComplexityLevel passwordComplexityLevel, List<PasswordRequirment> requirmentsNotPass){
        this.passwordComplexityLevel = passwordComplexityLevel;
        this.passwordRequirmentsNotPass = requirmentsNotPass;
    }

    public PasswordComplexityLevel getPasswordComplexityLevel() {
        return passwordComplexityLevel;
    }

    public List<PasswordRequirment> getPasswordRequirmentsNotPass() {
        return passwordRequirmentsNotPass;
    }
}
