package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.lists.IList;
import misc.Sorter;

import java.util.function.LongUnaryOperator;

public class Experiment2 {
    public static final long LIST_SIZE = 200000;
    public static final long STEP = 1000;

    public static void main(String[] args) {
        IList<Long> valuesOfK = AnalysisUtils.makeDoubleLinkedList(0L, LIST_SIZE, STEP);

        PlotWindow.launch("Experiment 2", "K", "Elapsed Time (ms)",
                new LongUnaryOperator[]{Experiment2::test}, new String[]{"test"},
                valuesOfK, 5, .01);
    }

    public static long test(long k) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0, LIST_SIZE, 1);

        long start = System.currentTimeMillis();
        Sorter.topKSort((int) k, list);
        return System.currentTimeMillis() - start;
    }
}
