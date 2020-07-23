package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.dictionaries.ChainedHashDictionary;
import datastructures.dictionaries.IDictionary;
import datastructures.lists.IList;

import java.util.Arrays;
import java.util.function.LongUnaryOperator;

public class Experiment3HashingChainCapacity {
    public static final long MAX_LIST_SIZE = 80000;
    public static final long STEP = 1000;

    public static final int LENGTH_PER_ARRAY = 50;

    public static void main(String[] args) {
        IList<Long> dictionarySizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_LIST_SIZE, STEP);

        PlotWindow.launch("Experiment 3", "Data Structure Size", "Elapsed Time (ms)",
                new LongUnaryOperator[]{Experiment3HashingChainCapacity::test1,
                        Experiment3HashingChainCapacity::test2,
                        Experiment3HashingChainCapacity::test3},
                new String[]{"test1", "test2", "test3"}, dictionarySizes);
    }

    public static long test1(long dictionarySize) {
        return runTest((int) dictionarySize, 2);
    }

    public static long test2(long dictionarySize) {
        return runTest((int) dictionarySize, 500);
    }

    public static long test3(long dictionarySize) {
        return runTest((int) dictionarySize, 1000);
    }

    private static long runTest(int dictionarySize, int chainInitialCapacity) {
        IList<char[]> chars = AnalysisUtils.generateRandomCharArrays(dictionarySize, LENGTH_PER_ARRAY);
        long start = System.currentTimeMillis();
        IDictionary<Integer, char[]> dict = new ChainedHashDictionary<>(.75, 10, chainInitialCapacity);
        for (char[] array : chars) {
            dict.put(Arrays.hashCode(array), array);
        }
        return System.currentTimeMillis() - start;
    }
}
