package task05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 9.153. Дан текст. Найти наибольшее количество идущих подряд одинаковых символов.
 */
class SearcherOfLongestSameCharSequence {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            searchLongestSequence(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void searchLongestSequence(BufferedReader reader) throws IOException {
        int maxLength = 0;
        String sequence = "";
        StringBuilder moreThanOneSequence = new StringBuilder();
        StringBuffer builder = new StringBuffer();

        System.out.println("Enter some text: ");
        String input = reader.readLine();
        char[] charString = input.toCharArray();

        for (int i = 0; i < charString.length - 1; i++) {
            if (charString[i] == charString[i + 1]) {
                builder.append(charString[i]);
            }  else if (maxLength == 0) {
                System.err.println("\nNo sequence of equal symbols in text!!!");
                break;
            }else if (charString[i] == charString[i - 1]){
                builder.append(charString[i]);
                if (builder.length() > maxLength) {
                    sequence = builder.toString();
                    maxLength = sequence.length();
                    builder = new StringBuffer();
                } else if (builder.length() == maxLength) {
                    System.out.println("There is more then one longest sequences of the same symbols.");
                    String secondSequence = builder.toString();
                    moreThanOneSequence.append(sequence).append(" и ").append(secondSequence);
                } else {
                    builder = new StringBuffer();
                }
            }
        }
        if (moreThanOneSequence.toString().length() != 0) {
            System.out.println("Longest sequences have " + maxLength + " equal symbols : \""
                                                                                    + moreThanOneSequence + "\"");
        } else {
            System.out.print("In entered text there is a sequence of " + maxLength + " equal symbols : \""
                                                                                                + sequence + "\"");
        }
    }
}
