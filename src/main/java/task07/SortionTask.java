package task07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Release of Insertion Sort, Quick Sort and Collections Sort (as bonus).
 */
public class SortionTask {
    private String userInput;
    private ArrayList<Integer> inputNumbers;
    private ArrayList<Integer> sortedList;

    public static void main(String[] args) {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            SortionTask sortion = new SortionTask();
            sortion.inputNumbers = new ArrayList<>();

            /* Insertion Sort */
            System.out.println("Enter 5 numbers: ");
            for (int i = 5; i > 0; i--) {
                sortion.userInput = input.readLine().replaceAll("[^\\d.]", "");
                if (sortion.userInput.length() == 0) {
                    System.err.println("You didn't enter the number");
                } else {
                    sortion.inputNumbers.add(Integer.parseInt(sortion.userInput));
                    System.out.println((i - 1) + " numbers left for input.");
                }
            }
            System.out.println("\nUnsorted list of integers " + sortion.inputNumbers.toString());
            sortion.sortedList = sortion.makeInsertionSort(sortion.inputNumbers);
            sortion.inputNumbers.clear();
            sortion.sortedList.clear();

            /* QuickSort */
            System.out.println("\n\nQuick sort in action:");
            System.out.println("Enter 6 numbers: ");
            for (int k = 6; k > 0; k--) {
                sortion.userInput = input.readLine().replaceAll("[^\\d.]", "");
                if (sortion.userInput.length() == 0) {
                    System.err.println("You didn't enter the number");
                } else {
                    sortion.inputNumbers.add(Integer.parseInt(sortion.userInput));
                    System.out.println((k - 1) + " numbers left for input.");
                }
            }
            System.out.println("\nUnsorted list of integers " + sortion.inputNumbers.toString());
            sortion.sortedList = sortion.makeQuickSort(sortion.inputNumbers, 0, sortion.inputNumbers.size() - 1);
            System.out.println("Sorted list: " + sortion.inputNumbers.toString());
            sortion.inputNumbers.clear();
            sortion.sortedList.clear();

            /* Java Collections Sort  */
            System.out.println("\n\nCollections sort in action:");
            System.out.println("Enter 7 numbers: ");
            for (int j = 7; j > 0; j--) {
                sortion.userInput = input.readLine().replaceAll("[^\\d.]", "");
                if (sortion.userInput.length() == 0) {
                    System.err.println("You didn't enter the number");
                } else {
                    sortion.inputNumbers.add(Integer.parseInt(sortion.userInput));
                    System.out.println((j - 1) + " numbers left for input.");
                }
            }
            System.out.println("\nUnsorted list of integers " + sortion.inputNumbers.toString());
            sortion.sortedList = sortion.makeCollectionsSort(sortion.inputNumbers);
            sortion.inputNumbers.clear();
            sortion.sortedList.clear();
        } catch (IOException e) {
            System.err.println("Error in number input occurred.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Number is out of range of Integer number [" + Integer.MIN_VALUE + " , " +
                    Integer.MAX_VALUE + "].");
            e.printStackTrace();
        }
    }

    /**
     * Performing insertion sort.
     */
    ArrayList<Integer> makeInsertionSort(ArrayList<Integer> numbers) {
        int temp;
        int sortInterator;

        System.out.print("\nPerforming insertion sort.");
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                temp = numbers.get(i + 1);
                numbers.set(i + 1, numbers.get(i));
                numbers.set(i, temp);
                sortInterator = i;
                while (sortInterator > 0 && temp < numbers.get(sortInterator - 1)) {
                    numbers.set(sortInterator, numbers.get(sortInterator - 1));
                    sortInterator--;
                }
                numbers.set(sortInterator, temp);
            }
        }
        System.out.print("\nSorted integers list: " + numbers.toString());
        return numbers;
    }

    /**
     * Performing Quick sort.
     */
    ArrayList<Integer> makeQuickSort(ArrayList<Integer> numbers, int first, int last) {
        int leftIndex = first;
        int middle = numbers.get(first + (last - first) / 2);
        int rightIndex = last;

        do {
            while (numbers.get(leftIndex) < middle) {
                leftIndex++;
            }
            while (numbers.get(rightIndex) > middle) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                if (leftIndex < rightIndex) {
                    int tempValue = numbers.get(leftIndex);
                    numbers.set(leftIndex, numbers.get(rightIndex));
                    numbers.set(rightIndex, tempValue);
                }
                leftIndex++;
                rightIndex--;
            }
        } while (leftIndex <= rightIndex);

        if (leftIndex < last) {
            makeQuickSort(numbers, leftIndex, last);
        }
        if (first < rightIndex) {
            makeQuickSort(numbers, first, rightIndex);
        }
        return numbers;
    }

    /**
     * Performing Collections sort.
     */
    ArrayList<Integer> makeCollectionsSort(ArrayList<Integer> numbers) {
        System.out.print("Performing Collections sort.");
        Collections.sort(numbers);
        System.out.print("\nSorted list of numbers" + numbers.toString());
        return numbers;
    }
}