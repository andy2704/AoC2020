package com.aoc2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day8 {
    public static void main(String[] args) throws IOException {
        int linenumber, accumulator = 0, counter = 0;
        boolean found, endfile = false;

        while (!endfile) { //outer loop, start of the file, changed instructions increasing per loop, counter says witch instruction getting changed
            List<Integer> lineIndex = new ArrayList<>();
            String lineString = null;
            int instructionCounter = 0;
            found = false;
            accumulator = 0;
            linenumber = 0;

            while (!found && !endfile) {
                try (Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
                    lineString = lines.skip(linenumber).findFirst().get();
                } catch (NoSuchElementException e) {
                    //e.printStackTrace();
                    endfile = true;
                    System.out.println("endfile");
                }
                lineIndex.add(linenumber);
                assert lineString != null;
                String[] instruction = lineString.split("\\s", 2); //first and all new lines

                if (instruction[0].equals(args[0]) && counter == instructionCounter) {
                    instruction[0] = args[1];
                    instructionCounter++;
                }

                if (!endfile) {
                    switch (instruction[0]) {
                        case "nop":
                            linenumber++;
                            if (lineIndex.contains(linenumber)) {
                                found = true;
                            }
                            if (instruction[0].equals(args[0])) {
                                instructionCounter++;
                            }
                            break;
                        case "acc":
                            linenumber++;
                            if (lineIndex.contains(linenumber)) {
                                found = true;
                            }
                            accumulator = accumulator + Integer.parseInt(instruction[1]);
                            break;
                        case "jmp":
                            linenumber = linenumber + Integer.parseInt(instruction[1]);
                            if (lineIndex.contains(linenumber)) {
                                found = true;
                            }
                            if (instruction[0].equals(args[0])) {
                                instructionCounter++;
                            }
                            break;
                        default:
                            System.out.println("bar");
                    }
                }
            }
            counter++;
        }
        System.out.println("acc = " + accumulator);
        //System.out.println("counter: " + counter);
    }
}
