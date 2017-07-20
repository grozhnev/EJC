package task07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Создаете коллекцию, ввести с клавиатуры 25 чисел. Выводите коллекцию на экран без сортировки.
 * Делаете Quick Sort и выводите после сортировки.
 * Вводите 100 чисел через рандом, 0..1000, Insertion Sort.
 * Покрыть Юнит тестами.
 * Make lists, not war.
 *
 * P.S.: Решение незначительно изменяет условие без потери смысла.
 */

public class InsertionSort {

    public static void main(String[] args) {

        System.out.println("Эта программа принимает на вход любые целые числа в диапазоне от " + Integer.MIN_VALUE +
                " до " + Integer.MAX_VALUE + " . На каждое введенное число, создается ещё одно случайное число.\n" +
                "После этого массив символов будет выведен в консоль, затем отсортирован методом сортировки вставкой.\n");

        ArrayList<Integer> numbers = new ArrayList<>();
        Random randomNumber = new Random();
        int temp;
        int place;
        String userInput;

        /**
         * Instantiation numbers.
         */
        System.out.println("Введите 10 чисел: ");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 10; i > 0; i--) {
                int newInt = randomNumber.nextInt();
                numbers.add(newInt);
                userInput = input.readLine().replaceAll("[^\\d.]", "");
                numbers.add(Integer.parseInt(userInput));
                System.out.println("Осталось " + (i - 1));

            }
            System.out.println("\nНеотсортированный список чисел:" + numbers.toString());

            /**
             * Insertion Sort in action.
             */
            for (int i = 0; i < numbers.size() - 1; i++) {
                if (numbers.get(i) > numbers.get(i + 1)) {
                    temp = numbers.get(i + 1);
                    numbers.set(i + 1, numbers.get(i));
                    numbers.set(i, temp);
                    place = i;
                    while (place > 0 && temp < numbers.get(place - 1)) {
                        numbers.set(place, numbers.get(place - 1));
                        place--;
                    }
                    numbers.set(place, temp);
                }
            }
            System.out.println("Отсотритрованный список чисел методом вставки : " + numbers.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
