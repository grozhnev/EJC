package task05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;

/**
 * 9.154. Дано слово. Определить, сколько различных букв в нем.
 */
public class SearcherOfUniqueLettersInText {

    public static void main(String[] args) {
        Collection symbols = new HashSet();
        System.out.println("Введите текст");

        try(BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in))) {
            char[] input = buffReader.readLine().toCharArray();
            for (int i = 0; i < input.length-1; i++) {
                if (String.valueOf(input[i]).matches("[a-zA-Z]")){
                    symbols.add(input[i]);
                }
            }
            System.out.println("В введенном тексте " + symbols.size() + " различных букв.");
            System.out.println(symbols.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}