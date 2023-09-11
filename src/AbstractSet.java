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

public abstract class AbstractSet<E> implements ISet<E> {

    /**
     * A union operation. Add all items of otherSet that
     * are not already present in this set to this set.
     * @param otherSet != null
     * @return true if this set changed as a result of this operation,
     * false otherwise.
     */
    public boolean addAll(ISet<E> otherSet) {
        for (E temp: otherSet) {
            if (!this.add(temp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear() {
        Iterator<E> curr = this.iterator();
        while (curr.hasNext()){
            curr.next();
            curr.remove();
        }
    }

    /**
     * Determine if item is in this set.
     * <br>pre: item != null
     * @param item element whose presence is being tested.
     * Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {
        for (E temp : this) {
            if (temp.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet,
     * false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Second set can't be null.");
        }
        for (E temp: otherSet) {
            if (!this.contains(temp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     * false otherwise
     */
    public boolean remove(E item) {
        Iterator<E> curr = this.iterator();
        while (curr.hasNext()) {
            E temp = curr.next();
            if (temp.equals(item)) {
                curr.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public int size() {
        Iterator<E> iterator = this.iterator();
        int size = 0;
        while (iterator.hasNext()) {
            size++;
            iterator.next();
        }
        return size;
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
        if (other instanceof ISet<?>) {
            for (Object temp1: this) {
                boolean contained = false;
                for (Object temp2: (ISet<?>)other) {
                    if (!contained && temp1.equals(temp2)) {
                        contained = true;
                    }
                }
                if (!contained) {
                    return false;
                }
            }
            return true;
        }
        return false;
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
        ISet<E> result = this.difference(otherSet);
        result.addAll(otherSet);
        return result;
    }

    /**
     * Return a String version of this set. 
     * Format is (e1, e2, ... en)
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }
}
