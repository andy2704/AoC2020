package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Day9 {
    public static void main (String[] args) throws IOException {
        Integer[] input = new Integer[25];
        boolean endfile = false, found = true;
        int lineNumber = 25, sum = 0, rounds = 0;

        while (!endfile && found) {
            found = false;
            for (int i = 0; i < input.length; i++) { //making the start ready
                try (Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
                    input[i] = Integer.parseInt(lines.skip(i + rounds).findFirst().get());
                } catch (NoSuchElementException e) {
                    //e.printStackTrace();
                    endfile = true;
                    System.out.println("endfile");
                }
                //System.out.println(input[i]);
            }
            try (Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
                sum = Integer.parseInt(lines.skip(lineNumber).findFirst().get()); //checksum
            } catch (NoSuchElementException e) {
                //e.printStackTrace();
                endfile = true;
                System.out.println("endfile");
            }
            if (!endfile) {
                for (int i = 0; i < input.length && !found; i++) {
                    for (int j = 0; j < input.length && !found; j++) {
                        if (input[i] < input[j]) {
                            if (input[i] + input[j] == sum) {
                                found = true;
                            }
                        }
                    }
                }
                /*
                int finalSum = sum;
                IntStream.range(0, input.length)
                        .forEach(i -> IntStream.range(0, input.length)
                                .filter(j -> i < j && input[i] + input[j] == finalSum)
                                .forEach(j -> System.out.println("foo: " + finalSum)));
                 */
                lineNumber++;
                rounds++;
            }
        }
        System.out.println(lineNumber);
        System.out.println("missed sum= " + sum);
    }
}
