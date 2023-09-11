/*  Student information for assignment:
 *
 *  On MY honor, Mohammad Kashif,
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used): Mohammad Kashif
 *  UTEID: mnk665
 *  email address: mohammadnkashif@utexas.edu
 *  TA name: Pranav Chandupatla
 *
 */

import java.util.Iterator;
import java.util.ArrayList;

public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {
    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * Create a copy of other that is sorted.<br>
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
        myCon = new ArrayList<>();
        for (E current: other) {
            add(current);
        }
        mergeSort(myCon);
    }

    /**
     * perform a merge sort on the elements of data
     * @param data data != null, all elements of data
     * are the same data type.
     * MergeSort and helper methods taken from lecture slides and adapted to ArrayList container.
     */
    private void mergeSort(ArrayList<E> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null.");
        }
        ArrayList<E> temp = new ArrayList<>(data.size());
        sort(data, temp, 0, data.size() - 1);
    }

    /**
     * Recursive sorting method for Merge Sort.
     * @param data data != null, all elements of data
     * are the same data type
     * @param temp Arraylist for inserting data points at correct sorted positions.
     * @param low Lower limit of sort.
     * @param high Upper limit of sort.
     */
    private void sort(ArrayList<E> data, ArrayList<E> temp, int low, int high) {
        if(low < high) {
            int center = (low + high) / 2;
            sort(data, temp, low, center);
            sort(data, temp, center + 1, high);
            merge(data, temp, low, center + 1, high);
        }
    }

    /**
     * Merge method incorporated in merge sort.
     * @param data data != null, all elements of data
     * are the same data type
     * @param temp Arraylist for inserting data points at correct sorted positions.
     * @param leftPos Starting position of data points to be merged.
     * @param rightPos End point for data points to be merged.
     * @param rightEnd End of sorting range.
     */
    private void merge(ArrayList<E> data, ArrayList<E> temp,
                       int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        //main loop
        while(leftPos <= leftEnd && rightPos <= rightEnd){
            if( data.get(leftPos).compareTo(data.get(rightPos)) <= 0) {
                temp.add(tempPos, data.get(leftPos));
                leftPos++;
            } else {
                temp.add(tempPos, data.get(rightPos));
                rightPos++;
            }
            tempPos++;
        }
        //copy rest of left half
        while (leftPos <= leftEnd) {
            temp.add(tempPos, data.get(leftPos));
            tempPos++;
            leftPos++;
        }
        //copy rest of right half
        while (rightPos <= rightEnd) {
            temp.add(tempPos, data.get(rightPos));
            tempPos++;
            rightPos++;
        }
        //Copy temp back into data
        for (int i = 0; i < numElements; i++, rightEnd--)
            data.set(rightEnd, temp.get(rightEnd));
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        if (myCon.size() == 0) {
            throw new IllegalArgumentException("Set cannot be empty");
        }
        return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    public E max() {
        if (myCon.size() == 0) {
            throw new IllegalArgumentException("Set cannot be empty");
        }
        return myCon.get(myCon.size() - 1);
    }

    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear() {
        myCon = new ArrayList<>();
    }

    /**
     * Determine if item is in this set.
     * <br>pre: item != null
     * @param item element whose presence is being tested.
     * Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null.");
        }
        return binarySearch(myCon, item, 0, myCon.size() - 1) >= 0;
    }

    /**
     * Add an item to this set, at correct position based on sorting.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     * false otherwise.
     */
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null.");
        }
        int pos = binarySearch(myCon, item, 0, myCon.size() - 1);
        if (pos >= 0) {
            return false;
        }
        else {
            myCon.add(-(pos + 1), item);
            return true;
        }
    }

    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     * false otherwise
     */
    public boolean remove(E item) {
        int pos = binarySearch(myCon, item, 0, myCon.size() - 1);
        if (pos >= 0) {
            myCon.remove(pos);
            return true;
        }
        return false;
    }

    /**
     * Recursive binary search method. Returns either position of target object or
     * expected position based on least to greatest element sorting.
     * @param data data != null, all elements of data
     * are the same data type
     * @param target The target element we are searching for.
     * @param low Lower limit of search.
     * @param high Upper limit of search.
     */
    private int binarySearch(ArrayList<E> data, E target,
                            int low, int high) {
        int mid = low + ((high - low) / 2);
        if(low <= high){
            if(data.get(mid).equals(target))
                return mid;
            else if(data.get(mid).compareTo(target) > 0)
                return binarySearch(data, target, low, mid - 1);
            else
                return binarySearch(data, target, mid + 1, high);
        }
        return (mid * -1) -1;
    }


    /**
     * Create a new set that is the difference of this set and otherSet.
     * Return an ISet of elements that are in this Set but not in otherSet.
     * Also called the relative complement.
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z]
     * then A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W].
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Second set can't be null.");
        }
        ISet<E> result = new UnsortedSet<>();
        for (E temp : myCon) {
            if (!otherSet.contains(temp)) {
                result.add(temp);
            }
        }
        return result;
    }

    /**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Second set can't be null.");
        }
        ISet<E> union = new SortedSet<>();
        for (E element: this) {
            union.add(element);
        }
        for (E element: otherSet) {
            union.add(element);
        }
        return union;
    }

    /**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set
     * and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    public ISet<E> intersection(ISet<E> otherSet) {
        Iterator<E> first = this.iterator();
        Iterator<E> second;
        if (!(otherSet instanceof SortedSet)) {
            ISet<E> secondSet = new SortedSet<>(otherSet);
            second = secondSet.iterator();
        }
        else {
            second = otherSet.iterator();
        }
        ISet<E> intersection = new SortedSet<>();
        intersectionHelper(intersection, first, second);
        return intersection;
    }

    /**
     * Helper method for Intersection.
     * Uses while loop and iterators to iterate through
     * both sorted sets and find all points of intersection.
     * @param intersection Storage for all found intersections.
     * @param first Iterator for first set.
     * @param second Iterator for second set.
     */
    private void intersectionHelper(ISet<E> intersection, Iterator<E> first,
                                    Iterator<E> second) {
        E temp1 = null;
        E temp2 = null;
        while (first.hasNext() && second.hasNext()) {
            if (temp1 == null) {
                temp1 = first.next();
            }
            if (temp2 == null) {
                temp2 = second.next();
            }
            int difference = temp1.compareTo(temp2);
            if (difference < 0) {
                temp1 = null;
            } else if (difference > 0) {
                temp2 = null;
            } else {
                intersection.add(temp1);
                temp1 = null;
                temp2 = null;
            }
        }
    }

    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public int size() {
        return myCon.size();
    }

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set
     * @return true if other is a Set and has the same elements as this set
     */
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ISet<?>)) {
            return false;
        }
        SortedSet<?> second = (SortedSet<?>) other;
        if (this.size() != second.size()) {
            return false;
        }
        int index = 0;
        for (Object item2: second) {
            if (!myCon.get(index).equals(item2)) {
                return false;
            }
            index++;
        }
        return true;
    }

    /**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return myCon.iterator();
    }
}
