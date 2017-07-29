package task05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;

/**
 * 9.154. Дано слово. Определить, сколько различных букв в нем.
 */
class SearcherOfUniqueLettersInText {
    public static void main(String[] args) {
        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in))) {
            findUniqueSymbols(buffReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void findUniqueSymbols(BufferedReader buffReader) throws IOException {
        Collection symbols = new HashSet();
        System.out.println("Enter some text");
        char[] input = buffReader.readLine().toUpperCase().toCharArray();
        for (int i = 0; i < input.length ; i++) {
            if (String.valueOf(input[i]).matches("[A-Z]")) {
                symbols.add(input[i]);
            }
        }
        System.out.println("This text consists of " + symbols.size() + " different alphabetic letters.");
        System.out.println(symbols.toString());
    }
}