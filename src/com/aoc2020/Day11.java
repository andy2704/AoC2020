package com.aoc2020;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        int count = 0, w = 0;
        while (scanner.hasNext()) {
            scanner.nextLine();
            count++;
        }
        scanner.close();
        Scanner scanner2 = new Scanner(new FileReader("input.txt"));

        Character[][] waitingArea = new Character[count][count];
        while (scanner2.hasNext()) {
            String row = scanner2.nextLine();
            for (int j = 0; j < count; j++) {
                waitingArea[w][j] = row.charAt(j);
            }
            w++;
        }
        for (int i = 0; i < count; i++) { // first round
            for (int j = 0; j < count; j++) {
                if (waitingArea[i][j] == 'L') {
                    waitingArea[i][j] = '#';
                }
                System.out.print(waitingArea[i][j]);
            }
            System.out.println();
        }
        scanner2.close();
    }
}
