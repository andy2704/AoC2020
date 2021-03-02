package com.aoc2020;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        int maxrows = 0, maxchars = 0, rowcount = 0;
        boolean charcheck = false, partOne = false, partTwo = false;
        while (scanner.hasNext()) {
            String longcheck = scanner.nextLine();
            while (maxchars < longcheck.length() && !charcheck) {
                maxchars++;
            }
            charcheck = true;
            maxrows++;
        }
        scanner.close();

        Scanner scanner2 = new Scanner(new FileReader("input.txt"));
        Character[][] waitingArea = new Character[Math.max(maxchars, maxrows)][Math.max(maxchars, maxrows)];
        Integer[][] seatfinisher = new Integer[Math.max(maxchars, maxrows)][Math.max(maxchars, maxrows)];
        while (scanner2.hasNext()) {
            String row = scanner2.nextLine();
            for (int j = 0; j < Math.min(maxchars, maxrows); j++) { //first round, min bc rectangle
                waitingArea[rowcount][j] = row.charAt(j);
                waitingArea[rowcount][j] = waitingArea[rowcount][j] == 'L' ? waitingArea[rowcount][j] = '#' : waitingArea[rowcount][j];
            }
            rowcount++;
        }
        scanner2.close();

        while (!partTwo) {
            Integer[][] checkseats = new Integer[Math.max(maxchars, maxrows)][Math.max(maxchars, maxrows)];
            for (int i = 0; i < maxrows; i++) {
                for (int j = 0; j < maxchars; j++) {
                    boolean N = false, NO = false, O = false, SO = false, S = false, SW = false, W = false, NW = false;
                    int occupied = 0;
                    for (int l = 1; l < Math.min(maxchars, maxrows); l++) {
                        if (i - l >= 0 && !N) {
                            if (waitingArea[i - l][j] == '#') {
                                occupied++;
                                N = true;
                            } if (waitingArea[i - l][j] == 'L') {
                                N = true;
                            }
                        }
                        if (i - l >= 0 && j + l < maxchars && !NO) {
                            if (waitingArea[i - l][j + l] == '#') {
                                occupied++;
                                NO = true;
                            } if (waitingArea[i - l][j + l] == 'L') {
                                NO = true;
                            }
                        }
                        if (i - l >= 0 && j - l >= 0 && !NW) {
                            if (waitingArea[i - l][j - l] == '#') {
                                occupied++;
                                NW = true;
                            } if (waitingArea[i - l][j - l] == 'L') {
                                NW = true;
                            }
                        }
                        if (i + l < maxrows && !S) {
                            if (waitingArea[i + l][j] == '#') {
                                occupied++;
                                S = true;
                            } if (waitingArea[i + l][j] == 'L') {
                                S = true;
                            }
                        }
                        if (i + l < maxrows && j + l < maxchars && !SO) {
                            if (waitingArea[i + l][j + l] == '#') {
                                occupied++;
                                SO = true;
                            } if (waitingArea[i + l][j + l] == 'L') {
                                SO = true;
                            }
                        }
                        if (i + l < maxrows && j - l >= 0 && !SW) {
                            if (waitingArea[i + l][j - l] == '#') {
                                occupied++;
                                SW = true;
                            } if (waitingArea[i + l][j - l] == 'L') {
                                SW = true;
                            }
                        }
                        if (j + l < maxchars && !O) {
                            if (waitingArea[i][j + l] == '#') {
                                occupied++;
                                O = true;
                            } if (waitingArea[i][j + l] == 'L') {
                                O = true;
                            }
                        }
                        if (j - l >= 0 && !W) {
                            if (waitingArea[i][j - l] == '#') {
                                occupied++;
                                W = true;
                            } if (waitingArea[i][j - l] == 'L') {
                                W = true;
                            }
                        }
                    }
                    checkseats[i][j] = occupied;
                }
            }

            if (Arrays.deepEquals(seatfinisher, checkseats)) {
                partTwo = true;
            } else {
                seatfinisher = checkseats.clone();
            }

            for (int i = 0; i < maxrows; i++) {
                for (int j = 0; j < maxchars; j++) {
                    if (waitingArea[i][j] == 'L' && checkseats[i][j] == 0) {
                        waitingArea[i][j] = '#';
                    }
                    if (waitingArea[i][j] == '#' && checkseats[i][j] >= 5) {
                        waitingArea[i][j] = 'L';
                    }
                }
            }
        }

        int partTwoCounter = 0;
        for (int i = 0; i < maxrows; i++) {
            for (int j = 0; j < maxchars; j++) {
                if (waitingArea[i][j] == '#') {
                    partTwoCounter++;
                }
            }
        }
        System.out.println("part Two= " + partTwoCounter);

/*
        while (!partOne) {
            Integer[][] checkseats = new Integer[Math.max(maxchars, maxrows)][Math.max(maxchars, maxrows)];
            for (int i = 0; i < maxrows; i++) {
                for (int j = 0; j < maxchars; j++) {
                    int occupied = 0;
                    for (int l = (i > 0 ? -1 : 0); l <= (i < maxrows - 1 ? 1 : 0); l++) {
                        for (int k = (j > 0 ? -1 : 0); k <= (j < maxchars - 1 ? 1 : 0); k++) {
                            if (waitingArea[i + l][j + k] == '#' && !(i + l == i && j + k == j)) { //dont count the own position
                                occupied++;
                            }
                        }
                    }
                    checkseats[i][j] = occupied;
                }
            }

            if (Arrays.deepEquals(seatfinisher, checkseats)) {
                partOne = true;
            } else {
                seatfinisher = checkseats.clone();
            }

            for (int i = 0; i < maxrows; i++) {
                for (int j = 0; j < maxchars; j++) {
                    if (waitingArea[i][j] == 'L' && checkseats[i][j] == 0) {
                        waitingArea[i][j] = '#';
                    }
                    if (waitingArea[i][j] == '#' && checkseats[i][j] >= 4) {
                        waitingArea[i][j] = 'L';
                    }
                }
            }
        }

        int partOneCounter = 0;
        for (int i = 0; i < maxrows; i++) {
            for (int j = 0; j < maxchars; j++) {
                if (waitingArea[i][j] == '#') {
                    partOneCounter++;
                }
            }
        }
        System.out.println("part One= " + partOneCounter);
 */
    }
}
