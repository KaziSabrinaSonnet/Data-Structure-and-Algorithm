package datastructures.priorityqueues;

import datastructures.EmptyContainerException;
import datastructures.dictionaries.IDictionary;
import misc.BaseTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * See spec for details on what kinds of tests this class should include.
 */
@Tag("project3")
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestArrayHeapPriorityQueue extends BaseTest {
    protected <T extends Comparable<T>> IPriorityQueue<T> makeInstance() {
        return new ArrayHeapPriorityQueue<>();
    }

    /**
     * A helper method for accessing the private array inside an `ArrayHeapPriorityQueue`.
     */
    protected static <T extends Comparable<T>> Comparable<T>[] getArray(IPriorityQueue<T> heap) {
        return ((ArrayHeapPriorityQueue<T>) heap).heap;
    }

    protected static <T extends Comparable<T>> IDictionary<T, Integer> getDictionary(IPriorityQueue<T> heap) {
        return ((ArrayHeapPriorityQueue<T>) heap).indices;
    }

    @Test
    void testAddEmptyInternalArray() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(3);
        Comparable<Integer>[] array = getArray(heap);
        assertThat(array[0], is(3));
    }

    @Test
    void testUpdateDecrease() {
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (int i = 1; i <= 5; i++) {
            heap.add(new IntPair(i, i));
        }

        heap.replace(new IntPair(2, 2), new IntPair(0, 0));

        assertThat(heap.removeMin(), is(new IntPair(0, 0)));
        assertThat(heap.removeMin(), is(new IntPair(1, 1)));
    }

    @Test
    void testUpdateIncrease() {
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }

        IntPair newValue = new IntPair(5, 5);
        heap.replace(values[0], newValue);

        assertThat(heap.removeMin(), is(values[1]));
        assertThat(heap.removeMin(), is(values[2]));
        assertThat(heap.removeMin(), is(newValue));
    }

    /* Tests for Empty Heap */
    @Test
    void testSizeOfEmptyHeap() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThat(heap.size(), is(0));
    }

    @Test
    void testContainsEmptyHeap() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThat(heap.contains(2), is(false));
    }

    @Test
    void testAddNullEmptyHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThrows(IllegalArgumentException.class, () -> heap.add(null));
    }

    @Test
    void testRemoveMinEmptyHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThrows(EmptyContainerException.class, heap::removeMin);
    }

    @Test
    void testPeekMinEmptyHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThrows(EmptyContainerException.class, heap::peekMin);
    }

    @Test
    void testRemoveEmptyHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThrows(InvalidElementException.class, () -> heap.remove(3));
    }

    @Test
    void testRemoveNullEmptyHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThrows(IllegalArgumentException.class, () -> heap.remove(null));
    }

    @Test
    void testReplaceEmptyHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThrows(InvalidElementException.class, () -> heap.replace(3, 4));
    }

    @Test
    void testReplaceOldNullEmptyHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThrows(IllegalArgumentException.class, () -> heap.replace(null, 4));
    }

    @Test
    void testReplaceNewNullEmptyHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThrows(IllegalArgumentException.class, () -> heap.replace(3, null));
    }

    @Test
    void testReplaceOldNewNullEmptyHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        assertThrows(IllegalArgumentException.class, () -> heap.replace(null, null));
    }

    /* Tests for single element Heap */

    @Test
    void testSizeOfSingleElementHeap() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        assertThat(heap.size(), is(1));
        Comparable<Integer>[] array = getArray(heap);
        assertThat(array[0], is(5));
    }

    @Test
    void testContainsSingleElementHeap() {
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        assertThat(heap.contains(2), is(false));
        assertThat(heap.contains(5), is(true));
    }

    @Test
    void testRemoveMinSingleElementHeap(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        assertThat(heap.removeMin(), is(5));
        assertThrows(EmptyContainerException.class, heap::removeMin);
    }

    @Test
    void testPeekMinSingleElementHeap(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        assertThat(heap.peekMin(), is(5));
        Comparable<Integer>[] array = getArray(heap);
        assertThat(array[0], is(5));
    }

    @Test
    void testRemoveSingleElementHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        assertThrows(InvalidElementException.class, () -> heap.remove(3));
        heap.remove(5);
        assertThat(heap.size(), is(0));
    }

    @Test
    void testRemoveNullSingleElementHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        assertThrows(IllegalArgumentException.class, () -> heap.remove(null));
    }

    @Test
    void testReplaceSingleElementHeap(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        heap.replace(5, 3);
        assertThat(heap.size(), is(1));
        assertThat(heap.peekMin(), is(3));
        assertThrows(IllegalArgumentException.class, () -> heap.replace(3, null));
        assertThrows(InvalidElementException.class, () -> heap.replace(5, 4));
    }

    @Test
    void testReplaceOldNullSingleElementHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        assertThrows(IllegalArgumentException.class, () -> heap.replace(null, 4));
    }

    @Test
    void testReplaceNewNullSingleElementHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        assertThrows(IllegalArgumentException.class, () -> heap.replace(5, null));
    }

    @Test
    void testReplaceOldNewNullSingleElementHeapThrowsException(){
        IPriorityQueue<Integer> heap = this.makeInstance();
        heap.add(5);
        assertThrows(IllegalArgumentException.class, () -> heap.replace(null, null));
    }

    /* Tests for multiple element Heap */
    @Test
    void testSizeOfMultipleElementHeap() {
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }

        assertThat(heap.size(), is(5));
        Comparable<IntPair>[] array = getArray(heap);
        assertThat(array[0], is(new IntPair(0, 0)));
    }

    @Test
    void testContainsMultipleElementHeap() {
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }

        assertThat(heap.contains(new IntPair(6, 6)), is(true));
        assertThat(heap.contains(new IntPair(8, 8)), is(true));
        assertThat(heap.contains(new IntPair(0, 0)), is(true));
        assertThat(heap.contains(new IntPair(9, 9)), is(false));

        heap.add(new IntPair(9, 9));
        assertThat(heap.contains(new IntPair(9, 9)), is(true));
    }

    @Test
    void testRemoveMinMultipleElementHeap(){
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }

        for (int i = 0; i <= 8; i = i + 2){
            assertThat(heap.removeMin(), is(new IntPair(i, i)));
        }

        assertThrows(EmptyContainerException.class, heap::removeMin);
    }

    @Test
    void testPeekMinMultipleElementHeap(){
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }

        assertThat(heap.peekMin(), is(new IntPair(0, 0)));
        Comparable<IntPair>[] array = getArray(heap);
        assertThat(array[2], is(new IntPair(4, 4)));
    }

    @Test
    void testRemoveMultipleElementHeap(){
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }

        heap.remove(new IntPair(4, 4));
        heap.remove(new IntPair(0, 0));
        heap.remove(new IntPair(8, 8));

        assertThat(heap.contains(new IntPair(0, 0)), is(false));
        assertThat(heap.contains(new IntPair(6, 6)), is(true));
        assertThat(heap.peekMin(), is(new IntPair(2, 2)));

        heap.remove(new IntPair(2, 2));
        assertThat(heap.contains(new IntPair(2, 2)), is(false));
        assertThat(heap.contains(new IntPair(6, 6)), is(true));
        assertThat(heap.peekMin(), is(new IntPair(6, 6)));
        assertThat(heap.size(), is(1));

        heap.removeMin();
        assertThat(heap.size(), is(0));
        assertThrows(InvalidElementException.class, () -> heap.remove(new IntPair(0, 0)));
    }

    @Test
    void testRemoveNullMultipleElementHeapThrowsException(){
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }

        assertThrows(IllegalArgumentException.class, () -> heap.remove(null));
    }

    @Test
    void testReplaceMultipleElementHeap(){
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }

        heap.replace(new IntPair(0, 0), new IntPair(9, 9));
        assertThat(heap.size(), is(5));
        assertThat(heap.peekMin(), is(new IntPair(2, 2)));
        assertThrows(IllegalArgumentException.class, () -> heap.replace(new IntPair(2, 2), null));
        assertThrows(InvalidElementException.class, () -> heap.replace(new IntPair(1, 1), new IntPair(2, 2)));
        assertThrows(InvalidElementException.class, () -> heap.replace(new IntPair(1, 1), new IntPair(3, 3)));
    }

    @Test
    void testReplaceOldNullMultipleElementHeapThrowsException(){
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }
        assertThrows(IllegalArgumentException.class, () -> heap.replace(null, new IntPair(4, 4)));
        heap.removeMin();
        assertThrows(InvalidElementException.class, () -> heap.replace(new IntPair(0, 0), new IntPair(6, 6)));

    }

    @Test
    void testReplaceNewNullMultipleElementHeapThrowsException(){
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }
        assertThrows(InvalidElementException.class, () -> heap.replace(new IntPair(8, 8), new IntPair(4, 4)));
    }

    @Test
    void testReplaceOldNewNullMultipleElementHeapThrowsException(){
        IntPair[] values = IntPair.createArray(new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}, {8, 8}});
        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair value : values) {
            heap.add(value);
        }

        assertThrows(IllegalArgumentException.class, () -> heap.replace(null, null));
    }

    /* Tests for Large Heap */
    @Test
    void testSizeOfLargeHeap() {
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        assertThat(heap.size(), is(150));
        Comparable<IntPair>[] array = getArray(heap);
        assertThat(array[101], is(new IntPair(101, 101)));
    }

    @Test
    void testContainsLargeHeap() {
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        assertThat(heap.contains(new IntPair(140, 140)), is(true));
        assertThat(heap.contains(new IntPair(8, 8)), is(true));
        assertThat(heap.contains(new IntPair(0, 0)), is(true));
        assertThat(heap.contains(new IntPair(151, 151)), is(false));
    }

    @Test
    void testRemoveMinLargeHeap(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        for (int i = 0; i < 150; i++){
            assertThat(heap.removeMin(), is(new IntPair(i, i)));
        }

        assertThrows(EmptyContainerException.class, heap::removeMin);
    }

    @Test
    void testPeekMinLargeHeap(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        assertThat(heap.peekMin(), is(new IntPair(0, 0)));
        Comparable<IntPair>[] array = getArray(heap);
        assertThat(array[10], is(new IntPair(10, 10)));
    }

    @Test
    void testRemoveLargeHeap(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        heap.remove(new IntPair(4, 4));
        heap.remove(new IntPair(0, 0));
        heap.remove(new IntPair(8, 8));

        assertThat(heap.contains(new IntPair(0, 0)), is(false));
        assertThat(heap.contains(new IntPair(6, 6)), is(true));
        assertThat(heap.peekMin(), is(new IntPair(1, 1)));

        heap.remove(new IntPair(2, 2));
        assertThat(heap.contains(new IntPair(2, 2)), is(false));
        assertThat(heap.contains(new IntPair(6, 6)), is(true));
        assertThat(heap.peekMin(), is(new IntPair(1, 1)));
        assertThat(heap.size(), is(146));

        for (int i = 0; i < 50; i++){
            heap.removeMin();
        }
        assertThat(heap.size(), is(96));
        assertThrows(InvalidElementException.class, () -> heap.remove(new IntPair(0, 0)));
    }

    @Test
    void testRemoveNullLargeHeapThrowsException(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        assertThrows(IllegalArgumentException.class, () -> heap.remove(null));
    }

    @Test
    void testReplaceLargeHeap(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        heap.replace(new IntPair(0, 0), new IntPair(150, 150));
        assertThat(heap.size(), is(150));
        assertThat(heap.peekMin(), is(new IntPair(1, 1)));
        assertThrows(IllegalArgumentException.class, () -> heap.replace(new IntPair(2, 2), null));
        assertThrows(InvalidElementException.class, () -> heap.replace(new IntPair(1, 1), new IntPair(2, 2)));
    }

    @Test
    void testReplaceOldNullLargeHeapThrowsException(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        assertThrows(IllegalArgumentException.class, () -> heap.replace(null, new IntPair(150, 150)));
        heap.removeMin();
        assertThrows(InvalidElementException.class, () -> heap.replace(new IntPair(0, 0), new IntPair(150, 150)));

    }

    @Test
    void testReplaceNewNullLargeHeapThrowsException(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }
        assertThrows(InvalidElementException.class, () -> heap.replace(new IntPair(8, 8), new IntPair(4, 4)));
    }

    @Test
    void testReplaceOldNewNullLargeHeapThrowsException(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        assertThrows(IllegalArgumentException.class, () -> heap.replace(null, null));
    }

    @Test
    void testMinHeapOrderInvariant(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        IntPair prev = heap.removeMin();
        while (heap.size() > 0){
            IntPair cur = heap.removeMin();
            assertThat(prev.compareTo(cur) < 0, is(true));
            prev = cur;
        }
    }

    @Test
    void testMinHeapParentChildInvariant(){
        IntPair[] values = new IntPair[150];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        Comparable<IntPair>[] array = getArray(heap);
        int idx = heap.size()-1;

        while (idx > 0) {
            int parentIdx = (idx - 1)/4;
            assertThat(array[parentIdx].compareTo((IntPair) array[idx]) < 0, is(true));
            idx--;
        }
    }

    @Test
    void testAddElementDescendingOrderLargeHeap(){
        IntPair[] values = new IntPair[150];
        for (int i = 149; i >= 0; i--) {
            values[values.length-i-1] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        assertThat(heap.size(), is(150));
        assertThat(heap.removeMin(), is(new IntPair(0, 0)));

        IntPair prev = heap.removeMin();
        while (heap.size() > 0){
            IntPair cur = heap.removeMin();
            assertThat(prev.compareTo(cur) < 0, is(true));
            prev = cur;
        }
    }

    @Test
    void testDictionaryStatesLargeHeap(){
        IntPair[] values = new IntPair[150];
        for (int i = 149; i >= 0; i--) {
            values[values.length-i-1] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        IDictionary<IntPair, Integer> dictionary = getDictionary(heap);

        assertThat(dictionary.size(), is(150));
        assertThat(dictionary.containsKey(new IntPair(149, 149)), is(true));
        assertThat(dictionary.containsKey(new IntPair(150, 150)), is(false));

        while (heap.size() > 0){
            IntPair cur = heap.removeMin();
            assertThat(dictionary.containsKey(cur), is(false));
        }
        assertThat(dictionary.size(), is(0));
    }

    @Test
    void testDuplicateValuesLargeHeap(){
        IntPair[] values = IntPair.createArray(new int[][]{{0, 12}, {1, 11}, {2, 10}, {3, 9}, {4, 8}, {5, 7}, {6, 6},
                {7, 5}, {8, 4}, {9, 3}, {10, 2}, {11, 1}, {12, 0}});

        IPriorityQueue<IntPair> heap = this.makeInstance();

        for (IntPair element : values){
            heap.add(element);
        }

        IntPair prev = heap.removeMin();
        while (heap.size() > 0) {
            IntPair cur = heap.removeMin();
            assertThat(prev.compareTo(cur)==0, is(true));
            assertThat(prev.equals(cur), is(false));
            prev = cur;
        }
    }

    @Test
    void testStress() {
        assertTimeoutPreemptively(Duration.ofSeconds(20), () -> {
            IntPair[] values = new IntPair[500000];
            for (int i = 0; i < values.length; i++) {
                values[i] = new IntPair(i, i);
            }
            IPriorityQueue<IntPair> heap = this.makeInstance();
            for (IntPair value : values) {
                heap.add(value);
            }

            while (heap.size() > 0){
                heap.removeMin();
            }

            assertThat(heap.size(), is(0));
        });
    }

    // Tests to address failed secret tests
    @Test
    void testContainsAfterRemove(){
        IntPair[] values = new IntPair[5];
        for (int i = 0; i < values.length; i++) {
            values[i] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        IntPair element = new IntPair(3, 3);
        heap.remove(element);
        assertThat(heap.contains(element), is(false));

        element = new IntPair(2, 2);
        heap.remove(element);
        assertThat(heap.contains(element), is(false));

        element = new IntPair(4, 4);
        heap.remove(element);
        assertThat(heap.contains(element), is(false));

        element = new IntPair(0, 0);
        heap.remove(element);
        assertThat(heap.contains(element), is(false));

        element = new IntPair(1, 1);
        heap.remove(element);
        assertThat(heap.contains(element), is(false));
    }

    @Test
    void testRemovePercolateDown(){
        IntPair[] values = new IntPair[10];
        for (int i = 0; i < 10; i++) {
            values[values.length-i-1] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        heap.remove(new IntPair(5, 5));
    }

    @Test
    void testRemoveMinStress(){
        IntPair[] values = new IntPair[100000];
        for (int i = 0; i < values.length; i++) {
            values[values.length-i-1] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        IntPair prev = heap.removeMin();
        while (heap.size() > 0) {
            IntPair cur = heap.removeMin();
            assertThat(prev.compareTo(cur) < 0, is(true));
            prev = cur;
        }
        assertThat(heap.size(), is(0));
    }

    boolean validInternalArray(Comparable<IntPair>[] array){
        for (int i=0; i < array.length; i++){
            IntPair element = (IntPair) array[i];
            int leastIndex = 4 * i + 1;
            for (int idx = leastIndex + 1; idx <= i + 4 - 1; idx++){
                if (array.length > idx && array[idx] != null && element != null && array[idx].compareTo(element) <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    void testRemoveRootConsecutive(){
        IntPair[] values = new IntPair[5000];
        for (int i = 0; i < values.length; i++) {
            values[values.length-i-1] = new IntPair(i, i);
        }
        IPriorityQueue<IntPair> heap = this.makeInstance();
        for (IntPair value : values) {
            heap.add(value);
        }

        while (heap.size() > 0) {
            IntPair element = heap.peekMin();
            heap.remove(element);
            assertThat(validInternalArray(getArray(heap)), is(true));

        }
        assertThat(heap.size(), is(0));
    }
}
