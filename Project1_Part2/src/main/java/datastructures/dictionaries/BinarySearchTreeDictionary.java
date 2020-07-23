package datastructures.dictionaries;

import java.util.Iterator;

public class BinarySearchTreeDictionary<K extends Comparable<K>, V> implements IDictionary<K, V> {
    private TreeNode<K, V> overallRoot;
    private int size;

    public BinarySearchTreeDictionary() {
        overallRoot = null;
        size = 0;
    }

    public V put(K key, V value) {
        TreeNode<K, V> oldPair = new TreeNode<>(null, null);
        overallRoot = put(overallRoot, new TreeNode<>(key, value), oldPair);
        return oldPair.value;
    }

    private TreeNode<K, V> put(TreeNode<K, V> current, TreeNode<K, V> newPair, TreeNode<K, V> oldPair) {
        if (current == null) {
            size++;
            return newPair;
        } else {
            int result = newPair.key.compareTo(current.key);
            if (result == 0) {
                oldPair.value = current.value;
                current.value = newPair.value;
            } else if (result < 0) {
                current.left = put(current.left, newPair, oldPair);
            } else {
                current.right = put(current.right, newPair, oldPair);
            }
                return current;
        }
    }

    public V get(K key) {
        TreeNode<K, V> result =  getNode(overallRoot, key);
        if (result == null) {
            throw new NoSuchKeyException();
        }
        return result.value;
    }

    private TreeNode<K, V> getNode(TreeNode<K, V> current, K key) {
        if (current == null) {
            return null;
        } else {
            int result = key.compareTo(current.key);
            if (result == 0) {
                return current;
            } else if (result < 0) {
                return getNode(current.left, key);
            } else {
                return getNode(current.right, key);
            }
        }
    }

    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(K key) {
        return getNode(overallRoot, key) != null;
    }

    public int size() {
        return size;
    }

    public Iterator<KVPair<K, V>> iterator() {
        throw new UnsupportedOperationException();
    }

    static class TreeNode<K, V> {
        final K key;

        V value;
        TreeNode<K, V> left;
        TreeNode<K, V> right;

        TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args){
        IDictionary<Long, Long> dictionary = new BinarySearchTreeDictionary<>();
        for (long i = 0L; i < 10; i++) {
            dictionary.put(i, i);
        }

    }
}
