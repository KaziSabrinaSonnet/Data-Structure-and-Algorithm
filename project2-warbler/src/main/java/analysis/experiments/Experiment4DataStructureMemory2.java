package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.dictionaries.IDictionary;
import datastructures.lists.IList;

import java.util.function.LongUnaryOperator;

public class Experiment4DataStructureMemory2 {
    public static final long MAX_LIST_SIZE = 10000;
    public static final long STEP = 100;

    public static void main(String[] args) {
        IList<Long> sizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_LIST_SIZE, STEP);

        /*
        Note: we're measuring memory usage, which is deterministic (assuming we constructed our data
        structures correctly) - that is, two data structures with the same number of elements will
        always use the same amount of memory. So, there's no need to conduct trials, as they would
        end up being exactly the same.
        */

        PlotWindow.launch("Experiment 4", "Data Structure Size", "Memory Usage (bytes)",
                new LongUnaryOperator[]{Experiment4DataStructureMemory2::test1,
                        Experiment4DataStructureMemory2::test2,
                        Experiment4DataStructureMemory2::test3},
                new String[]{"test1", "test2", "test3"}, sizes);
    }

    public static long test1(long size) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, size, 1L);

        return AnalysisUtils.estimateObjectMemoryUsage(list);
    }

    public static long test2(long size) {
        IDictionary<Long, Long> dictionary = AnalysisUtils.makeArrayDictionary(size);

        return AnalysisUtils.estimateObjectMemoryUsage(dictionary);
    }

    public static long test3(long size) {
        IDictionary<Long, Long> dictionary = AnalysisUtils.makeAVLTreeDictionary(size);

        return AnalysisUtils.estimateObjectMemoryUsage(dictionary);
    }
}
