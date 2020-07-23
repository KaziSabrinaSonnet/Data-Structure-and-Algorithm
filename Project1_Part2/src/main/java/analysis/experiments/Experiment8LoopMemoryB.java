package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.lists.DoubleLinkedList;
import datastructures.lists.IList;

import java.util.function.LongUnaryOperator;

import static analysis.utils.AnalysisUtils.makeDoubleLinkedList;

public class Experiment8LoopMemoryB {
    /*
    Note: please do not change these constants (or the constants in any of the other experiments)
    while working on your writeup
    */
    private static final int MAX_ARRAY_SIZE = 100000;
    private static final int PLOT_FREQUENCY = 100;

    public static void main(String[] args) {
        IList<Long> values = makeDoubleLinkedList(0, MAX_ARRAY_SIZE, PLOT_FREQUENCY);

        LongUnaryOperator[] experimentResultGetters = AnalysisUtils.runMemoryTests(MAX_ARRAY_SIZE, PLOT_FREQUENCY,
                Experiment8LoopMemoryA::test2, Experiment8LoopMemoryB::test2, Experiment8LoopMemoryB::test3);

        PlotWindow.launch("Experiment 8B", "Iteration Number", "Memory Used (bytes)",
                experimentResultGetters, new String[]{"test1", "test2", "test3"}, values);
    }

    public static void test2(int maxSize, int freq, Long[] results) {
        // request Java to run garbage collection to clean up old memory from before the test starts
        System.gc();
        IList<Long[]> experimentArray = new DoubleLinkedList<>();
        for (int i = 0; i < maxSize; i++) {
            experimentArray.add(null);
            experimentArray.set(0, new Long[i]);

            if (i % freq == 0) {
                // request garbage collection again to clean up the unreachable arrays
                System.gc();
                results[i / freq] = AnalysisUtils.estimateCurrentTotalMemoryUsage();
            }
        }
    }

    public static void test3(int maxSize, int freq, Long[] results) {
        System.gc();
        IList<Long[]> experimentArray = new DoubleLinkedList<>();
        for (int i = 0; i < maxSize; i++) {
            experimentArray.add(null);
            experimentArray.set(0, new Long[i]);

            if (i % freq == 0) {
                results[i / freq] = AnalysisUtils.estimateCurrentTotalMemoryUsage();
                // also plot memory usage from before garbage collection
                System.gc();
            }
        }
    }
}
