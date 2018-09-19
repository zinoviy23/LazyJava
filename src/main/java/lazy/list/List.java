package lazy.list;

import lazy.LazyValue;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class List<T> implements Iterable<T> {
    private T head;

    private LazyValue<List<T>> tail;

    public T getHead() {
        return head;
    }

    public LazyValue<List<T>> getTail() {
        return tail;
    }

    public List(T head, LazyValue<List<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    public static <E> List<E> addToHead(E value, LazyValue<List<E>> list) {
        return new List<>(value, list);
    }

    public static <E> E get(int index, List<E> list) {
        if (list == null)
            return null;

        if (index == 0) {
            return list.getHead();
        }

        if (list.getTail() == null)
            return null;

        return get(index - 1, list.getTail().getValue());
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            List<T> current = List.this;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T head = current.getHead();

                if (current.getTail() != null)
                    current = current.getTail().getValue();
                else
                    current = null;

                return head;
            }
        };
    }

    /**
     * Generates stream from list
     * @return stream generated from list
     */
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
