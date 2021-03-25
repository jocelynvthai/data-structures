/**
 * Create an array list.
 * @author Jocelyn Thai
 * @param <T>
 */
public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final double MAX_USAGE = 0.25;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }


    /** Resizes the array deque to capacity of param, newSize. */
    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }

        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /**  Another example
    public int wrap(int index) {
        return (index + this.items.length) % this.items.length;
    }
    */

    /** New index when array size increases by 1.
     */
    private int increaseI(int i) {
        return (i + 1) % items.length;
    }

    /** New index when array size decreases by 1.
     */
    private int decreaseI(int i) {
        if (i == 0) {
            return items.length - 1;
        }
        return i - 1;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            this.resize(items.length * 2);
        }

        items[nextFirst] = item;
        nextFirst = decreaseI(nextFirst);
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            this.resize(items.length * 2);
        }

        items[nextLast] = item;
        nextLast = increaseI(nextLast);
        size++;
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
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    /**
     * If size of items array is less that 25% of array length, resize items by half.
     */
    private void fixMemory() {
        double usage = Double.valueOf(size) / Double.valueOf(items.length);
        if (items.length >= 16 && usage < MAX_USAGE) {
            resize(items.length / 2);
        }
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        int firstI = increaseI(nextFirst);
        T item = items[firstI];
        items[firstI] = null;
        nextFirst = firstI;

        size--;
        fixMemory();

        return item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int lastI = decreaseI(nextLast);
        T item = items[lastI];
        items[lastI] = null;
        nextLast = lastI;

        size--;
        fixMemory();

        return item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        int firstI = increaseI(nextFirst);
        int actualI = (firstI + index) % items.length;
        return items[actualI];
    }
}
