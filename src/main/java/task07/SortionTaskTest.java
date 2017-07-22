package task07;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Unit test for Sortion Task
 */
public class SortionTaskTest {

    @Test
    public void testInsertioNSort() {
        SortionTask sort = new SortionTask();

        /* define random array of numbers  to be sorted in test*/
        int[] numbers = {100, 500, 6000, 200, -3, 140, 0, 8};
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            array.add(numbers[i]);
        }

        /* define correctly sorted list of numbers */
        ArrayList<Integer> sortedArray = new ArrayList<>();
        int[] sortedNumbers = {-3, 0, 8, 100, 140, 200, 500, 6000};
        for (int i = 0; i < sortedNumbers.length; i++) {
            sortedArray.add(sortedNumbers[i]);
        }

        /* perform test */
        Assert.assertEquals("Wrong result of Insertion sort", sortedArray, sort.makeInsertionSort(array));
    }

    @Test
    public void testQuickSort() {
        SortionTask sort = new SortionTask();

        /* define random array of numbers  to be sorted in test*/
        int[] numbers = {100, 500, 6000, 200, -3, 140, 0, 8};
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            array.add(numbers[i]);
        }
        /* define correctly sorted list of numbers */
        ArrayList<Integer> sortedArray = new ArrayList<>();
        int[] sortedNumbers = {-3, 0, 8, 100, 140, 200, 500, 6000};
        for (int i = 0; i < sortedNumbers.length; i++) {
            sortedArray.add(sortedNumbers[i]);
        }
        
        /* perform test */
        Assert.assertEquals("Wrong result of Quick sort", sortedArray, sort.makeQuickSort(array, 0, array.size() - 1));
    }
}
