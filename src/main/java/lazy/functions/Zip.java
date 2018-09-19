package lazy.functions;

import lazy.LazyValue;
import lazy.list.List;

import java.util.function.BiFunction;

public class Zip {
    public static <T1, T2, Tresult> List<Tresult> zipWith(
            BiFunction<T1, T2, Tresult> function,
            List<T1> list1,
            List<T2> list2) {

        if (list1 == null || list2 == null) {
            return null;
        }

        if (list1.getTail() == null || list2.getTail() == null) {
            return new List<>(function.apply(list1.getHead(), list2.getHead()), new LazyValue<>(() -> null));
        }

        return new List<>(function.apply(list1.getHead(), list2.getHead()),
                new LazyValue<>(() -> zipWith(function, list1.getTail().getValue(), list2.getTail().getValue())));
    }
}
