package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.dictionaries.ChainedHashDictionary;
import datastructures.dictionaries.IDictionary;
import datastructures.lists.IList;

import java.util.function.LongUnaryOperator;

public class Experiment2HashingResizingLoadFactor {
    public static final long MAX_DICTIONARY_SIZE = 50000;
    public static final long STEP = 500;


    public static void main(String[] args) {
        IList<Long> dictionarySizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_DICTIONARY_SIZE, STEP);

        PlotWindow.launch("Experiment 2", "Dictionary Size", "Elapsed Time (ns)",
                new LongUnaryOperator[]{Experiment2HashingResizingLoadFactor::test1,
                        Experiment2HashingResizingLoadFactor::test2},
                new String[]{"test1", "test2"}, dictionarySizes, 1, .01);
    }

    public static long test1(long dictionarySize) {
        return runTest(dictionarySize, 0.75);
    }

    public static long test2(long dictionarySize) {
        return runTest(dictionarySize, 300);
    }

    private static long runTest(long dictionarySize, double resizingLoadFactor) {
        long start = System.nanoTime();
        IDictionary<Long, Long> dictionary = new ChainedHashDictionary<>(resizingLoadFactor, 10, 8);
        for (long i = 0L; i < dictionarySize; i++) {
            dictionary.put(i, 0L);
        }
        return System.nanoTime() - start;
    }
}
