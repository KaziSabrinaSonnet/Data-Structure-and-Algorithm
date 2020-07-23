package datastructures.priorityqueues;

import datastructures.EmptyContainerException;
import datastructures.dictionaries.ChainedHashDictionary;
import datastructures.dictionaries.IDictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @see IPriorityQueue for details on what each method must do.
 */
public class ArrayHeapPriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T> {
    // See spec: you must implement a implement a 4-heap.
    private static final int NUM_CHILDREN = 4;

    /*
    You MUST use this field to store the contents of your heap.
    You may NOT rename this field or change its type: we will be inspecting it in our secret tests.
    */
    T[] heap;
    IDictionary<T, Integer> indices;
    private int capacity;
    private int size;

    // Feel free to add more fields and constants.
    private static final int INITIAL_CAPACITY = 10;

    public ArrayHeapPriorityQueue() {

        this.heap = makeArrayOfT(INITIAL_CAPACITY);
        this.indices = new ChainedHashDictionary<>();
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain elements of type `T`.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private T[] makeArrayOfT(int arraySize) {
        /*
        This helper method is basically the same one we gave you in `ArrayDictionary` and
        `ChainedHashDictionary`.

        As before, you do not need to understand how this method works, and should not modify it in
         any way.
        */
        return (T[]) (new Comparable[arraySize]);
    }

    @Override
    public T removeMin() {
        if (this.size == 0){
            throw new EmptyContainerException();
        }

        T out = heap[0];
        if (this.size == 1){
            heap[0] = null;
            this.indices.remove(out);
            this.size--;
        }
        else if (this.size > 1){
            this.indices.remove(out);
            heap[0] = heap[this.size - 1];
            this.indices.put(heap[0], 0);
            heap[this.size - 1] = null;
            this.size--;
            percolateDown(0);
        }
        return out;
    }

    @Override
    public T peekMin() {
        if (this.size == 0){
            throw new EmptyContainerException();
        }
        return heap[0];
    }

    @Override
    public void add(T item) {
       if (item == null) {
           throw new IllegalArgumentException();
       }
       if (this.indices.containsKey(item)) {
           throw new InvalidElementException();
       }
       if (this.size >= this.capacity) {
           this.capacity = 2 * this.capacity;
           T[] temporaryHeap = makeArrayOfT(this.capacity);
           System.arraycopy(this.heap, 0, temporaryHeap, 0, this.heap.length);
           /*
           for (int i = 0; i < this.size; i++){
               temporaryHeap[i] = this.heap[i];
           }
           */
           this.heap = temporaryHeap;
       }
       this.indices.put(item, this.size);
       this.heap[this.size] = item;
       this.size++;
       percolateUp(this.size-1);
    }

    @Override
    public boolean contains(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        return indices.containsKey(item);
    }

    @Override
    public void remove(T item) {
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (!this.indices.containsKey(item)){
            throw new InvalidElementException();
        }
        int index = indices.get(item);
        if (heap[index] == heap[this.size -1]){
            heap[this.size-1] = null;
            this.size--;
        }
        else {
            swap(this.size - 1, index);
            heap[this.size-1] = null;
            this.size--;
            percolate(index);
        }
        this.indices.remove(item);
    }

    @Override
    public void replace(T oldItem, T newItem) {
        if (oldItem == null || newItem == null){
            throw new IllegalArgumentException();
        }
        if (!this.indices.containsKey(oldItem) || this.indices.containsKey(newItem)){
            throw new InvalidElementException();
        }

        int index = indices.remove(oldItem);
        heap[index] = newItem;
        indices.put(newItem, index);
        percolate(index);
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * A method stub that you may replace with a helper method for percolating
     * upwards from a given index, if necessary.
     */
    /*
    private void percolateUp(int index) {
        int parentIndex = (index - 1) / NUM_CHILDREN;
        if (parentIndex >= 0 && heap[index].compareTo(heap[parentIndex]) < 0) {
            swap(index, parentIndex);
            percolateUp(parentIndex);
        }
    }
    */
    private void percolateUp(int index) {
        int parentIndex = (index - 1) / NUM_CHILDREN;
        while (parentIndex >= 0 && heap[index].compareTo(heap[parentIndex]) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / NUM_CHILDREN;
        }
    }

    /**
     * A method stub that you may replace with a helper method for percolating
     * downwards from a given index, if necessary.
     */
    /*
    private void percolateDown(int index) {
        if (index >= 0 && this.size > NUM_CHILDREN * index + 1){
            int leastIndex = compareChildren(index);
            if (heap[index].compareTo(heap[leastIndex]) > 0){
                swap(leastIndex, index);
                percolateDown(leastIndex);
            }
        }
    }
    */

    private void percolateDown(int index) {
        if (index < 0) {
            return;
        }
        while (this.size > ((NUM_CHILDREN * index) + 1)){
            int leastIndex = compareChildren(index);
            if (heap[index].compareTo(heap[leastIndex]) > 0){
                swap(leastIndex, index);
                index = leastIndex;
            }
            else {
                return;
            }
        }
    }

    private int compareChildren(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }

        int leastIndex = NUM_CHILDREN * index + 1;
        for (int idx = leastIndex + 1; idx <= leastIndex + NUM_CHILDREN - 1; idx++) {
            if (this.size <= idx) {
                break;
            }
            else if (this.heap[leastIndex].compareTo(this.heap[idx]) > 0) {
                leastIndex = idx;
            }
        }
        return leastIndex;
    }


    /**
     * A method stub that you may replace with a helper method for determining
     * which direction an index needs to percolate and percolating accordingly.
     */
    private void percolate(int index) {
        if (index < 0){
            throw new IllegalArgumentException();
        }
        if (index == 0){
           percolateDown(index);
        }
       else {
           int parentIndex = (index-1)/NUM_CHILDREN;
           if (heap[index].compareTo(heap[parentIndex]) < 0){
               percolateUp(index);
           }
           else if (heap[index].compareTo(heap[parentIndex]) > 0){
               percolateDown(index);
           }
       }
    }

    /**
     * A method stub that you may replace with a helper method for swapping
     * the elements at two indices in the `heap` array.
     */
    private void swap(int a, int b) {
        if (a < 0 || b < 0 || this.size <= a || this.size <= b){
            throw new IllegalArgumentException();
        }
        else {
            T temp = this.heap[a];
            this.heap[a] = this.heap[b];
            indices.put(this.heap[b], a);
            this.heap[b] = temp;
            indices.put(temp, b);
        }
    }

    @Override
    public String toString() {
        return IPriorityQueue.toString(this);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayHeapIterator<>(this.heap, this.size());
    }

    private static class ArrayHeapIterator<T extends Comparable<T>> implements Iterator<T> {
        private final T[] heap;
        private final int size;
        private int index;

        ArrayHeapIterator(T[] heap, int size) {
            this.heap = heap;
            this.size = size;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < this.size;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T output = heap[this.index];
            this.index++;
            return output;
        }
    }
}
