package com.aoc2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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
                lineNumber++;
                rounds++;
            }
        }
        System.out.println(lineNumber);
        System.out.println("missed sum= " + sum);

//------------------------------------------part two-------------------------------------------------

        int insertNumberStart = 0, insertNumberEnd = 0, answer = 0;
        boolean tooHigh, parttwoFound = false;

        for (int i = insertNumberStart; i < lineNumber && !parttwoFound && !endfile; i++) {
            insertNumberEnd = insertNumberStart + 1;
            List<Integer> storedNumbers = new ArrayList<>();
            tooHigh = false;
            try (Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
                storedNumbers.add(Integer.parseInt(lines.skip(insertNumberStart).findFirst().get()));
            } catch (NoSuchElementException e) {
                //e.printStackTrace();
                endfile = true;
                System.out.println("endfile part two");
            }
                for (int j = insertNumberEnd; j < lineNumber && !tooHigh && !parttwoFound && !endfile; j++) {
                    try (Stream<String> lines2 = Files.lines(Paths.get("input.txt"))) {
                        storedNumbers.add(Integer.parseInt(lines2.skip(insertNumberEnd).findFirst().get()));
                    } catch (NoSuchElementException e) {
                        //e.printStackTrace();
                        endfile = true;
                        System.out.println("endfile part two");
                    }
                    if (storedNumbers.stream().mapToInt(Integer::intValue).sum() == sum) {
                        //answer = storedNumbers.stream().min(Comparator.comparing(Integer::valueOf)).get() + storedNumbers.stream().max(Comparator.comparing(Integer::valueOf)).get();
                        answer = Collections.min(storedNumbers) + Collections.max(storedNumbers);
                        System.out.println("anwser found");
                        parttwoFound = true;
                    } else if (storedNumbers.stream().mapToInt(Integer::intValue).sum() > sum) {
                        tooHigh = true;
                        insertNumberStart++;
                    } else {
                        insertNumberEnd++;
                    }
                }
        }
        System.out.println("encryption weakness = " + answer);
    }
}
