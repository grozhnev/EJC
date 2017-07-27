package task08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * class: BubbleAndMergeSortWithBinarySearch
 * version: 0.3
 * <p>
 * Created by: Georgii Rozhnev, https://github.com/grozhnev
 * date: 2017-07-26
 * <p>
 * description:
 * We randomly get unsorted list of 100 numbers in range from 0 to 1000.
 * After that we make Bubble sort.
 * And Merge sort.
 * Each sort result is covered with JUnit tests. java code.
 * After that we use binary search in sorted list of elements.
 */
public class BubbleAndMergeSortWithBinarySearch {
    public static void main(String[] args) {
        BubbleAndMergeSortWithBinarySearch sort = new BubbleAndMergeSortWithBinarySearch();
        int capacityForBubbleSort = -1;
        int capacityForMergeSort = -1;
        int numberInBubbleSortedList = -1;
        int numberInMergeSortedList = -1;
        int positionInBubbleSortedList;
        int positionInMergeSortedList;
        boolean correctInputFormat = false;

        ArrayList<Integer> listSortedWithBubbleSort;
        ArrayList<Integer> listSortedWithMergeSort;
        ArrayList<Integer> listOfNumbers = new ArrayList<>();
        ArrayList<Integer> otherListOfNumbers = new ArrayList<>();

        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Only numbers accepted s input. Letters and empty spaces are being skipped.\n");

             /* make bubble sort */
            System.out.print("What is the capacity for the new list of unsorted numbers?");
            do {
                String inputString = buff.readLine().trim().replaceAll("[^\\d.]", "");
                if (inputString.length() > 0) {
                    capacityForBubbleSort = Integer.parseInt(inputString);
                    System.out.println("Input \"" + capacityForBubbleSort + "\" accepted.");
                    correctInputFormat = true;
                } else {
                    System.err.println("Incorrect input format! Try again.");
                }
            } while (!correctInputFormat);
            listOfNumbers.addAll(sort.createListOfNumbers(capacityForBubbleSort));
            System.out.println("\nHere is unsorted list: " + listOfNumbers);
            listSortedWithBubbleSort = sort.performBubbleSort(listOfNumbers);
            System.out.println("Sorted with BubbleSort: " + listSortedWithBubbleSort);

            /* make merge sort */
            System.out.print("\nEnter the capacity for MergeSort:");
            correctInputFormat = false;
            do {
                String inputString = buff.readLine().trim().replaceAll("[^\\d.]", "");
                if (inputString.length() > 0) {
                    capacityForMergeSort = Integer.parseInt(inputString);
                    System.out.println("Input \"" + capacityForMergeSort + "\" accepted.");
                    correctInputFormat = true;
                } else {
                    System.err.println("Incorrect input format! Try again.");
                }
            } while (!correctInputFormat);
            otherListOfNumbers.addAll(sort.createListOfNumbers(capacityForMergeSort));
            System.out.println("Unsorted list2 : " + otherListOfNumbers);
            listSortedWithMergeSort = new ArrayList<>(otherListOfNumbers);
            sort.performMergeSort(listSortedWithMergeSort, 0, listSortedWithMergeSort.size() - 1);
            System.out.println("Sorted with MergeSort: " + listSortedWithMergeSort);

            /* binary search in Bubble sorted list */
            System.out.print("\nWhat is the number you are looking for in the first sorted list? ");
            correctInputFormat = false;
            do {
                String inputString = buff.readLine().trim().replaceAll("[^\\d.]", "");
                if (inputString.length() > 0) {
                    numberInBubbleSortedList = Integer.parseInt(inputString);
                    System.out.println("Input \"" + numberInBubbleSortedList + "\" accepted.");
                    correctInputFormat = true;
                } else {
                    System.err.println("Incorrect input format! Try again.");
                }
            } while (!correctInputFormat);
            positionInBubbleSortedList = sort.performBinarySearch(listSortedWithBubbleSort, numberInBubbleSortedList);
            if (positionInBubbleSortedList != -1) {
                System.out.println("Index of searching element in list is " + (positionInBubbleSortedList + 1));
            } else {
                System.out.println("Number " + numberInBubbleSortedList + " is not in the list!");
            }

            /* binary search in Merge sorted list */
            System.out.print("\nThan what is the number you are looking for in the second sorted list? ");
            correctInputFormat = false;
            do {
                String inputString = buff.readLine().trim().replaceAll("[^\\d.]", "");
                if (inputString.length() > 0) {
                    numberInMergeSortedList = Integer.parseInt(inputString);
                    System.out.println("Input \"" + numberInMergeSortedList + "\" accepted.");
                    correctInputFormat = true;
                } else {
                    System.err.println("Incorrect input format! Try again.");
                }
            } while (!correctInputFormat);

            positionInMergeSortedList = sort.performBinarySearch(listSortedWithMergeSort, numberInMergeSortedList);
            if (positionInMergeSortedList != -1) {
                System.out.println("Index of searching element in list is " + (positionInMergeSortedList + 1));
            } else {
                System.out.println("Number " + numberInMergeSortedList + " is not in the list!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate random numbers.
     *
     * @param amount
     * @return
     */
    public ArrayList<Integer> createListOfNumbers(int amount) {
        Random random = new Random();
        ArrayList<Integer> generatedList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            generatedList.add(random.nextInt(1000));
        }
        return generatedList;
    }

    /**
     * perform BubbleSort.
     *
     * @param inputList
     * @return
     */
    public ArrayList<Integer> performBubbleSort(ArrayList<Integer> inputList) {
        ArrayList<Integer> outputList = new ArrayList<>();
        int temp;
        int lastElement = inputList.size() - 1;

        while (lastElement >= 0) {
            for (int i = 0; i < lastElement; i++) {

                if (inputList.get(i) > inputList.get(i + 1)) {
                    temp = inputList.get(i);
                    inputList.set(i, inputList.get(i + 1));
                    inputList.set(i + 1, temp);
                }
            }
            lastElement--;
        }
        outputList.addAll(inputList);
        return outputList;
    }

    /**
     * perform MergeSort.
     *
     * @param inputList
     * @param head
     * @param tail
     */
    public ArrayList<Integer> performMergeSort(ArrayList<Integer> inputList, int head, int tail) {
        /* divide list into single-element sorted arrays  */
        if (head < tail) {
            int middle = (head + tail) / 2;
            /* sort left part */
            performMergeSort(inputList, head, middle);
            /* sort right part */
            performMergeSort(inputList, middle + 1, tail);
            /* combine both sorted parts  */
            performMerge(inputList, head, middle, tail);
        }
        return inputList;
    }

    /* merge two separately sorted parts */
    public void performMerge(ArrayList<Integer> inputList, int head, int middle, int tail) {
        ArrayList<Integer> mergeResult = new ArrayList<>(inputList);
        int leftPartIterator = head;
        int rightPartIterator = middle + 1;
        int elementsIterator = head;

        while (leftPartIterator <= middle && rightPartIterator <= tail) {
            if (mergeResult.get(leftPartIterator) <= mergeResult.get(rightPartIterator)) {
                inputList.set(elementsIterator, mergeResult.get(leftPartIterator));
                leftPartIterator++;
            } else {
                inputList.set(elementsIterator, mergeResult.get(rightPartIterator));
                rightPartIterator++;
            }
            elementsIterator++;
        }

        while (leftPartIterator <= middle) {
            inputList.set(elementsIterator, mergeResult.get(leftPartIterator));
            leftPartIterator++;
            elementsIterator++;
        }
    }

    /**
     * perform Binary search.
     */
    public int performBinarySearch(ArrayList<Integer> sortedList, int numberLost) {
        int index = -1;
        if (sortedList.size() > 0) {
            int lowerIterator = 0;
            int middle;
            int higherIterator = sortedList.size();

            while (lowerIterator < higherIterator) {
                middle = (lowerIterator + higherIterator) / 2;
                if (numberLost == sortedList.get(middle)) {
                    index = middle;
                    break;
                } else {
                    if (numberLost < sortedList.get(middle)) {
                        higherIterator = middle;
                    } else {
                        lowerIterator = middle + 1;
                    }
                }
            }
        }
        return index;
    }
}