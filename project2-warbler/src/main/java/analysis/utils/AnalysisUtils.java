package analysis.utils;

import datastructures.dictionaries.AVLTreeDictionary;
import datastructures.dictionaries.ArrayDictionary;
import datastructures.dictionaries.IDictionary;
import datastructures.lists.DoubleLinkedList;
import datastructures.lists.IList;
import org.openjdk.jol.info.GraphLayout;

import java.util.Random;

/**
 * This class contains a variety of utility methods useful when running
 * experiments. You do NOT need to understand how each method works.
 * Instead, focus on reading the method header comments so you understand
 * what each method DOES.
 */
public class AnalysisUtils {
    /**
     * Constructs a doubly-linked list of longs starting with `size`, going to `end`, in `step`
     * increments.
     */
    public static IList<Long> makeDoubleLinkedList(long start, long end, long step) {
        IList<Long> out = new DoubleLinkedList<>();
        for (long i = start; i < end; i += step) {
            out.add(i);
        }
        return out;
    }

    /**
     * Constructs an array dictionary containing keys from 0 to `size` (with dummy values).
     */
    public static IDictionary<Long, Long> makeArrayDictionary(long size) {
        IDictionary<Long, Long> dictionary = new ArrayDictionary<>();
        for (long i = 0; i < size; i += 1) {
            dictionary.put(i, -1L);
        }
        return dictionary;
    }

    /**
     * Constructs a dictionary containing keys from 0 to `size` (with dummy values)
     */
    public static IDictionary<Long, Long> makeAVLTreeDictionary(long size) {
        IDictionary<Long, Long> dictionary = new AVLTreeDictionary<>();
        for (long i = 0; i < size; i += 1) {
            dictionary.put(i, -1L);
        }
        return dictionary;
    }

    /**
     * Returns the approximate amount of memory used by the entire object, in bytes.
     */
    public static long estimateObjectMemoryUsage(Object obj) {
        return GraphLayout.parseInstance(obj).totalSize();
    }

    public static IList<char[]> generateRandomCharArrays(int numArrays, int lengthPerArray) {
        Random rand = new Random(numArrays);

        IList<char[]> output = new DoubleLinkedList<>();
        for (int i = 0; i < numArrays; i++) {
            char[] array = new char[lengthPerArray];
            for (int j = 0; j < lengthPerArray; j++) {
                array[j] = (char) (rand.nextInt('z' - 'a') + 'a');
            }
            output.add(array);
        }
        return output;
    }
}
