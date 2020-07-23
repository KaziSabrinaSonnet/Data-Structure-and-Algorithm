package analysis.utils;

import datastructures.dictionaries.ArrayDictionary;
import datastructures.dictionaries.IDictionary;
import datastructures.lists.DoubleLinkedList;
import datastructures.lists.IList;
import org.openjdk.jol.info.GraphLayout;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.Arrays;
import java.util.function.LongUnaryOperator;

/**
 * This class contains a variety of utility methods useful when running
 * experiments. You do NOT need to understand how each method works.
 * Instead, focus on reading the method header comments so you understand
 * what each method DOES.
 */
public class AnalysisUtils {
    public static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

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
     * Constructs an array dictionary containing keys from 0 to `size` (with dummy values).
     */
    public static IDictionary<Long, Long> makeArrayDictionary(long size) {
        IDictionary<Long, Long> dictionary = new ArrayDictionary<>();
        for (long i = 0; i < size; i += 1) {
            dictionary.put(i, -1L);
        }
        return dictionary;
    }

    /**
     * Returns the approximate amount of memory used by the entire object, in bytes.
     */
    public static long estimateObjectMemoryUsage(Object obj) {
        return GraphLayout.parseInstance(obj).totalSize();
    }

    /**
     * Returns the approximate amount of memory used by all Java objects that currently exist, in
     * bytes.
     */
    public static long estimateCurrentTotalMemoryUsage() {
        return memoryMXBean.getHeapMemoryUsage().getUsed();
    }

    /**
     * Runs memory tests.
     *
     * The memory tests that uses this test runner are different from the runtime tests, which
     * simply record the runtime of some code called multiple times with differing input sizes:
     * instead, these tests record the memory usage of code at different times during a single
     * execution.
     */
    @SafeVarargs
    public static LongUnaryOperator[] runMemoryTests(int maxSize, int outputFrequency,
                                                     TriConsumer<Integer, Integer, Long[]>... tests) {
        // First allocate all output arrays so that this persistent memory is the same for all tests
        Long[][] resultArrays = new Long[tests.length][maxSize / outputFrequency];
        // Run tests
        for (int i = 0; i < tests.length; i++) {
            TriConsumer<Integer, Integer, Long[]> test = tests[i];
            System.out.println("Running test " + (i+1) + "...");
            long start = System.currentTimeMillis();
            test.accept(maxSize, outputFrequency, resultArrays[i]);
            System.out.println("  Finished in " + (System.currentTimeMillis() - start) + " ms.");
        }
        // create getter lambdas for the results of each test for the plotting window to use
        return Arrays.stream(resultArrays)
                .map(r -> (LongUnaryOperator) x -> r[(int) x / outputFrequency])
                .toArray(LongUnaryOperator[]::new);
    }
}
