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
        checkTaskTextOnPalindrome();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            checkUserInputTextAsPalindrome(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void checkUserInputTextAsPalindrome(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        String inputString;
        while (true) {
            System.out.print("\nEnter some text: ");
            inputString = reader.readLine().replaceAll(" ", "");
            builder.append(inputString).reverse();

            if (inputString.equals(builder.toString())) {
                System.out.println("Text \"" + inputString + "\" is palindrome.");
            } else {
                System.out.println("Text \"" + inputString + "\" is NOT a palindrome.");
            }
            builder = new StringBuilder();
        }
    }

    static void checkTaskTextOnPalindrome() {
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
                System.out.println("Text \"" + z + "\" is palindrome.");
            } else {
                System.out.println("Text \"" + z + "\" is NOT a palindrome.");
            }

            builder = new StringBuilder();
        }
        System.out.print("\nNow, you can check any other text for being palindrome.");
    }
}