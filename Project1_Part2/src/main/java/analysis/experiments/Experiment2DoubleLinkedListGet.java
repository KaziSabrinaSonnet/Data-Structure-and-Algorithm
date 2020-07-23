package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.lists.IList;

import java.util.function.LongUnaryOperator;

public class Experiment2DoubleLinkedListGet {
    /*
    Note: please do not change these constants (or the constants in any of the other experiments)
    while working on your writeup
    */
    public static final int NUM_TIMES_TO_REPEAT = 1000;
    public static final long MAX_LIST_SIZE = 20000;
    public static final long STEP = 100;

    public static void main(String[] args) {
        IList<Long> indices = AnalysisUtils.makeDoubleLinkedList(STEP, MAX_LIST_SIZE + STEP, STEP);

        PlotWindow.launch("Experiment 2", "List Size", "Elapsed Time (ns)",
                new LongUnaryOperator[]{Experiment2DoubleLinkedListGet::test1, Experiment2DoubleLinkedListGet::test2,
                        Experiment2DoubleLinkedListGet::test3},
                new String[]{"test1", "test2", "test3"}, indices, 1000, .05);
    }

    public static long test1(long listSize) {
        // don't include the cost of constructing the list when running the tests
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, listSize, 1L);

        long start = System.nanoTime();

        /*
        try getting the same thing multiple times, since a single `get` is too fast to reliably
        measure runtime. By testing the same amount of `get`s for each index, we come up with a
        kind of "average" runtime of `get` for that index.
         */
        long temp = 0L;
        for (int i = 0; i < NUM_TIMES_TO_REPEAT; i++) {
            temp += list.get(0);
        }

        return System.nanoTime() - start;
    }

    public static long test2(long listSize) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, listSize, 1L);

        long start = System.nanoTime();

        long temp = 0L;
        for (int i = 0; i < NUM_TIMES_TO_REPEAT; i++) {
            temp += list.get((int) listSize - 1);
        }

        return System.nanoTime() - start;
    }

    public static long test3(long listSize) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, listSize, 1L);

        long start = System.nanoTime();

        long temp = 0L;
        for (int i = 0; i < NUM_TIMES_TO_REPEAT; i++) {
            temp += list.get((int) listSize / 2);
        }

        return System.nanoTime() - start;
    }
}
