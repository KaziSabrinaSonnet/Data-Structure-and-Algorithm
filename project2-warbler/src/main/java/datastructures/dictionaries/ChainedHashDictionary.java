package datastructures.dictionaries;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @see IDictionary and the assignment page for more details on what each method should do
 */
public class ChainedHashDictionary<K, V> implements IDictionary<K, V> {
    // You'll need to define reasonable default values for each of the following three fields
    private static final double DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD = 1.0;
    private static final int DEFAULT_INITIAL_CHAIN_COUNT = 100;
    private static final int DEFAULT_INITIAL_CHAIN_CAPACITY = 10;

    private double resizingLoadFactorThreshold;
    private int initialChainCount;
    private int initialChainCapacity;
    private int size;

    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.

    Note: The field below intentionally omits the "private" keyword. By leaving off a specific
    access modifier like "public" or "private" it becomes package-private, which means anything in
    the same package can access it. Since our tests are in the same package, they will be able
    to test this property directly.
     */
    IDictionary<K, V>[] chains;


    // You're encouraged to add extra fields (and helper methods) though!

    public ChainedHashDictionary() {
        this(DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD, DEFAULT_INITIAL_CHAIN_COUNT, DEFAULT_INITIAL_CHAIN_CAPACITY);
    }

    public ChainedHashDictionary(double resizingLoadFactorThreshold, int initialChainCount, int chainInitialCapacity) {
        this.resizingLoadFactorThreshold = resizingLoadFactorThreshold;
        this.initialChainCount = initialChainCount;
        this.initialChainCapacity = chainInitialCapacity;
        this.chains = new IDictionary[initialChainCount];
        this.size = 0;
    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * `IDictionary<K, V>` objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private IDictionary<K, V>[] makeArrayOfChains(int arraySize) {
        /*
        Note: You do not need to modify this method. See `ArrayDictionary`'s `makeArrayOfPairs`
        method for more background on why we need this method.
        */
        return (IDictionary<K, V>[]) new IDictionary[arraySize];
    }

    @Override
    public V get(K key) {
        int bucketIndex = getHashCode(key, this.chains.length);
        if (chains[bucketIndex] != null) {
            return chains[bucketIndex].get(key);
        } else {
            throw new NoSuchKeyException();
        }
    }

    public int getHashCode(K key, int length) {
        int hashCode;
        if (key == null) {
            hashCode = 0;
        } else {
            int keyHash = key.hashCode();
            hashCode = Math.floorMod(keyHash, length);
        }
        return hashCode;
    }

    @Override
    public V put(K key, V value) {
        double loadFactor = (this.size() / this.chains.length);
        if (loadFactor >= this.resizingLoadFactorThreshold) {
            IDictionary<K, V>[] newchains = new IDictionary[2 * this.chains.length];
            for (int i = 0; i < this.chains.length; i++) {
                if (this.chains[i] == null) {
                    continue;
                }
                Iterator<KVPair<K, V>> iterator = this.chains[i].iterator();
                while (iterator.hasNext()) {
                    KVPair<K, V> pair = iterator.next();
                    int bucketIndex = getHashCode(pair.getKey(), newchains.length);
                    if (newchains[bucketIndex] == null) {
                        newchains[bucketIndex] = new ArrayDictionary<K, V>(this.initialChainCapacity);

                    }
                    newchains[bucketIndex].put(pair.getKey(), pair.getValue());
                }
            }
            this.chains = newchains;
        }
        int bucketIndex = getHashCode(key, this.chains.length);
        if (chains[bucketIndex] == null) {
            chains[bucketIndex] = new ArrayDictionary<K, V>(this.initialChainCapacity);
        }
        if (!chains[bucketIndex].containsKey(key)) {
            this.size++;
        }
        return chains[bucketIndex].put(key, value);
    }

    @Override
    public V remove(K key) {
        if (!this.containsKey(key)) {
            return null;
        } else {
            int bucketIndex = getHashCode(key, this.chains.length);
            this.size--;
            V prevVal = chains[bucketIndex].remove(key);
            if (chains[bucketIndex].size() == 0) {
                chains[bucketIndex] = null;
            }
            return prevVal;
        }
    }

    @Override
    public boolean containsKey(K key) {
        int bucketIndex = getHashCode(key, this.chains.length);
        if (chains[bucketIndex] == null) {
            return false;
        } else {
            return chains[bucketIndex].containsKey(key);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // Note: you do not need to change this method
        return new ChainedIterator<>(this.chains);
    }

    @Override
    public String toString() {
        return super.toString();

        /*
        After you've implemented the iterator, comment out the line above and uncomment the line
        below to get a better string representation for objects in assertion errors and in the
        debugger.
        */

        //return IDictionary.toString(this);
    }

    /*
    See the assignment webpage for tips and restrictions on implementing this iterator.
     */
    private static class ChainedIterator<K, V> implements Iterator<KVPair<K, V>> {
        private IDictionary<K, V>[] chains;
        private int currentBucket;
        private Iterator<KVPair<K, V>> iterator;

        // You may add more fields and constructor parameters


        public ChainedIterator(IDictionary<K, V>[] chains) {
            this.chains = chains;
            this.currentBucket = getNextValidBucket(0);
            if (this.currentBucket < this.chains.length) {
                this.iterator = this.chains[this.currentBucket].iterator();
            }
        }

        public int getNextValidBucket(int currentIdx) {
            while (currentIdx < this.chains.length && chains[currentIdx] == null) {
                currentIdx++;
            }
            return currentIdx;
        }

        @Override
        public boolean hasNext() {
            if (this.currentBucket >= this.chains.length) {
                return false;

            } else if (iterator.hasNext()) {
                return true;
            } else {
                return getNextValidBucket(this.currentBucket + 1) < this.chains.length;
            }
        }

        @Override
        public KVPair<K, V> next() {
            if (this.hasNext()) {
                if (this.iterator.hasNext()) {
                    return this.iterator.next();
                } else {
                    this.currentBucket = getNextValidBucket(this.currentBucket + 1);
                    this.iterator = chains[currentBucket].iterator();
                    return this.iterator.next();
                }
            } else {
                throw new NoSuchElementException();
            }

        }

    }
}
