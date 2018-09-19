package com.github.zinoviu23.lazyjava.functions;

import com.github.zinoviu23.lazyjava.LazyValue;
import com.github.zinoviu23.lazyjava.list.List;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZipTest {

    @Test
    public void zipWithTestWithEqualsLength() {
        List<Integer> list1 = List.addToHead(
                1, new LazyValue<>(() -> List.addToHead(
                        2, new LazyValue<>(() -> List.addToHead(
                                3, null))
                )));

        List<Integer> list2 = List.addToHead(
                1, new LazyValue<>(() -> List.addToHead(
                        2, new LazyValue<>(() -> List.addToHead(
                                3, null))
                )));

        List<Integer> result = Zip.zipWith(Integer::sum, list1, list2);

        assertEquals(2, (int) List.get(0, result));
        assertEquals(4, (int) List.get(1, result));
        assertEquals(6, (int) List.get(2, result));
        assertNull(List.get(3, result));
    }

    @Test
    public void zipWithTestWithDifferentLength() {
        List<Integer> list1 = List.addToHead(
                1, new LazyValue<>(() -> List.addToHead(
                        2, null
                )));

        List<Integer> list2 = List.addToHead(
                1, new LazyValue<>(() -> List.addToHead(
                        2, new LazyValue<>(() -> List.addToHead(
                                3, null))
                )));

        List<Integer> result = Zip.zipWith(Integer::sum, list1, list2);

        assertEquals(2, (int) List.get(0, result));
        assertEquals(4, (int) List.get(1, result));
        assertNull(List.get(2, result));
    }
}