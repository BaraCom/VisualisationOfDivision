package com.bkolomiets.division.test;

import com.bkolomiets.division.Divider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DivisionTest {

    @Test()
    public void testDivide1() {
        Divider divider = new Divider();
        String actual = divider.divide(1888, 44);

        assertEquals("1888 | 44\n" +
                              "176  + --\n" +
                              "---  | 42\n" +
                              " 128\n" +
                              "  88\n" +
                              " ---",
                              actual);
    }

    @Test
    public void testDivide2() {
        Divider divider = new Divider();
        String actual = divider.divide(12652, 5);

        assertEquals("12652 | 5\n"
                            + "10    + ---\n"
                            + "--    | 253\n"
                            + " 26\n"
                            + " 25\n"
                            + " --\n"
                            + "  15\n"
                            + "  15\n"
                            + "  --",
                            actual);
    }

    @Test
    public void testDivide3() {
        Divider divider = new Divider();
        String actual = divider.divide(1265212652, 5);

        assertEquals("1265212652 | 5\n"
                            + "10         + -------\n"
                            + "--         | 2534253\n"
                            + " 26\n"
                            + " 25\n"
                            + " --\n"
                            + "  15\n"
                            + "  15\n"
                            + "  --\n"
                            + "    21\n"
                            + "    20\n"
                            + "    --\n"
                            + "     12\n"
                            + "     10\n"
                            + "     --\n"
                            + "      26\n"
                            + "      25\n"
                            + "      --\n"
                            + "       15\n"
                            + "       15\n"
                            + "       --",
                    actual);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivide4() {
        Divider divider = new Divider();
        divider.divide(12652, 0);
    }
}
