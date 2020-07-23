package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.lists.IList;
import misc.Sorter;

import java.util.function.LongUnaryOperator;

public class Experiment1 {
    public static final long MAX_LIST_SIZE = 200000;
    public static final long STEP = 1000;
    public static final int K = 500;

    public static void main(String[] args) {
        IList<Long> listSizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_LIST_SIZE, STEP);

        PlotWindow.launch("Experiment 1", "List Size", "Elapsed Time (ms)",
                new LongUnaryOperator[]{Experiment1::test}, new String[]{"test"},
                listSizes, 2, .01);
    }

    public static long test(long listSize) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0, listSize, 1);

        long start = System.currentTimeMillis();
        Sorter.topKSort(K, list);
        return System.currentTimeMillis() - start;
    }
}
