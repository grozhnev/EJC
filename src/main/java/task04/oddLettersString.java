package task04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Задача из сборника задач Златопольского:
 *      9.43. Дано слово s1. Получить слово s2, образованное нечетными буквами слова s1.
 */
public class oddLettersString {

    public static void main(String[] args) {

        String s1, s2;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                System.out.print("Введите слово или строку: ");
                s1 = reader.readLine();
                System.out.println("Вы ввели: " + s1);

                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < s1.length(); i++) {
                    if (i % 2 == 0)
                        builder.append(s1.charAt(i));
                }
                s2 = builder.toString();
                System.out.print("Сейчас все нечетные символы из вашей строки испаряться: " + s2 + "\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
