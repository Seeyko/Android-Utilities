package com.tomandrieu.utilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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

    @Test
    public void containsNormalize() {
        String searchingText = "saint ";
        String spotName = "Sâint-Gé orges-d'Orques";

        String normalize = StringUtils.normalize(spotName);
        String normalizeSearchText = StringUtils.normalize(spotName);

//        assertEquals(spotName, normalize);

        assertEquals(true, StringUtils.containsNormalize(spotName, searchingText));

        String spotName2 = "Saint malo skatepark";
        String spotName3 = "Saintpétèrbourg";
        ArrayList<String> spots = new ArrayList<>();
        spots.add(spotName);
        spots.add(spotName2);
        spots.add(spotName3);
        assertEquals(true, StringUtils.containsNormalize(spots, searchingText));
    }
}