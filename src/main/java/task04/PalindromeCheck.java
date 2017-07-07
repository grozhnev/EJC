package task04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Задача из сборника задач Златопольского:
 *      9.116. Проверить, является ли "перевертышем" (см. задачу 9.78) следующая символьная строка после удаления
 *             из нее всех пробелов:
 *                  а) АРГЕНТИНА МАНИТ НЕГРА;
 *                  б) ПОТ КАК ПОТОП;
 *                  в) А РОЗА УПАЛА НА ЛАПУ АЗОРА.
 *              Во всех задачах последние символы "_", полученные после удаления пробелов, не учитывать.
 */
public class PalindromeCheck {

    public static void main(String[] args) {
        String check1 = "АРГЕНТИНА МАНИТ НЕГРА";
        String check2 = "ПОТ КАК ПОТОП";
        String check3 = "А РОЗА УПАЛА НА ЛАПУ АЗОРА";

        String str;
        StringBuilder bldr = new StringBuilder();

        ArrayList<String> zadanye = new ArrayList<>();
        zadanye.add(check1);
        zadanye.add(check2);
        zadanye.add(check3);

        for (String z: zadanye) {
            str = z.replaceAll(" ", "");
            bldr= bldr.append(str).reverse();

            if(str.equals(bldr.toString())) {
                System.out.println("Строка \"" + z + "\" является палиндромом.");
            } else {
                System.out.println("Строка \"" + z + "\" НЕ палиндромом.");
            }

            bldr = new StringBuilder();
        }

        System.out.print("\nA теперь, проверете текст на палиндромность.");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("\nВведите слово или строку: ");
                str = reader.readLine().replaceAll(" ", "");
                bldr = bldr.append(str).reverse();

                //сравнить и выдать результат
                if (str.equals(bldr.toString())) {
                    System.out.println("Строка \"" + str + "\" является полиндоромом.");
                } else {
                    System.out.println("Строка \"" + str + "\" НЕ аявляется полиндоромом.");
                }
                bldr = new StringBuilder();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
