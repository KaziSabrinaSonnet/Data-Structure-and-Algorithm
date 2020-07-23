package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.dictionaries.IDictionary;
import datastructures.lists.IList;

import java.util.function.LongUnaryOperator;

public class Experiment5DataStructureMemory {
    /*
    Note: please do not change these constants (or the constants in any of the other experiments)
    while working on your writeup
    */
    public static final long MAX_LIST_SIZE = 10000;
    public static final long STEP = 100;

    public static void main(String[] args) {
        IList<Long> listSizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_LIST_SIZE, STEP);

        /*
        Note: we're measuring memory usage, which is deterministic
        (assuming we constructed our DoubleLinkedList correctly) - that is,
        two DLLs with the same number of elements will always use the same
        amount of memory. So, there's no need to conduct trials, as they
        would end up being exactly the same.
        */

        PlotWindow.launch("Experiment 5", "Data Structure Size", "Memory Used (bytes)",
                new LongUnaryOperator[]{Experiment5DataStructureMemory::test1, Experiment5DataStructureMemory::test2},
                new String[]{"test1", "test2"}, listSizes);
    }

    public static long test1(long size) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, size, 1L);

        return AnalysisUtils.estimateObjectMemoryUsage(list);
    }

    public static long test2(long size) {
        IDictionary<Long, Long> dictionary = AnalysisUtils.makeArrayDictionary(size);

        return AnalysisUtils.estimateObjectMemoryUsage(dictionary);
    }
}
