package task05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;


/**
 * 9.153. Дан текст. Найти наибольшее количество идущих подряд одинаковых символов.
 */
public class SearcherOfLongestSameCharSequence {
    public static void main(String[] args) {
        int maxLength=0;
        String sequence="";
        StringBuffer moreThanOneSequence= new StringBuffer();
        StringBuffer bulder = new StringBuffer();

        System.out.println("Введите текст");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            char[] charString = input.toCharArray();
            for (int i = 0; i <charString.length-1; i++) {
                if (charString[i] == charString[i + 1]) /*(Objects.equals(charString[i],charString[i + 1]))*/ {
                    bulder.append(charString[i]);
                } else if ( charString[i] == charString[i - 1] )/*(Objects.equals(charString[i], charString[i - 1]))*/ {
                    bulder.append(charString[i]);
                    if (bulder.length() > maxLength) {
                        sequence = bulder.toString();
                        maxLength = sequence.length();
                        bulder = new StringBuffer(); // empty buffer
                    } else if (bulder.length() == maxLength) {
                        System.out.println("В строке несколько одинаковых по длине последоватедьностей одинаковых символов");
                        String secondSequence = bulder.toString();
                        moreThanOneSequence.append(sequence).append(" и ").append(secondSequence);
                    } else {
                        bulder = new StringBuffer(); // empty buffer
                    }
                }
            }
            if (moreThanOneSequence.toString().length() != 0) {
                System.out.println("Последовательности из "+ maxLength + " одинаковых символов : \"" + moreThanOneSequence + "\"");
            } else {
                System.out.print("Самая длинная последовательность из " + maxLength +  " символов в введенной строке : \"" + sequence +"\"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
