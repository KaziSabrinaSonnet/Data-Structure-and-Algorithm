package analysis.utils;

import datastructures.lists.DoubleLinkedList;
import datastructures.lists.IList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

/**
 * This class contains a variety of utility methods useful when running
 * experiments. You do NOT need to understand how each method works.
 * Instead, focus on reading the method header comments so you understand
 * what each method DOES.
 */
public class AnalysisUtils {
    /**
     * Constructs a doubly-linked list of longs starting with `size`, going to `end`, in `step`
     * increments.
     */
    public static IList<Long> makeDoubleLinkedList(long start, long end, long step) {
        IList<Long> out = new DoubleLinkedList<>();
        for (long i = start; i < end; i += step) {
            out.add(i);
        }
        return out;
    }

    /**
     * Constructs a doubly-linked list of longs from `0` (inclusive) to `n` (exclusive) with a
     * random ordering.
     */
    public static IList<Long> makeRandomDoubleLinkedList(long n) {
        List<Long> list = new LinkedList<>();
        for (long i = 0; i < n; i++) {
            list.add(i);
        }
        // randomize element order
        Collections.shuffle(list);
        // convert into `IList`
        IList<Long> out = new DoubleLinkedList<>();
        for (long x : list) {
            out.add(x);
        }
        return out;
    }
}
