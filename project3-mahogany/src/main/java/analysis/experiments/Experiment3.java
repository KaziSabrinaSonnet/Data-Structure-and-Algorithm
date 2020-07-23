package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import analysis.utils.SortingAlgorithms;
import datastructures.lists.IList;
import misc.Sorter;

import java.util.function.LongUnaryOperator;

public class Experiment3 {
    public static final long MAX_LIST_SIZE = 200000;
    public static final long STEP = 2000;
    public static final int K = 50;

    public static void main(String[] args) {
        IList<Long> listSizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_LIST_SIZE, STEP);

        PlotWindow.launch("Experiment 3", "n", "Elapsed Time (ms)",
                new LongUnaryOperator[]{Experiment3::testHeap, Experiment3::testInsertion, Experiment3::testMerge},
                new String[]{"heap sort", "insertion sort", "merge sort"}, listSizes, 2, .01);
    }

    public static long testHeap(long listSize) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0, listSize, 1);

        long start = System.currentTimeMillis();
        Sorter.topKSort(K, list);
        return System.currentTimeMillis() - start;
    }

    public static long testInsertion(long listSize) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0, listSize, 1);

        long start = System.currentTimeMillis();
        SortingAlgorithms.insertionTopKSort(K, list);
        return System.currentTimeMillis() - start;
    }

    public static long testMerge(long listSize) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0, listSize, 1);

        long start = System.currentTimeMillis();
        SortingAlgorithms.mergeTopKSort(K, list);
        return System.currentTimeMillis() - start;
    }
}
