package com.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Day2 {

    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("input.txt");
        BufferedReader reader = new BufferedReader(fr);
        int answer = 0;
        String row = reader.readLine();

        while (row != null) {
            //String number1 = row.replaceAll("[^0-9]", "");
            String[] splitted = row.split("\\s", 3);
            String[] numbers = splitted[0].split("-");
            int number1 = Integer.parseInt(numbers[0]);
            int number2 = Integer.parseInt(numbers[1]);
            char letter = splitted[1].charAt(0);

            /*int count = 0;
            for (int i = 0; i < splitted[2].length(); i++) {
                char currentletter = splitted[2].charAt(i);
                if (currentletter == letter) {
                    count++;
                }
            }*/

            if (letter == splitted[2].charAt(number1-1) ^ letter == splitted[2].charAt(number2-1)){
                answer++;
            }

            /*if (number1 <= count && count <= number2) {
                answer++;
            }*/
            row = reader.readLine();
        }
        /*System.out.println(Arrays.toString(splitted));
        System.out.println(Arrays.toString(numbers));
        System.out.println(number1);
        System.out.println(number2);
        System.out.println(letter);
        System.out.println(splitted[2].length());
        System.out.println(row);
        */
        System.out.println("valid passwords = " + answer);
        reader.close();
    }
}
