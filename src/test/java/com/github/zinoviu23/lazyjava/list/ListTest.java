package com.github.zinoviu23.lazyjava.list;

import com.github.zinoviu23.lazyjava.LazyValue;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void addToHeadTest() {
        List<Integer> list = List.addToHead(
                1, new LazyValue<>(() -> List.addToHead(
                    2, new LazyValue<>(() -> List.addToHead(
                        3, null))
        )));

        assertEquals(1, (int) list.getHead());
        assertEquals(2, (int) list.getTail().getValue().getHead());
        assertEquals(3, (int) list.getTail().getValue().getTail().getValue().getHead());
        assertNull(list.getTail().getValue().getTail().getValue().getTail());
    }

    @Test
    public void getTest() {
        List<Integer> list = List.addToHead(
                1, new LazyValue<>(() -> List.addToHead(
                        2, new LazyValue<>(() -> List.addToHead(
                                3, null))
                )));

        assertEquals(1,(int) List.get(0, list));
        assertEquals(2,(int) List.get(1, list));
        assertEquals(3,(int) List.get(2, list));
        assertNull(List.get(4, list));
    }

    /**
     * Tests iterator
     */
    @Test
    public void iteratorTest() {
        List<Integer> list = List.addToHead(
                1, new LazyValue<>(() -> List.addToHead(
                        2, new LazyValue<>(() -> List.addToHead(
                                3, null))
                )));


        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));

        for (int i : list) {
            if (!set.contains(i))
                fail();
            set.remove(i);
            System.out.print(i + " ");
        }
    }

    /**
     * Tests stream
     */
    @Test
    public void streamTest() {
        List<Integer> list = List.addToHead(
                1, new LazyValue<>(() -> List.addToHead(
                        2, new LazyValue<>(() -> List.addToHead(
                                3, new LazyValue<>(() -> List.addToHead(
                                        4, null))))
                )));

        int result = list.stream()
                .map(x -> x + 2)
                .reduce(0, (x, y) -> x + y);

        assertEquals(18, result);
    }
}