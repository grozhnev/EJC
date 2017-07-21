package task07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 */
public class SortionTask {

    private String userInput;
    private ArrayList<Integer> inputNumbers;

    public static void main(String[] args) {

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            SortionTask sortion = new SortionTask();
            sortion.inputNumbers = new ArrayList<>();

            System.out.println("Enter 25 numbers: ");
            for (int i = 25; i > 0; i--) {
                sortion.userInput = input.readLine().replaceAll("[^\\d.]", "");

                if (sortion.userInput.length() == 0) {
                    System.err.println("You didn't enter the number");
                } else {
                    sortion.inputNumbers.add(Integer.parseInt(sortion.userInput));
                    System.out.println((i - 1) + " numbers left for input.");
                }
            }
            System.out.println("\nUnsorted list of integers " + sortion.inputNumbers.toString());



            sortion.makeInsertionSort(sortion.inputNumbers);

            /* add quick sort */

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
    void makeInsertionSort(ArrayList<Integer> numbers) {
        int temp;
        int sortInterator;

        System.out.println("\nPerforming insertion sort.");
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
        System.out.println("Sorted integers list: " + numbers.toString());
    }

    /**
     * Performing Quick sort.
     */
    void makeQuickSort(ArrayList<Integer> numbers){




    }
}
