
/*
 * Student information for assignment:
 *
 * Number of slip days used:
 * Student 1 (Student whose turnin account is being used)
 *  UTEID:
 *  email address:
 *  Grader name:
 *
 * Student 2
 *  UTEID:
 *  email address:
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;

/* Test Results (Factors):
                                    Sorted Set:
 * | Title                         | Size (kb) | Total Words  | Distinct Words | Time (sec)
 * | My HS Yearbook Statement      | 15 kb     | 450 words    | 435 words   | 0.0037752 seconds
 * | My Survey Data Analysis       | 23.5 x    | 26.4x        | 25 x        | 13.4 x
 * | Pride and Prejudice           | 2.1 x     | 11 x         | 1.4 x       | 3 x
 * | Complete Works of Shakespeare | 7.4 x     | 7.4 x        | 5.1 x       | 9.1 x
 *
                                    Unsorted Set:
 * | Title                         | Size (kb) | Total Words  | Distinct Words | Time (sec)
 * | My HS Yearbook Statement      | 15 kb     | 450 words    | 435 words   | 0.0065897 seconds
 * | My Survey Data Analysis       | 23.5 x    | 26.4x        | 25 x        | 54 x
 * | Pride and Prejudice           | 2.1 x     | 11 x         | 1.4 x       | 4.9 x
 * | Complete Works of Shakespeare | 7.4 x     | 7.4 x        | 5.1 x       | 30.2 x
 *
                                    Hash Set:
 * | Title                         | Size (kb) | Total Words  | Distinct Words | Time (sec)
 * | My HS Yearbook Statement      | 15 kb     | 450 words    | 435 words   | 0.0061759 seconds
 * | My Survey Data Analysis       | 23.5 x    | 26.4x        | 25 x        | 4.4 x
 * | Pride and Prejudice           | 2.1 x     | 11 x         | 1.4 x       | 4.5 x
 * | Complete Works of Shakespeare | 7.4 x     | 7.4 x        | 5.1 x       | 5.6 x

                                    Tree Set:
 * | Title                         | Size (kb) | Total Words  | Distinct Words | Time (sec)
 * | My HS Yearbook Statement      | 15 kb     | 450 words    | 435 words   | 0.0056866 seconds
 * | My Survey Data Analysis       | 23.5 x    | 26.4x        | 25 x        | 6.3 x
 * | Pride and Prejudice           | 2.1 x     | 11 x         | 1.4 x       | 3.7 x
 * | Complete Works of Shakespeare | 7.4 x     | 7.4 x        | 5.1 x       | 6.9 x
 *
 *
 */

/* Test Results (Numbers):
                                    Sorted Set:
 * | Title                         | Size (kb) | Total Words  | Distinct Words | Time (sec)
 * | My HS Yearbook Statement      | 15 kb     | 450 words    | 435 words   | 0.0037752 seconds
 * | My Survey Data Analysis       | 353 kb    | 11874 words  | 10859 words | 0.0504961 seconds
 * | Pride and Prejudice           | 755 kb    | 130412 words | 14706 words | 0.1503623 seconds
 * | Complete Works of Shakespeare | 5561 kb   | 963274 words | 75576 words | 1.3626336 seconds
 *
                                    Unsorted Set:
 * | Title                         | Size (kb) | Total Words  | Distinct Words | Time (sec)
 * | My HS Yearbook Statement      | 15 kb     | 450 words    | 435 words   | 0.0065897 seconds
 * | My Survey Data Analysis       | 353 kb    | 11874 words  | 10859 words | 0.3561726 seconds
 * | Pride and Prejudice           | 755 kb    | 130412 words | 14706 words | 1.735618 seconds
 * | Complete Works of Shakespeare | 5561 kb   | 963274 words | 75576 words | 52.3339305 seconds
 *
                                    Hash Set:
 * | Title                         | Size (kb) | Total Words  | Distinct Words | Time (sec)
 * | My HS Yearbook Statement      | 15 kb     | 450 words    | 435 words   | 0.0061759 seconds
 * | My Survey Data Analysis       | 353 kb    | 11874 words  | 10859 words | 0.0273668 seconds
 * | Pride and Prejudice           | 755 kb    | 130412 words | 14706 words | 0.1233114 seconds
 * | Complete Works of Shakespeare | 5561 kb   | 963274 words | 75576 words | 0.6948847 seconds

                                    Tree Set:
 * | Title                         | Size (kb) | Total Words  | Distinct Words | Time (sec)
 * | My HS Yearbook Statement      | 15 kb     | 450 words    | 435 words   | 0.0056866 seconds
 * | My Survey Data Analysis       | 353 kb    | 11874 words  | 10859 words | 0.03565 seconds
 * | Pride and Prejudice           | 755 kb    | 130412 words | 14706 words | 0.1329369 seconds
 * | Complete Works of Shakespeare | 5561 kb   | 963274 words | 75576 words | 0.9165587 seconds
 *
 *
 */

/*
 * 1. Based on the timing data, I think the big O of the processText methods are:
 * Sorted Set: O(N log M)
 * Unsorted Set: O(N)
 * Hash Set: O(N)
 * Tree Set: O(N log M)
 * 2. The order for the add method of each type of set is:
 * Sorted Set: O(log M)
 * Unsorted Set: O(1)
 * Hash Set: O(1)
 * Tree Set: O(log M)
 * 3. The main difference between the printing of HashSet and TreeSet is the order of the elements.
 * HashSet doesn't have any particular order, while TreeSet orders the elements
 * some natural ordering or sorting methodology provided. Additionally,
 * TreeSet is slower than HashSet due to this ordering of elements.
 *
 * CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
     * Not implementing these methods in AbstractSet allows subclasses to selectively implement
     * only the methods relevant to the subclasses' function/usage.
     * It also allows for more efficient implementation in subclasses. If more efficient
     * implementation is always possible. then those methods in AbstractSet lose their value.
 */

public class SetTester {

    public static void main(String[] args) {
        studentTests();
    }
    private static void studentTests() {

        // Test 1: Unsorted Set Empty Constructor
        ISet<String> testSet = new UnsortedSet<>();
        if (testSet.size() == 0 && !testSet.contains("a")) {
            System.out.println("Passed Test 1: Unsorted Set empty constructor.");
        }
        else {
            System.out.println("Failed Test 1: Unsorted Set empty constructor.");
        }

        testSet.add("A");
        testSet.add("Z");

        // Test 2: Unsorted Set ISet parameter constructor.
        ISet<String> testSet2 = new UnsortedSet<>(testSet);
        if (testSet2.size() == 2 && testSet2.contains("Z") && testSet2.contains("A")) {
            System.out.println("Passed Test 2: Unsorted Set ISet parameter constructor.");
        }
        else {
            System.out.println("Failed Test 2: Unsorted Set ISet parameter constructor.");
        }

        // Test 3: Unsorted Set Add Method
        testSet.add("G");
        testSet.add("H");
        if (testSet.contains("A") && testSet.contains("G") &&
                testSet.contains("H") && testSet.contains("Z")) {
            System.out.println("Passed Test 3: Unsorted Set add method.");
        }
        else {
            System.out.println("Failed Test 3: Unsorted Set add method.");
        }

        // Test 4: Unsorted Set Clear Method
        testSet.clear();
        if (!testSet.contains("A") && !testSet.contains("G") &&
                !testSet.contains("H") && !testSet.contains("Z")) {
            System.out.println("Passed Test 4: Unsorted Set clear method.");
        }
        else {
            System.out.println("Failed Test 4: Unsorted Set clear method.");
        }

        testSet.add("A");
        testSet.add("Z");
        testSet.add("G");
        testSet.add("H");

        // Test 5: Unsorted Set Difference Method
        ISet<String> expected = new UnsortedSet<>();
        expected.add("G");
        expected.add("H");
        if (testSet.difference(testSet2).equals(expected)) {
            System.out.println("Passed Test 5: Unsorted Set difference method.");
        }
        else {
            System.out.println("Failed Test 5: Unsorted Set difference method.");
        }

        // Test 6: Unsorted Set Intersection Method
        expected.clear();
        expected.add("A");
        expected.add("Z");
        if (testSet.intersection(testSet2).equals(expected)) {
            System.out.println("Passed Test 6: Unsorted Set intersection method.");
        }
        else {
            System.out.println("Failed Test 6: Unsorted Set intersection method.");
        }

        // Test 7: Unsorted Set Size Method.
        if (testSet.size() == 4 && testSet2.size() == 2) {
            System.out.println("Passed Test 7: Unsorted Set size method.");
        }
        else {
            System.out.println("Failed Test 7: Unsorted Set size method.");
        }

        // Test 8: Unsorted Set Iterator
        Iterator testIterator = testSet.iterator();
        if (testIterator.next().equals("A") && testIterator.hasNext()) {
            System.out.println("Passed Test 8: Unsorted Set Iterator.");
        }
        else {
            System.out.println("Failed Test 8: Unsorted Set Iterator.");
        }

        // Test 9: Sorted Set Empty Constructor
        testSet = new UnsortedSet<>();
        if (testSet.size() == 0 && !testSet.contains("a")) {
            System.out.println("Passed Test 9: Sorted Set empty constructor.");
        }
        else {
            System.out.println("Failed Test 9: Sorted Set empty constructor.");
        }

        testSet.add("Z");
        testSet.add("A");

        // Test 10: Sorted Set ISet parameter constructor.
        SortedSet testSet3 = new SortedSet<String>(testSet);
        if (testSet2.size() == 2 && testSet3.min().equals("A") && testSet3.max().equals("Z")) {
            System.out.println("Passed Test 10: Sorted Set ISet parameter constructor.");
        }
        else {
            System.out.println("Failed Test 10: Sorted Set ISet parameter constructor.");
        }

        testSet3.add("F");
        testSet3.add("G");
        testSet3.add("H");

        // Test 11: Sorted Set min Method
        if (testSet3.min().equals("A")) {
            System.out.println("Passed Test 11: Sorted Set min method.");
        }
        else {
            System.out.println("Failed Test 11: Sorted Set min method.");
        }

        // Test 12: Sorted Set max Method
        if (testSet3.max().equals("Z")) {
            System.out.println("Passed Test 12: Sorted Set max method.");
        }
        else {
            System.out.println("Failed Test 12: Sorted Set max method.");
        }

        // Test 13: Sorted Set Clear Method
        testSet3.clear();
        if (testSet3.size() == 0 && !testSet3.contains("F")) {
            System.out.println("Passed Test 13: Sorted Set clear method.");
        }
        else {
            System.out.println("Failed Test 13: Sorted Set clear method.");
        }

        // Test 14: Sorted Set Contains Method
        if (!testSet3.contains("N")) {
            System.out.println("Passed Test 14: Sorted Set contains method.");
        }
        else {
            System.out.println("Failed Test 14: Sorted Set contains method.");
        }

        // Test 15: Sorted Set Add Method
        testSet3.add("N");
        if (testSet3.contains("N")) {
            System.out.println("Passed Test 15: Sorted Set add method.");
        }
        else {
            System.out.println("Failed Test 16: Sorted Set add method.");
        }

        // Test 16: Sorted Set Remove Method
        if (testSet3.remove("N") && !testSet3.contains("N")) {
            System.out.println("Passed Test 16: Sorted Set remove method.");
        }
        else {
            System.out.println("Failed Test 16: Sorted Set remove method.");
        }

        testSet3.add("A");
        testSet3.add("B");

        // Test 17: Sorted Set Difference Method
        ISet<String> testSet4 = new SortedSet<>();
        testSet4.add("B");
        expected = new SortedSet<>();
        expected.add("A");
        if (testSet3.difference(testSet4).equals(expected)) {
            System.out.println("Passed Test 17: Sorted Set difference method.");
        }
        else {
            System.out.println("Failed Test 17: Sorted Set difference method.");
        }

        // Test 18:  Sorted Set Union Method
        testSet4.add("C");
        testSet4.add("D");
        expected.add("B");
        expected.add("C");
        expected.add("D");
        if (testSet3.union(testSet4).equals(expected)) {
            System.out.println("Passed Test 18: Sorted Set union method.");
        }
        else {
            System.out.println("Failed Test 18: Sorted Set union method.");
        }

        // Test 19: Sorted Set Intersection Method
        expected.clear();
        expected.add("B");
        if (testSet3.intersection(testSet4).equals(expected)) {
            System.out.println("Passed Test 19: Sorted Set intersection method.");
        }
        else {
            System.out.println("Failed Test 19: Sorted Set intersection method.");
        }


        // Test 20: Sorted Set Size Method
        expected.remove("A");
        expected.remove("N");
        if (testSet3.size() == 2 && testSet4.size() == 3) {
            System.out.println("Passed Test 20: Sorted Set size method.");
        }
        else {
            System.out.println("Failed Test 20: Sorted Set size method.");
        }

        // Test 21: Sorted Set Equals Method
        testSet3.clear();
        testSet3.add("M");
        testSet4.clear();
        testSet4.add("m");
        if (!testSet3.equals(testSet4)) {
            System.out.println("Passed Test 21: Sorted Set equals method.");
        }
        else {
            System.out.println("Failed Test 21: Sorted Set equals method.");
        }

        // Test 22: Sorted Set Iterator
        testIterator = testSet3.iterator();
        if (testIterator.next().equals("M") && !testIterator.hasNext()) {
            System.out.println("Passed Test 22: Sorted Set Iterator.");
        }
        else {
            System.out.println("Failed Test 22: Sorted Set Iterator.");
        }

        // Test 23: Abstract Set addAll Method
        AbstractSet testSet5 = new UnsortedSet();
        testSet5.add("M");
        testSet4.add("M");
        if (!testSet5.addAll(testSet4)) {
            System.out.println("Passed Test 23: Abstract Set addAll method.");
        }
        else {
            System.out.println("Failed Test 23: Abstract Set addAll method.");
        }

        // Test 24: Abstract Set Clear Method
        testSet5.clear();
        if (testSet5.size() == 0) {
            System.out.println("Passed Test 24: Abstract Set clear method.");
        }
        else {
            System.out.println("Failed Test 24: Abstract Set clear method.");
        }

        // Test 25: Abstract Set Contains Method
        if (!testSet5.contains("M")) {
            System.out.println("Passed Test 25: Abstract Set contains method.");
        }
        else {
            System.out.println("Failed Test 25: Abstract Set contains method.");
        }

        // Test 26: Abstract Set containsAll
        testSet5.add("m");
        testSet5.add("M");
        if (testSet5.containsAll(testSet4)) {
            System.out.println("Passed Test 26: Abstract Set containsAll method.");
        }
        else {
            System.out.println("Failed Test 26: Abstract Set containsAll method.");
        }

        // Test 27: Abstract Set Remove Method
        if (testSet5.remove("M")) {
            System.out.println("Passed Test 27: Abstract Set remove method.");
        }
        else {
            System.out.println("Failed Test 27: Abstract Set remove method.");
        }

        // Test 28: Abstract Set Size Method
        testSet5.add("M");
        if (testSet5.size() == 2) {
            System.out.println("Passed Test 28: Abstract Set size method.");
        }
        else {
            System.out.println("Failed Test 28: Abstract Set size method.");
        }

        // Test 29: Abstract Set Equals Method
        if (testSet5.equals(testSet4)) {
            System.out.println("Passed Test 29: Abstract Set equals method.");
        }
        else {
            System.out.println("Failed Test 29: Abstract Set equals method.");
        }

        // Test 30: Abstract Set Union Method
        if (testSet5.union(testSet4).equals(testSet5)) {
            System.out.println("Passed Test 30: Abstract Set union method.");
        }
        else {
            System.out.println("Failed Test 30: Abstract Set union method.");
        }

        // Test 31: Abstract Set toString Method
        if (testSet5.toString().equals("(m, M)")) {
            System.out.println("Passed Test 31: Abstract Set toString method.");
        }
        else {
            System.out.println("Failed Test 30: Abstract Set toString method.");
        }
    }
}