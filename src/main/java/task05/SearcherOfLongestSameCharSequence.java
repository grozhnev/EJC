package task05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 9.153. Дан текст. Найти наибольшее количество идущих подряд одинаковых символов.
 */
class SearcherOfLongestSameCharSequence {
    public static void main(String[] args) {
        int maxLength = 0;
        String sequence = "";
        StringBuilder moreThanOneSequence = new StringBuilder();
        StringBuffer builder = new StringBuffer();

        System.out.println("Введите текст");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            char[] charString = input.toCharArray();
            for (int i = 0; i < charString.length - 1; i++) {
                if (charString[i] == charString[i + 1]) {
                    builder.append(charString[i]);
                } else if (charString[i] == charString[i - 1]){
                    builder.append(charString[i]);
                    if (builder.length() > maxLength) {
                        sequence = builder.toString();
                        maxLength = sequence.length();
                        builder = new StringBuffer();
                    } else if (builder.length() == maxLength) {
                        System.out.println("В строке несколько одинаковых по длине последоватедьностей " +
                                                                                                "одинаковых символов");
                        String secondSequence = builder.toString();
                        moreThanOneSequence.append(sequence).append(" и ").append(secondSequence);

                    } else {
                        builder = new StringBuffer();
                    }
                }
            }
            if (moreThanOneSequence.toString().length() != 0) {
                System.out.println("Последовательности из " + maxLength + " одинаковых символов : \""
                                                                                        + moreThanOneSequence + "\"");
            } else {
                System.out.print("Самая длинная последовательность из " + maxLength + " символов в " +
                                                                             "введенной строке : \"" + sequence + "\"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
