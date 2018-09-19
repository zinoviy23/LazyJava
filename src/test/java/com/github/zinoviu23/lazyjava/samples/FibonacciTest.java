package com.github.zinoviu23.lazyjava.samples;

import com.github.zinoviu23.lazyjava.LazyValue;
import com.github.zinoviu23.lazyjava.functions.Zip;
import com.github.zinoviu23.lazyjava.list.List;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests some cool features of lib
 */
public class FibonacciTest {
    /**
     * Infinite list of fibonacci number
     */
    private static List<BigInteger> fibonacciList = List.addToHead(valueOf(0),
            new LazyValue<>(() -> List.addToHead(valueOf(1),
                    new LazyValue<>(() -> Zip.zipWith(BigInteger::add, fibonacci(), fibonacci().getTail().getValue())))));

    /**
     * Function for getting list of fibonacci numbers
     * @return list of fibonacci numbers
     */
    private static List<BigInteger> fibonacci() {
        return fibonacciList;
    }

    /**
     * Test fibonacci sequence
     */
    @Test
    public void fibonacciTest() {
        Assert.assertEquals(valueOf(1), List.get(1, fibonacciList));
        Assert.assertEquals(valueOf(1), List.get(2, fibonacciList));
        Assert.assertEquals(valueOf(2), List.get(3, fibonacciList));
        Assert.assertEquals(valueOf(3), List.get(4, fibonacciList));

        assertNotNull(List.get(6, fibonacciList));

        BigInteger bi = List.get(1000, fibonacciList);

        System.gc();  // tests deleting unnecessary compution objects

        assertNotNull(bi);
        System.out.println("100s: " + bi);
    }

    @Test
    public void fibonacciTestIterator() {
        System.out.println("Print with iterator");
        int cnt = 0;
        for (BigInteger i : fibonacciList) {
            assertNotNull(i);
            cnt++;
            System.out.println(i);
            if (cnt > 30)
                break;
        }
    }

    @Test
    public void fibonacciTestStream() {
        System.out.println("Print with stream");

        fibonacciList.stream().limit(30).forEach((i) -> {
            assertNotNull(i);
            System.out.println(i);
        });
    }
}
