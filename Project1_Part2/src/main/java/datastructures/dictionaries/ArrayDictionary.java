package datastructures.dictionaries;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @see IDictionary
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.

    Note: The field below intentionally omits the "private" keyword. By leaving off a specific
    access modifier like "public" or "private" it   becomes package-private, which means anything in
    the same package can access it. Since our tests are in the same package, they will be able
    to test this property directly.
     */
    Pair<K, V>[] pairs;
    int size;

    // You may add extra fields or helper methods though!

    public ArrayDictionary() {
        this.pairs = new Pair[5];
        this.size = 0;
    }

    /**
     * This method will return a new, empty array of the given size that can contain `Pair<K, V>`
     * objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        /*
        It turns out that creating arrays of generic objects in Java is complicated due to something
        known as "type erasure."

        We've given you this helper method to help simplify this part of your assignment. Use this
        helper method as appropriate when implementing the rest of this class.

        You are not required to understand how this method works, what type erasure is, or how
         arrays and generics interact. Do not modify this method in any way.
        */
        return (Pair<K, V>[]) (new Pair[arraySize]);
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < this.size; i++) {
            if (pairs[i].key != null && pairs[i].key.equals(key)) {
                return pairs[i].value;
            }
            if (pairs[i].key == null && key == null) {
                return pairs[i].value;
            }
        }

        throw new NoSuchKeyException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        for (int i = 0; i < this.size; i++) {
            // System.out.println(pairs[i].key);
            if (pairs[i].key != null && pairs[i].key.equals(key)) {
                V preValue = pairs[i].value;
                pairs[i].value = value;

                return preValue;
            }
            if (pairs[i].key == null && key == null) {
                V preValue = pairs[i].value;
                pairs[i].value = value;

                return preValue;
            }
        }

        if (this.size < this.pairs.length) {
            pairs[this.size] = new Pair<K, V>(key, value);
            this.size++;
        } else {
            // this.pairs = Arrays.copyOf(pairs, 2 * pairs.length);
            Pair<K, V>[] newPairs = new Pair[2 * this.size];
            for (int i = 0; i < this.size; i++) {
                newPairs[i] = pairs[i];
            }

            this.pairs = newPairs;
            pairs[this.size] = new Pair<K, V>(key, value);
            this.size++;
        }

        return null;
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < this.size; i++) {
            if ((pairs[i].key != null && pairs[i].key.equals(key)) ||
                    (pairs[i].key == null && key == null)) {
                V old = pairs[i].value;
                pairs[i].key = pairs[this.size - 1].key;
                pairs[i].value = pairs[this.size - 1].value;
                pairs[this.size - 1].key = null;
                pairs[this.size - 1].value = null;
                this.size--;

                return old;
            }
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < this.size; i++) {
            if ((pairs[i].key != null && pairs[i].key.equals(key)) ||
                    (pairs[i].key == null && key == null)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new ArrayDictionaryIterator<K, V>(this.pairs, this.size);
    }

    @Override
    public String toString() {
        // return super.toString();

        /*
        After you've implemented the iterator, comment out the line above and uncomment the line
        below to get a better string representation for objects in assertion errors and in the
        debugger.
        */

        return IDictionary.toString(this);
    }

    public V getOrDefault(K key, V defaultValue) {
        for (int i = 0; i < this.size; i++) {
            if (pairs[i].key != null && pairs[i].key.equals(key)) {
                return pairs[i].value;
            }
            if (pairs[i].key == null && key == null) {
                return pairs[i].value;
            }
        }

        return defaultValue;
    }

    private static class Pair<K, V> {
        public K key;
        public V value;

        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s=%s", this.key, this.value);
        }
    }

    private static class ArrayDictionaryIterator<K, V> implements Iterator<KVPair<K, V>> {
        private int current;
        private Pair<K, V>[] pairs;
        private int size;

        public ArrayDictionaryIterator(Pair<K, V>[] pairs, int size) {
            this.pairs = pairs;
            this.current = 0;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return (current < size);
        }

        @Override
        public KVPair<K, V> next() {
            if (hasNext()) {
                KVPair<K, V> output = new KVPair<>(this.pairs[current].key, this.pairs[current].value);
                current++;
                return output;
            }

            throw new NoSuchElementException();
        }
    }
}
