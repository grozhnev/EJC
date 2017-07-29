package task09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * class: SelectionAndRadixSort
 * version: 0.2
 * <p>
 * Created by: Georgii Rozhnev, https://github.com/grozhnev
 * date: 2017-07-30
 * <p>
 * description: perform Selection and Radix Sort
 */
public class SelectionAndRadixSort {
    private int amountOfNumbers;
    private boolean correctInputFormat = false;
    private Random random = new Random();
    private ArrayList<Integer> randomListOfNumbers = new ArrayList<>();
    private ArrayList<Integer> numbersSortedWithSelectionSort;
    private ArrayList<Integer> numbersSortedWothRadixSort = new ArrayList<>();

    public static void main(String[] args) {
        SelectionAndRadixSort sort = new SelectionAndRadixSort();
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            sort.launchSelectionSort(sort, buff);
            sort.correctInputFormat = false;
            sort.launchRadixSort(sort, buff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Selection sort.
     *
     * @param sort
     * @param buff
     * @throws IOException
     */

    void launchSelectionSort(SelectionAndRadixSort sort, BufferedReader buff) throws IOException {
        System.out.print("Enter the amount of number for unsorted list: ");
        do {
            String inputString = buff.readLine().trim().replaceAll("[^\\d.]", "");
            if (inputString.length() > 0) {
                sort.amountOfNumbers = Integer.parseInt(inputString);
                for (int i = 0; i < sort.amountOfNumbers; i++) {
                    sort.randomListOfNumbers.add(sort.random.nextInt(100));
                }
                System.out.println("We got " + sort.amountOfNumbers + " numbers to sort.\n" + sort.randomListOfNumbers);
                sort.correctInputFormat = true;
            } else {
                System.err.println("Incorrect input format! Try again.");
            }
        } while (!sort.correctInputFormat);

        sort.numbersSortedWithSelectionSort = sort.performSelectionSort(sort.randomListOfNumbers);
        System.out.println("Here we got the list of numbers, sorted with Selection sort:\n" + sort.numbersSortedWithSelectionSort);
    }


    public ArrayList<Integer> performSelectionSort(ArrayList<Integer> unsortedNumbers) {
        ArrayList<Integer> sortedNumbers = new ArrayList<>(unsortedNumbers);

        /* making two cycles, reducing the each second cycle on one step  */
        for (int firstCycle = 0; firstCycle < sortedNumbers.size(); firstCycle++) {
            int smallestNumber = firstCycle;
            int smallestValue = sortedNumbers.get(firstCycle);
            for (int secondCycle = firstCycle; secondCycle < sortedNumbers.size(); secondCycle++) {
                if (sortedNumbers.get(secondCycle) <= sortedNumbers.get(smallestNumber)) {
                    smallestNumber = secondCycle;
                    smallestValue = sortedNumbers.get(secondCycle);
                }
            }
            /* replacing*/
            int temp = sortedNumbers.get(firstCycle);
            sortedNumbers.set(firstCycle, smallestValue);
            sortedNumbers.set(smallestNumber, temp);
        }
        return sortedNumbers;
    }

    /**
     * Radix sort.
     *
     * @param sort
     * @param bufferedReader
     * @throws IOException
     */
    void launchRadixSort(SelectionAndRadixSort sort, BufferedReader bufferedReader) throws IOException {
        System.out.print("\n\nReady for radix sort?\nEnter the amount of number for unsorted list: ");
        do {
            String inputString = bufferedReader.readLine().trim().replaceAll("[^\\d.]", "");
            if (inputString.length() > 0) {
                sort.amountOfNumbers = Integer.parseInt(inputString);
                for (int i = 0; i < sort.amountOfNumbers; i++) {
                    sort.randomListOfNumbers.add(sort.random.nextInt(10000));
                }
                System.out.println("We got " + sort.amountOfNumbers + " numbers to sort.\n" + sort.randomListOfNumbers);
                sort.correctInputFormat = true;
            } else {
                System.err.println("Incorrect input format! Try again.");
            }
        } while (!sort.correctInputFormat);

        sort.numbersSortedWothRadixSort = sort.performRadixSort(sort.randomListOfNumbers);
        System.out.println("Result of RADIX sort :\n" + sort.numbersSortedWothRadixSort);
    }

    public ArrayList<Integer> performRadixSort(ArrayList<Integer> unsortedArray) {
        int[] sortingArray = new int[unsortedArray.size()];
        for (int i = 0; i < unsortedArray.size() - 1; i++) {
            sortingArray[i] = unsortedArray.get(i);
        }

        /* Find the largest number to get the number of bits, cycles for count sort */
        int maxValue = getMaxValue(sortingArray);

        /* make Count sort for every bit */
        for (int bit = 1; maxValue / bit > 0; bit *= 10) {
            countSort(sortingArray, bit);
        }

        ArrayList<Integer> sortedArray = new ArrayList<>();
        for (int i = 0; i < sortingArray.length; i++) {
            sortedArray.add(sortingArray[i]);
        }
        return sortedArray;
    }

    int getMaxValue(int array[]) {
        int largestNumber = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i] > largestNumber)
                largestNumber = array[i];
        return largestNumber;
    }

    int[] countSort(int sortingArray[], int bit) {
        int outputArray[] = new int[sortingArray.length];
        int digitsCount[] = new int[10];
        Arrays.fill(digitsCount, 0);

        // Store count of occurrences in digitsCount[[]
        for (int i = 0; i < sortingArray.length; i++) {
            digitsCount[(sortingArray[i] / bit) % 10]++;
        }

        // Change digitsCount[[i] for the actual position of this digit in outputArray[]
        for (int i = 1; i < 10; i++) {
            digitsCount[i] += digitsCount[i - 1];
        }

        /*  Building the output array */
        for (int i = sortingArray.length - 1; i >= 0; i--) {
            outputArray[digitsCount[(sortingArray[i] / bit) % 10] - 1] = sortingArray[i];
            digitsCount[(sortingArray[i] / bit) % 10]--;
        }

        /* Copying the outputArray back to sortingArray[], where numbers are sorted  by the current bit */
        for (int i = 0; i < sortingArray.length; i++) {
            sortingArray[i] = outputArray[i];
        }
        return sortingArray;
    }
}