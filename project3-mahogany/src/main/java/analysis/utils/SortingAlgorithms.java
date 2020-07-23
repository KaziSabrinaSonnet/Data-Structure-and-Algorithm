package analysis.utils;

import datastructures.lists.DoubleLinkedList;
import datastructures.lists.IList;

import java.util.ArrayList;
import java.util.List;

public class SortingAlgorithms {
    public static IList<Long> insertionTopKSort(int k, IList<Long> list) {
        // make a copy of the list
        List<Long> tempList = new ArrayList<>();
        for (long value : list) {
            tempList.add(value);
        }

        // only sort when there are more than 1 elements
        if (list.size() > 1) {
            for (int i = 1; i < tempList.size(); i++) {
                // find where to insert
                int insertionIndex;
                for (insertionIndex = i; insertionIndex >= 0; insertionIndex--) {
                    if (insertionIndex == 0) {
                        break;
                    } else if (tempList.get(i) >= tempList.get(insertionIndex - 1)) {
                        // we found the spot
                        break;
                    }
                }
                // insert the elements
                long temp = tempList.get(i);
                for (int x = i; x > insertionIndex; x--) {
                    tempList.set(x, tempList.get(x - 1));
                }
                tempList.set(insertionIndex, temp);
            }
        }

        // only return the top K elements
        IList<Long> res = new DoubleLinkedList<>();
        int numInserted = 0;
        while (numInserted < k && numInserted < tempList.size()) {
            numInserted++;
            res.insert(0, tempList.get(tempList.size() - numInserted));
        }
        return res;
    }

    public static IList<Long> quickTopKSort(int k, IList<Long> list) {
        // make a copy of the list
        List<Long> tempList = new ArrayList<>();
        for (long value : list) {
            tempList.add(value);
        }

        quickTopKSortHelper(tempList, 0, list.size());

        // only return the top K elements
        IList<Long> res = new DoubleLinkedList<>();
        int numInserted = 0;
        while (numInserted < k && numInserted < tempList.size()) {
            numInserted++;
            res.insert(0, tempList.get(tempList.size() - numInserted));
        }
        return res;
    }

    public static void quickTopKSortHelper(List<Long> list, int loIndex, int hiIndex) {
        if (loIndex < hiIndex) {
            // Step 1: Partition
            // takes the last element as the pivot
            int pIndex = hiIndex - 1;
            long pivot = list.get(pIndex);
            int leftIndex = loIndex - 1;
            int rightIndex = loIndex;
            while (rightIndex < hiIndex - 1) {
                if (list.get(rightIndex) <= pivot) {
                    leftIndex++;
                    // swap
                    long temp = list.get(leftIndex);
                    list.set(leftIndex, list.get(rightIndex));
                    list.set(rightIndex, temp);
                }
                rightIndex++;
            }
            // swap pivot to the right place
            list.set(pIndex, list.get(leftIndex + 1));
            list.set(leftIndex + 1, pivot);
            pIndex = leftIndex + 1;

            // Step 2: Recursion
            quickTopKSortHelper(list, loIndex, pIndex);
            quickTopKSortHelper(list, pIndex + 1, hiIndex);
        }
    }

    public static IList<Long> mergeTopKSort(int k, IList<Long> list) {
        // make a copy of the list
        List<Long> tempList = new ArrayList<>();
        for (long value : list) {
            tempList.add(value);
        }

        mergeTopKSortHelper(tempList, 0, list.size());

        // only return the top K elements
        IList<Long> res = new DoubleLinkedList<>();
        int numInserted = 0;
        while (numInserted < k && numInserted < tempList.size()) {
            numInserted++;
            res.insert(0, tempList.get(tempList.size() - numInserted));
        }
        return res;
    }

    private static void mergeTopKSortHelper(List<Long> list, int loIndex, int hiIndex) {
        if (hiIndex - loIndex > 1) {
            // Step 1: Split and recurse
            int midIndex = (hiIndex + loIndex) / 2;
            mergeTopKSortHelper(list, loIndex, midIndex);
            mergeTopKSortHelper(list, midIndex, hiIndex);

            // Step 2: Merge
            List<Long> temp = new ArrayList<>();
            int leftIndex = loIndex;
            int rightIndex = midIndex;
            while (leftIndex < midIndex && rightIndex < hiIndex) {
                if (list.get(leftIndex) < list.get(rightIndex)) {
                    temp.add(list.get(leftIndex));
                    leftIndex++;
                } else {
                    temp.add(list.get(rightIndex));
                    rightIndex++;
                }
            }
            while (leftIndex < midIndex) {
                temp.add(list.get(leftIndex));
                leftIndex++;
            }
            while (rightIndex < hiIndex) {
                temp.add(list.get(rightIndex));
                rightIndex++;
            }
            // put the results back in
            for (int i = loIndex; i < hiIndex; i++) {
                list.set(i, temp.get(i - loIndex));
            }
        }
    }







}
