package com.bkolomiets.division.test;

import com.bkolomiets.division.Division;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DivisionTest {

    @Test
    public void testVisualisationAndDivision() {
        Division division = new Division();
        String actual = division.visualisationAndDivision();

        assertEquals("1888 | 44\n" +
                              "176  + --\n" +
                              "---  | 42\n" +
                              " 128\n" +
                              "  88\n" +
                              " ---",
                              actual);
    }
}
