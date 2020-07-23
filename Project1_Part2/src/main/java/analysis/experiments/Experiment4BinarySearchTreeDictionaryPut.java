package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.dictionaries.BinarySearchTreeDictionary;
import datastructures.dictionaries.IDictionary;
import datastructures.lists.IList;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.LongUnaryOperator;

public class Experiment4BinarySearchTreeDictionaryPut {
    /*
    Note: please do not change these constants (or the constants in any of the other experiments)
    while working on your writeup
    */
    public static final long MAX_DICTIONARY_SIZE = 10000;
    public static final long STEP = 100;

    public static void main(String[] args) {
        IList<Long> dictionarySizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_DICTIONARY_SIZE, STEP);

        PlotWindow.launch("Experiment 4", "Dictionary Size", "Elapsed Time (ns)",
                new LongUnaryOperator[]{Experiment4BinarySearchTreeDictionaryPut::test1,
                        Experiment4BinarySearchTreeDictionaryPut::test2,
                        Experiment4BinarySearchTreeDictionaryPut::test3},
                new String[]{"test1", "test2", "test3"}, dictionarySizes, 1000, .05);
    }

    public static long test1(long dictionarySize) {
        // don't include the cost of constructing the dictionary when running the tests
        IDictionary<Long, Long> dictionary = new BinarySearchTreeDictionary<>();

        long start = System.nanoTime();
        for (long i = 0L; i < dictionarySize; i++) {
            dictionary.put(i, i);
        }

        return System.nanoTime() - start;
    }

    public static long test2(long dictionarySize) {
        IDictionary<Long, Long> dictionary = new BinarySearchTreeDictionary<>();

        long start = System.nanoTime();
        for (long i = dictionarySize - 1; i >= 0; i--) {
            dictionary.put(i, i);
        }

        return System.nanoTime() - start;
    }

    public static long test3(long dictionarySize) {

        List<Long> values = new ArrayList<>();
        for (long i = 0; i < dictionarySize; i++) {
            values.add(i);
        }
        Collections.shuffle(values);

        IDictionary<Long, Integer> dictionary = new BinarySearchTreeDictionary<>();

        long start = System.nanoTime();
        for (int i = 0; i < dictionarySize; i++) {
            dictionary.put(values.get(i), i);
        }

        return System.nanoTime() - start;
    }
}
