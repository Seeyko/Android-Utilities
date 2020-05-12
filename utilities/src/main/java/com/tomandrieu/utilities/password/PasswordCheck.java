package com.tomandrieu.utilities.password;

import android.content.Context;

import com.tomandrieu.utilities.R;

import java.util.List;

import static com.tomandrieu.utilities.SeeykoUtils.getIdentifier;

public class PasswordCheck {
    private PasswordComplexityLevel passwordComplexityLevel;
    private List<PasswordRequirment> passwordRequirmentsNotPass;

    public PasswordCheck(PasswordComplexityLevel passwordComplexityLevel, List<PasswordRequirment> requirmentsNotPass) {
        this.passwordComplexityLevel = passwordComplexityLevel;
        this.passwordRequirmentsNotPass = requirmentsNotPass;
    }

    public PasswordComplexityLevel getPasswordComplexityLevel() {
        return passwordComplexityLevel;
    }

    public List<PasswordRequirment> getPasswordRequirmentsNotPass() {
        return passwordRequirmentsNotPass;
    }

    public String getPasswordRequirments(Context context) {
        String requirments = "";
        for (int i = 0; i < getPasswordRequirmentsNotPass().size(); i++) {
            String identifier = "error_password_" + getPasswordRequirmentsNotPass().get(i).name().toLowerCase();
            System.out.println("requirment: " +  getPasswordRequirmentsNotPass().get(i).name().toLowerCase());
            if (i == 0) {
                requirments += context.getString(R.string.error_password_start) + " " + context.getString(getIdentifier(identifier, "string", context));
            } else {
                requirments += ", " + context.getString(getIdentifier(identifier, "string", context));
            }
            System.out.println("trad: " +  context.getString(getIdentifier(identifier, "string", context)));
        }

        return requirments;
    }
}
