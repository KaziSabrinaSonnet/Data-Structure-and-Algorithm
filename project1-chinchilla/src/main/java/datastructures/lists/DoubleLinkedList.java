package datastructures.lists;

import datastructures.EmptyContainerException;
// import misc.exceptions.NotYetImplementedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Note: For more info on the expected behavior of your methods:
 * @see IList
 * (You should be able to control/command+click "IList" above to open the file from IntelliJ.)
 */
public class DoubleLinkedList<T> implements IList<T> {
    /*
    Warning:
    You may not rename these fields or change their types.
    We will be inspecting these in our secret tests.
    You also may not add any additional fields.

    Note: The fields below intentionally omit the "private" keyword. By leaving off a specific
    access modifier like "public" or "private" they become package-private, which means anything in
    the same package can access them. Since our tests are in the same package, they will be able
    to test these properties directly.
     */
    Node<T> front;
    Node<T> back;
    int size;

    public DoubleLinkedList() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    @Override
    public void add(T item) {
        if (this.back != null) {
            this.back.next = new Node<T>(item);
            this.back.next.prev = this.back;
            this.back = this.back.next;
        }else {
            this.front = new Node<T>(item);
            this.back = this.front;
        }

        this.size++;
    }

    @Override
    public T remove() {
        if (this.back == null){
            throw new EmptyContainerException();
        }

        T out = this.back.data;

        if (this.back.prev == null){
            // contain only one node
            this.back = null;
            this.front = null;
            this.size--;
        }else {
            this.back = this.back.prev;
            this.back.next = null;
            this.size--;
        }

        return out;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = this.front;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }

        return current.data;
    }

    @Override
    public T set(int index, T item) {
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }

        Node<T> replace = new Node<T>(item);
        T out = front.data;

        if (index == 0){
            if (this.size == 1) {
                this.back = replace;
            }else {
                replace.next = this.front.next;
                this.front.next.prev = replace;
            }
            this.front = replace;
        }else if (index == this.size - 1){
            out = this.back.data;
            replace.prev = this.back.prev;
            this.back.prev.next = replace;
            this.back = replace;
        }else {
            Node<T> current = this.front;
            if (index < this.size / 2) {
                int i = 0;
                while (i < index) {
                    current = current.next;
                    i++;
                }
            }else {
                current = this.back;
                int i = this.size - 1;
                while (i > index) {
                    current = current.prev;
                    i--;
                }
            }

            out = current.data;
            replace.next = current.next;
            replace.prev = current.prev;
            current.prev.next = replace;
            current.next.prev = replace;
        }

        return out;
    }

    @Override
    public void insert(int index, T item) {
        if (index < 0 || index > this.size){
            throw new IndexOutOfBoundsException();
        }

        if (this.size == 0) {
            this.front = new Node<T>(item);
            this.back = this.front;
        }else if (index == 0){
            Node<T> ins = new Node<T>(item);
            this.front.prev = ins;
            ins.next = this.front;
            this.front = ins;
        }else if (index == this.size){
            add(item);
            this.size--;
        }else {
            Node<T> ins = new Node<T>(item);
            Node<T> current = this.front;

            if (index < this.size / 2) {
                int i = 0;
                while (i < index - 1) {
                    current = current.next;
                    i++;
                }
            }else {
                current = this.back;
                int i = this.size - 1;
                while (i > index - 1){
                    current = current.prev;
                    i--;
                }
            }

            ins.prev = current;
            ins.next = current.next;
            current.next = ins;
            ins.next.prev = ins;
        }

        this.size++;
    }

    @Override
    public T delete(int index) {
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }

        T del = this.front.data;

        if (this.size == 1) {
            this.front = null;
            this.back = null;
        }else if (index == 0) {
            this.front = this.front.next;
            this.front.prev = null;
        }else if (index == this.size - 1){
            this.back = this.back.prev;
            this.back.next = null;
        }else {
            Node<T> current = this.front;
            if (index < this.size / 2) {
                int i = 0;
                while (i < index - 1) {
                    current = current.next;
                    i++;
                }
            }else {
                current = this.back;
                int i = this.size - 1;
                while (i > index - 1) {
                    current = current.prev;
                    i--;
                }
            }

            current.next = current.next.next;
            current.next.prev = current;
        }

        this.size--;

        return del;
    }

    @Override
    public int indexOf(T item) {
        int index = 0;
        Node<T> current = this.front;

        while (current != null) {
            if (current.data != null && current.data.equals(item)) {
                return index;
            }else if (current.data == null && item == null){
                return index;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(T other) {
        Node<T> current = this.front;
        while (current != null) {
            if (current.data != null && current.data.equals(other)) {
                return true;
            }else if (current.data == null && other == null) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public String toString() {
        // return super.toString();

        /*
        After you've implemented the iterator, comment out the line above and uncomment the line
        below to get a better string representation for objects in assertion errors and in the
        debugger.
        */

        return IList.toString(this);
    }

    @Override
    public Iterator<T> iterator() {
        /*
        Note: we have provided a part of the implementation of an iterator for you. You should
        complete the methods stubs in the DoubleLinkedListIterator inner class at the bottom of
        this file. You do not need to change this method.
        */
        return new DoubleLinkedListIterator<>(this.front);
    }

    static class Node<E> {
        // You may not change the fields in this class or add any new fields.
        final E data;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        Node(E data) {
            this(null, data, null);
        }

        // Feel free to add additional constructors or methods to this class.
    }

    private static class DoubleLinkedListIterator<T> implements Iterator<T> {
        // You should not need to change this field, or add any new fields.
        private Node<T> next;

        public DoubleLinkedListIterator(Node<T> front) {
            // You do not need to make any changes to this constructor.
            this.next = front;
        }

        /**
         * Returns `true` if the iterator still has elements to look at;
         * returns `false` otherwise.
         */
        public boolean hasNext() {
            return (this.next != null);
        }

        /**
         * Returns the next item in the iteration and internally updates the
         * iterator to advance one element forward.
         *
         * @throws NoSuchElementException if we have reached the end of the iteration and
         *         there are no more elements to look at.
         */
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            T out = this.next.data;
            this.next = this.next.next;
            return out;
        }
    }
}
