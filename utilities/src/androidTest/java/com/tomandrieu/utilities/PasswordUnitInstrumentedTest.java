package com.tomandrieu.utilities;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.tomandrieu.utilities.password.PasswordCheck;
import com.tomandrieu.utilities.password.PasswordUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordUnitInstrumentedTest {
    @Test
    public void checkPasswordRequirmentsAsStringFull() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        String expected = appContext.getString(R.string.error_password_start) + " " +
                appContext.getString(R.string.error_password_digit) + ", " +
                appContext.getString(R.string.error_password_maj) + ", " +
                appContext.getString(R.string.error_password_min) + ", " +
                appContext.getString(R.string.error_password_min_nb_char);

        PasswordCheck passwordCheck = PasswordUtils.getPasswordMatchComplexityLevel("");
                assertEquals(expected, passwordCheck.getPasswordRequirments(appContext));
    }

    @Test
    public void checkPasswordRequirmentsAsStringDigit() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        String expected = appContext.getString(R.string.error_password_start) + " " +
                appContext.getString(R.string.error_password_digit);

        PasswordCheck passwordCheck = PasswordUtils.getPasswordMatchComplexityLevel("addzdZDE");
        assertEquals(expected, passwordCheck.getPasswordRequirments(appContext));
    }

    @Test
    public void checkPasswordRequirmentsAsStringMajAndMin() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        String expected = appContext.getString(R.string.error_password_start) + " " +
                appContext.getString(R.string.error_password_min) + ", " +
                appContext.getString(R.string.error_password_maj);

        PasswordCheck passwordCheck = PasswordUtils.getPasswordMatchComplexityLevel("4545554545");
        assertEquals(expected, passwordCheck.getPasswordRequirments(appContext));
    }

    @Test
    public void checkPasswordRequirmentsAsStringNbChar() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        String expected = appContext.getString(R.string.error_password_start) + " " +
                appContext.getString(R.string.error_password_min_nb_char) ;

        PasswordCheck passwordCheck = PasswordUtils.getPasswordMatchComplexityLevel("fe4DZ");
        assertEquals(expected, passwordCheck.getPasswordRequirments(appContext));
    }
}
