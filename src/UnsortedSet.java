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

public class UnsortedSet<E> extends AbstractSet<E> {
    private ArrayList<E> myCon;

    /**
     * create an empty UnsortedSet
     */
    public UnsortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * Create a copy of other that is unsorted.<br>
     * @param other != null
     */
    public UnsortedSet(ISet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other can't be a null set.");
        }
        myCon = new ArrayList<>();
        for (E current: other) {
            myCon.add(current);
        }
    }

    /**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     * false otherwise.
     */
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null.");
        }
        if (myCon.contains(item)) {
            return false;
        }
        else {
            myCon.add(item);
            return true;
        }
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
        if (otherSet == null) {
            throw new IllegalArgumentException("Second set can't be null.");
        }
        ISet<E> result = new UnsortedSet<>();
        for (E temp : myCon) {
            if (otherSet.contains(temp)) {
                result.add(temp);
            }
        }
        return result;
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
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return myCon.iterator();
    }


}
