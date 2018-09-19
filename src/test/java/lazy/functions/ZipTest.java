package lazy.functions;

import lazy.LazyValue;
import lazy.list.List;
import org.junit.Test;

import static lazy.list.List.addToHead;
import static lazy.list.List.get;
import static org.junit.Assert.*;
import static lazy.functions.Zip.*;

public class ZipTest {

    @Test
    public void zipWithTestWithEqualsLength() {
        List<Integer> list1 = addToHead(
                1, new LazyValue<>(() -> addToHead(
                        2, new LazyValue<>(() -> addToHead(
                                3, null))
                )));

        List<Integer> list2 = addToHead(
                1, new LazyValue<>(() -> addToHead(
                        2, new LazyValue<>(() -> addToHead(
                                3, null))
                )));

        List<Integer> result = zipWith(Integer::sum, list1, list2);

        assertEquals(2, (int) get(0, result));
        assertEquals(4, (int) get(1, result));
        assertEquals(6, (int) get(2, result));
        assertNull(get(3, result));
    }

    @Test
    public void zipWithTestWithDifferentLength() {
        List<Integer> list1 = addToHead(
                1, new LazyValue<>(() -> addToHead(
                        2, null
                )));

        List<Integer> list2 = addToHead(
                1, new LazyValue<>(() -> addToHead(
                        2, new LazyValue<>(() -> addToHead(
                                3, null))
                )));

        List<Integer> result = zipWith(Integer::sum, list1, list2);

        assertEquals(2, (int) get(0, result));
        assertEquals(4, (int) get(1, result));
        assertNull(get(2, result));
    }
}