package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.lists.IList;

import java.util.Iterator;
import java.util.function.LongUnaryOperator;

public class Experiment1DoubleLinkedListIterator {
    /*
    Note: please do not change these constants (or the constants in any of the other experiments)
    while working on your writeup
    */
    public static final long MAX_LIST_SIZE = 20000;
    public static final long STEP = 100;

    public static void main(String[] args) {
        IList<Long> listSizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_LIST_SIZE, STEP);

        PlotWindow.launch("Experiment 1", "List Size", "Elapsed Time (ns)",
                new LongUnaryOperator[]{Experiment1DoubleLinkedListIterator::test1,
                        Experiment1DoubleLinkedListIterator::test2, Experiment1DoubleLinkedListIterator::test3},
                new String[]{"test1", "test2", "test3"}, listSizes, 1000, .05);
    }

    /*
     * We will call `test1`, `test2`, and `test3` repeatedly with the `listSize` parameter set to
     * each of the values from the `listSizes` list created above.
     * The parameter `listSize` determines the size of the list used in the rest of the method.
     * The method returns the amount of time taken to sum up every element of the list, in
     * nanoseconds.
     *
     * (This overall process will be the same for most of our experiments.)
     */

    public static Long test1(long listSize) {
        // don't include the cost of constructing the list when running the tests
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, listSize, 1L);

        long start = System.nanoTime();
        long temp = 0L;
        for (int i = 0; i < listSize; i++) {
            // use the "get" method to access each element in order
            temp += list.get(i);
        }

        return System.nanoTime() - start;
    }

    public static Long test2(long listSize) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, listSize, 1L);

        long start = System.nanoTime();
        long temp = 0L;
        Iterator<Long> iter = list.iterator();
        while (iter.hasNext()) {
            // use `Iterator.next` to access each element in order
            temp += iter.next();
        }

        return System.nanoTime() - start;
    }

    public static Long test3(Long listSize) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, listSize, 1L);

        long start = System.nanoTime();
        long temp = 0L;
        for (long item : list) {
            // use a for-each loop to access each element in order
            temp += item;
        }

        return System.nanoTime() - start;
    }
}
