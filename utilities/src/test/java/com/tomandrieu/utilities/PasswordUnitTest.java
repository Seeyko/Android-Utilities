package com.tomandrieu.utilities;

import com.tomandrieu.utilities.password.PasswordCheck;
import com.tomandrieu.utilities.password.PasswordRequirment;
import com.tomandrieu.utilities.password.PasswordUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PasswordUnitTest {

    @Test
    public void checkDigit(){
        PasswordCheck pCheck = PasswordUtils.getPasswordMatchComplexityLevel("azertyui");
        assertEquals(PasswordRequirment.DIGIT, pCheck.getPasswordRequirmentsNotPass().get(0));
    }

    @Test
    public void checkCapital(){
        PasswordCheck pCheck = PasswordUtils.getPasswordMatchComplexityLevel("aa454dadzza");
        assertEquals(PasswordRequirment.MAJ, pCheck.getPasswordRequirmentsNotPass().get(0));
    }
    @Test
    public void checkMin(){
        PasswordCheck pCheck = PasswordUtils.getPasswordMatchComplexityLevel("ACE45454DZAD");
        assertEquals(PasswordRequirment.MIN, pCheck.getPasswordRequirmentsNotPass().get(0));
    }
    @Test
    public void checkNbChar(){
        PasswordCheck pCheck = PasswordUtils.getPasswordMatchComplexityLevel("Aa34567");
        assertEquals(PasswordRequirment.MIN_NB_CHAR, pCheck.getPasswordRequirmentsNotPass().get(0));
    }
}