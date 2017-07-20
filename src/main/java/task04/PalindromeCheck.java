package task04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Задача из сборника задач Златопольского:
 * 9.116. Проверить, является ли "перевертышем" (см. задачу 9.78) следующая символьная строка после удаления
 * из нее всех пробелов:
 * а) АРГЕНТИНА МАНИТ НЕГРА;
 * б) ПОТ КАК ПОТОП;
 * в) А РОЗА УПАЛА НА ЛАПУ АЗОРА.
 * Во всех задачах последние символы "_", полученные после удаления пробелов, не учитывать.
 */
class PalindromeCheck {

    public static void main(String[] args) {
        String check1 = "АРГЕНТИНА МАНИТ НЕГРА";
        String check2 = "ПОТ КАК ПОТОП";
        String check3 = "А РОЗА УПАЛА НА ЛАПУ АЗОРА";

        String inputString;
        StringBuilder builder = new StringBuilder();

        ArrayList<String> zadanye = new ArrayList<>();
        zadanye.add(check1);
        zadanye.add(check2);
        zadanye.add(check3);

        for (String z : zadanye) {
            inputString = z.replaceAll(" ", "");
            builder = builder.append(inputString).reverse();

            if (inputString.equals(builder.toString())) {
                System.out.println("Строка \"" + z + "\" является палиндромом.");
            } else {
                System.out.println("Строка \"" + z + "\" НЕ палиндромом.");
            }

            builder = new StringBuilder();
        }

        System.out.print("\nA теперь, проверете текст на палиндромность.");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("\nВведите слово или строку: ");
                inputString = reader.readLine().replaceAll(" ", "");
                builder = builder.append(inputString).reverse();

                if (inputString.equals(builder.toString())) {
                    System.out.println("Строка \"" + inputString + "\" является полиндоромом.");
                } else {
                    System.out.println("Строка \"" + inputString + "\" НЕ аявляется полиндоромом.");
                }
                builder = new StringBuilder();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}