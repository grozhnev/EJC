package task04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Задача из сборника задач Златопольского:
 * 9.43. Дано слово s1. Получить слово s2, образованное нечетными буквами слова s1.
 */
public class OddLettersString {

    public static void main(String[] args) {

        String string1, string2;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("Введите слово или строку: ");
                string1 = reader.readLine();
                System.out.println("Вы ввели: " + string1);

                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < string1.length(); i++) {
                    if (i % 2 == 0)
                        builder.append(string1.charAt(i));
                }
                string2 = builder.toString();
                System.out.print("Сейчас все нечетные символы из вашей строки испаряться: " + string2 + "\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}