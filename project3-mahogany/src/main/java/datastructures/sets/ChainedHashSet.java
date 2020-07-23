package datastructures.sets;

import datastructures.dictionaries.ChainedHashDictionary;
import datastructures.dictionaries.IDictionary;
import datastructures.dictionaries.KVPair;

import java.util.Iterator;

/**
 * @see ISet for more details on what each method is supposed to do.
 */
public class ChainedHashSet<T> implements ISet<T> {
    // This should be the only field you need
    private IDictionary<T, Boolean> map;

    public ChainedHashSet() {
        // No need to change this method
        this.map = new ChainedHashDictionary<>();
    }

    @Override
    public boolean add(T item) {
        if (this.map.put(item, true) != null){
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(T item) {
        if (map.remove(item) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator<>(this.map.iterator());
    }

    @Override
    public String toString() {
        return ISet.toString(this);
    }

    private static class SetIterator<T> implements Iterator<T> {
        // This should be the only field you need
        private Iterator<KVPair<T, Boolean>> iter;

        public SetIterator(Iterator<KVPair<T, Boolean>> iter) {
            // No need to change this method.
            this.iter = iter;
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public T next() {
            return iter.next().getKey();
        }
    }
}
