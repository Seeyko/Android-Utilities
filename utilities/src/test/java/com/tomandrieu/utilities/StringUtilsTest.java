package com.tomandrieu.utilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstCharUppercase() {
    }

    @Test
    public void reduceString() {
    }

    @Test
    public void isMailAdressValid() {
    }

    @Test
    public void md5() {
        String id103md5result = "6974ce5ac66061b44d9b9fed0ff9548";
        String id = "103";
        assertEquals(id103md5result, StringUtils.md5("103"));
    }
}