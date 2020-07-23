package analysis.experiments;

import analysis.utils.AnalysisUtils;
import analysis.utils.PlotWindow;
import datastructures.lists.DoubleLinkedList;
import datastructures.lists.IList;

import java.util.function.LongUnaryOperator;

public class Experiment7RecursiveReverse {
    /*
    Note: please do not change these constants (or the constants in any of the other experiments)
    while working on your writeup
    */
    public static final long MAX_DICTIONARY_SIZE = 200000;
    public static final long STEP = 500;

    public static void main(String[] args) {
        IList<Long> listSizes = AnalysisUtils.makeDoubleLinkedList(0L, MAX_DICTIONARY_SIZE, STEP);

        PlotWindow.launch("Experiment 7", "List Size", "Elapsed Time (ms)",
                new LongUnaryOperator[]{Experiment7RecursiveReverse::test1, Experiment7RecursiveReverse::test2},
                new String[]{"test1", "test2"}, listSizes, 2, .05);
    }

    public static long test1(long listSize) {
        // don't include the cost of constructing the dictionary when running these tests
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, listSize, 1L);

        long start = System.currentTimeMillis();

        reverse1(list);

        return System.currentTimeMillis() - start;
    }

    public static long test2(long listSize) {
        IList<Long> list = AnalysisUtils.makeDoubleLinkedList(0L, listSize, 1L);

        long start = System.currentTimeMillis();

        reverse2(list);

        return System.currentTimeMillis() - start;
    }

    /**
     * Returns a reversed version of a list.
     */
    private static <T> IList<T> reverse1(IList<T> list) {
        int len = list.size();

        if (len <= 1) {
            // base case: 1 or fewer elements
            return list;
        } else {
            // recursive case

            // first, divide the list into two lists of equal length
            int middle = len / 2;
            IList<T> left = new DoubleLinkedList<>();
            IList<T> right = new DoubleLinkedList<>();
            int i = 0;
            for (T element : list) {
                if (i < middle) {
                    left.add(element);
                } else {
                    right.add(element);
                }
                i++;
            }

            // second, call `reverse1` recursively to reverse the left and right halves
            IList<T> reversedLeft = reverse1(left);
            IList<T> reversedRight = reverse1(right);

            // last, combine reversed left and right halves by adding all the elements of
            // `reversedRight` into result, followed by the elements of `reversedLeft`
            IList<T> result = new DoubleLinkedList<>();
            for (T element : reversedRight) {
                result.add(element);
            }
            for (T element : reversedLeft) {
                result.add(element);
            }

            return result;
        }

    }

    /**
     * Returns a reversed version of a list.
     */
    private static <T> IList<T> reverse2(IList<T> list) {
        int len = list.size();

        if (len <= 1) {
            // base case: 1 or fewer elements
            return list;
        } else {
            // recursive case

            // first, divide the list into three lists of equal length
            int middleLeft = len / 3;
            int middleRight = len * 2 / 3;
            IList<T> left = new DoubleLinkedList<>();
            IList<T> middle = new DoubleLinkedList<>();
            IList<T> right = new DoubleLinkedList<>();
            int i = 0;
            for (T element : list) {
                if (i < middleLeft) {
                    left.add(element);
                } else if (i < middleRight){
                    middle.add(element);
                } else {
                    right.add(element);
                }
                i++;
            }

            // second, call `reverse2` recursively to each third of the list
            IList<T> reversedLeft = reverse2(left);
            IList<T> reverseMiddle = reverse2(middle);
            IList<T> reversedRight = reverse2(right);

            // last, combine the reversed thirds of the list by adding all the elements of
            // `reversedRight` into result, followed by the elements of `reversedMiddle`, followed
            // by the elements of `reversedLeft`.
            IList<T> result = new DoubleLinkedList<>();
            for (T element : reversedRight) {
                result.add(element);
            }
            for (T element : reverseMiddle) {
                result.add(element);
            }
            for (T element : reversedLeft) {
                result.add(element);
            }

            return result;
        }
    }
}
