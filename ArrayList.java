import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author Kiavosh Peynabard
 * @version 1.0
 * @userid kpeynabard3
 * @GTID 903353136
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index Out of Bound");
        }
        if (data == null) {
            throw new IllegalArgumentException("Invalid Argument");
        }
        if (size == backingArray.length) {
            expand();
        }
        if (size == index) {
            addToBack(data);
            return;
        } else if (index == 0) {
            addToFront(data);
            return;
        } else {
            shiftHelper(1, index);
            backingArray[index] = data;
        }
        size++;
    }
    /**
     * Helper method which expands the backing array
     *
     * Array will be expanded by adding INITIAL_CAPACITY to its current length.
     *
     */
    private void expand() {
        T[] largerBackingArray = (T[]) new Object[backingArray.length + INITIAL_CAPACITY];
        int currInd = 0;
        while (currInd < backingArray.length) {
            largerBackingArray[currInd] = backingArray[currInd];
            currInd++;
        }
        backingArray = largerBackingArray;
    }

    /**
     * Helper Method to shift the elements
     *
     * it either shifts element to right or left
     *
     * @param flag is a determinant to either shift to right or to left
     * @param index is the index that shift will start from.
     */
    private void shiftHelper(int flag, int index) {
        if (flag > 0) {

            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }

        } else if (flag < 0) {

            int indexHolder = index;
            while (backingArray[indexHolder + 1] != null) {
                backingArray[indexHolder] = backingArray[indexHolder + 1];
                indexHolder++;
            }
            backingArray[size - 1] = null;

        }
    }
    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Argument");
        }
        if (size == backingArray.length) {
            expand();
        }
        shiftHelper(1, 0);
        backingArray[0] = data;
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Argument");
        }
        if (size == backingArray.length) {
            expand();
        }
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out of Bound");
        }
        if (index == size - 1) {
            return removeFromBack();
        } else if (index == 0) {
            return removeFromFront();
        } else {
            T element = backingArray[index];
            shiftHelper(-1, index);
            size--;
            return element;
        }
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Nu Such Element");
        }
        T element = backingArray[0];
        shiftHelper(-1, 0);
        size--;
        return element;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("No Such Element");
        }
        T element = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return element;
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Out of Bound");
        }
        return backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

}
