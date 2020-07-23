package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.lists.DoubleLinkedList;
import datastructures.lists.IList;

import java.util.function.LongUnaryOperator;

import static analysis.utils.AnalysisUtils.makeDoubleLinkedList;

public class Experiment8LoopMemoryA {
    /*
    Note: please do not change these constants (or the constants in any of the other experiments)
    while working on your writeup
    */
    private static final int MAX_ARRAY_SIZE = 10000;
    private static final int PLOT_FREQUENCY = 10;

    public static void main(String[] args) {
        IList<Long> values = makeDoubleLinkedList(0, MAX_ARRAY_SIZE, PLOT_FREQUENCY);

        LongUnaryOperator[] experimentResultGetters = AnalysisUtils.runMemoryTests(MAX_ARRAY_SIZE, PLOT_FREQUENCY,
                Experiment8LoopMemoryA::test1, Experiment8LoopMemoryA::test2);

        PlotWindow.launch("Experiment 8A", "Iteration Number", "Memory Used (bytes)",
                experimentResultGetters, new String[]{"test1", "test2"}, values);
    }

    public static void test1(int maxSize, int freq, Long[] results) {
        // request Java to run garbage collection to clean up old memory from before the test starts
        System.gc();
        IList<Long[]> experimentList = new DoubleLinkedList<>();
        for (int i = 0; i < maxSize; i++) {
            experimentList.add(new Long[i]);

            // populate output array
            if (i % freq == 0) {
                results[i/ freq] = AnalysisUtils.estimateCurrentTotalMemoryUsage();
            }
        }
    }

    public static void test2(int maxSize, int freq, Long[] results) {
        System.gc();
        IList<Long[]> experimentList = new DoubleLinkedList<>();
        for (int i = 0; i < maxSize; i++) {
            // append a null so that the list size stays the same as in `test1`
            experimentList.add(null);
            // replace the first element with the new array
            experimentList.set(0, new Long[i]);

            if (i % freq == 0) {
                results[i / freq] = AnalysisUtils.estimateCurrentTotalMemoryUsage();
            }
        }
    }
}
