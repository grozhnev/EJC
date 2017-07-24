package task08;

/**
 * Сделать TEST !
 * <p>
 * И Binary search.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * class: BubbleAndMergeSort
 * version: 0.1
 * <p>
 * Created by: Georgii Rozhnev, https://github.com/grozhnev
 * date: 2017-07-24
 * <p>
 * description:
 * We randomly get unsorted list of 100 numbers in range from 0 to 1000.
 * After that we make Bubble sort.
 * And Merge sort.
 * Each sort result is covered with JUnit tests. java code.
 * After that we use binary search in sorted list of elements.
 */
public class BubbleAndMergeSort {

    public static void main(String[] args) {
        BubbleAndMergeSort sort = new BubbleAndMergeSort();
        int capacityForBubbleSort;
        int capacityForMergeSort;

        ArrayList<Integer> listSortedWithBubbleSort;
        ArrayList<Integer> listSortedWithMergeSort;
        ArrayList<Integer> listOfNumbers = new ArrayList<>();
        ArrayList<Integer> otherListOfNumbers = new ArrayList<>();

        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
             /* make bubble sort */
            System.out.print("What is the capacity for the new lis of unsorted numbers?");
            capacityForBubbleSort = Integer.parseInt(buff.readLine());
            System.out.println("Instantiating the list of " + capacityForBubbleSort + " numbers...");
            listOfNumbers.addAll(sort.createListOfNumbers(capacityForBubbleSort));
            System.out.println("\nHere is unsorted list: " + listOfNumbers);

            listSortedWithBubbleSort = sort.performBubbleSort(listOfNumbers);
            System.out.println("Sorted with BubbleSort: " + listSortedWithBubbleSort);

            /* make merge sort */
            System.out.print("\nEnter the capacity for MergeSort:");
            capacityForMergeSort = Integer.parseInt(buff.readLine());
            otherListOfNumbers.addAll(sort.createListOfNumbers(capacityForMergeSort));
            System.out.println("Unsorted list2 : " + otherListOfNumbers);


            listSortedWithMergeSort = new ArrayList<>(listOfNumbers);
            sort.performMergeSort(listSortedWithMergeSort, 0, listSortedWithMergeSort.size() - 1);
            System.out.println("Sorted with MergeSort: " + listSortedWithMergeSort);

            /* make binary search */
            System.out.println();

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
    public void performMergeSort(ArrayList<Integer> inputList, int head, int tail) {
        int middle = (head + tail) / 2;
        while (head <= middle && tail >= middle) {
            if (inputList.get(head) > inputList.get(tail)) {
                int temp = inputList.get(head);
                inputList.set(head, inputList.get(tail));
                inputList.set(tail, temp);
            }
            head++;
            tail--;
        }
        head = 0;
        tail = inputList.size() - 1;
        middle = (head + tail) / 2;

        if (inputList.get(head) > inputList.get(middle + 1)) {
            performMergeSort(inputList, head, middle);
            performMergeSort(inputList, middle + 1, tail);
        }
    }

    /**
     * Binary search ..... coming soon.
     * :)
     */
}
