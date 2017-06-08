package xyz.wwqgtxx.testcalculate;


import org.junit.Test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testCalculate_second_step() throws Exception {
        List<BitSet> list = new ArrayList<>();
        BitSet bitSet;
        bitSet = new BitSet();
        bitSet.set(1);
        list.add(bitSet);
        bitSet = new BitSet();
        bitSet.set(2);
        list.add(bitSet);
        bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(2);
        list.add(bitSet);
        bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(3);
        list.add(bitSet);
        bitSet = new BitSet();
        bitSet.set(2);
        bitSet.set(3);
        list.add(bitSet);
        bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(2);
        bitSet.set(3);
        list.add(bitSet);
        assertEquals(App.calculate_second_step(list), false);
    }

    @Test
    public void testCalculate3() throws Exception {
        assertEquals(App.calculate(3), 29);
    }

    @Test
    public void testCalculate4() throws Exception {
        assertEquals(App.calculate(4), 355);
    }
}
