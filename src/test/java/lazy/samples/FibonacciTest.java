package lazy.samples;

import lazy.LazyValue;
import lazy.list.List;
import org.junit.Test;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;
import static lazy.functions.Zip.zipWith;
import static lazy.list.List.addToHead;
import static lazy.list.List.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests some cool features of lib
 */
public class FibonacciTest {
    /**
     * Infinite list of fibonacci number
     */
    private static List<BigInteger> fibonacciList = addToHead(valueOf(0),
            new LazyValue<>(() -> addToHead(valueOf(1),
                    new LazyValue<>(() -> zipWith(BigInteger::add, fibonacci(), fibonacci().getTail().getValue())))));

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
        assertEquals(valueOf(1), get(1, fibonacciList));
        assertEquals(valueOf(1), get(2, fibonacciList));
        assertEquals(valueOf(2), get(3, fibonacciList));
        assertEquals(valueOf(3), get(4, fibonacciList));

        assertNotNull(get(6, fibonacciList));

        BigInteger bi = get(1000, fibonacciList);

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
