package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.dictionaries.IDictionary;
import datastructures.lists.IList;

import java.util.function.LongUnaryOperator;

public class Experiment3ArrayDictionaryRemove {
    /*
    Note: please do not change these constants (or the constants in any of the other experiments)
    while working on your writeup
    */
    public static final long MAX_DICTIONARY_SIZE = 15000;
    public static final long STEP = 100;

    public static void main(String[] args) {
        IList<Long> dictionarySizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_DICTIONARY_SIZE, STEP);

        PlotWindow.launch("Experiment 3", "Dictionary Size", "Elapsed Time (ns)",
                new LongUnaryOperator[]{Experiment3ArrayDictionaryRemove::test1,
                        Experiment3ArrayDictionaryRemove::test2},
                new String[]{"test1", "test2"}, dictionarySizes, 1000, .05);
    }

    public static long test1(long dictionarySize) {
        // don't include the cost of constructing the dictionary when running the tests
        IDictionary<Long, Long> dictionary = AnalysisUtils.makeArrayDictionary(dictionarySize);

        long start = System.nanoTime();
        dictionary.remove(0L);
        for (long i = dictionarySize - 1; i >= 1; i--) {
            dictionary.remove(i);
        }

        return System.nanoTime() - start;
    }

    public static long test2(long dictionarySize) {
        IDictionary<Long, Long> dictionary = AnalysisUtils.makeArrayDictionary(dictionarySize);

        long start = System.nanoTime();
        for (long i = dictionarySize - 1; i >= 0; i--) {
            dictionary.remove(i);
        }

        return System.nanoTime() - start;
    }
}
