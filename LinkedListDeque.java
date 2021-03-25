/**
 * Create a linked list.
 * @author Jocelyn Thai
 * @param <T>
 */
public class LinkedListDeque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T i, Node p, Node n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node nNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = nNode;
        sentinel.next = nNode;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        Node nNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = nNode;
        sentinel.prev = nNode;
        size += 1;

    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node pointer = sentinel.next;
        while (pointer != sentinel) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T item = (T) sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T item = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return item;

    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        if (this.size() == 0) {
            return null;
        } else {
            Node pointer = sentinel.next;
            while (index != 0) {
                if (pointer.next == sentinel) {
                    return null;
                }
                pointer = pointer.next;
                index -= 1;
            }
            return pointer.item;
        }
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (this.size() == 0) {
            return null;
        }
        return helper(index, sentinel.next);
    }

    private T helper(int index, Node pointer) {
        if (index == 0) {
            return pointer.item;
        } else if (pointer.next == sentinel) {
            return null;
        } else {
            return helper(index - 1, pointer.next);
        }
    }

    

}














 