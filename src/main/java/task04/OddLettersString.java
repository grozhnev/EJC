package task04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Zlatopolsky task:
 * 9.43. You got the word s1. Create word s2, made only with odd characters from word s1.
 */
public class OddLettersString {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            showOddLetters(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void showOddLetters(BufferedReader reader) throws IOException {
        String string1;
        String string2;
        while (true) {
            System.out.print("Enter a word or a string: ");
            string1 = reader.readLine();
            System.out.println("Your input is: " + string1);

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < string1.length(); i++) {
                if (i % 2 == 0)
                    builder.append(string1.charAt(i));
            }
            string2 = builder.toString();
            System.out.print("Here is your input only with odd characters: " + string2 + "\n\n");
        }
    }
}