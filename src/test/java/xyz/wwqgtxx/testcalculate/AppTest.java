package xyz.wwqgtxx.testcalculate;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testCalculate3() throws Exception {
        assertEquals(App.calculate(3),29);
    }

    @Test
    public void testCalculate4() throws Exception {
        assertEquals(App.calculate(4),355);
    }
}
