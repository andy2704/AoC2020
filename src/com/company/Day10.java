package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        List<Integer> input = new ArrayList<>();
        int startnumber = 0, counterdiff1 = 0, counterdiff2 = 0, counterdiff3 = 0;

        while (scanner.hasNext()) {
            input.add(Integer.parseInt(scanner.nextLine()));
        }
        input.sort(Comparator.naturalOrder());
        while(!input.isEmpty()) {
            if (input.get(0) - startnumber == 1) {
                startnumber = input.get(0);
                counterdiff1++;
                input.remove(0);
            } else if (input.get(0) - startnumber == 2) {
                startnumber = input.get(0);
                counterdiff2++;
                input.remove(0);
            } else if (input.get(0) - startnumber == 3) {
                startnumber = input.get(0);
                counterdiff3++;
                input.remove(0);
            }
        }
        counterdiff3++;
        System.out.println("diff1= " + counterdiff1 + " diff2= " + counterdiff2 + " diff3= " + counterdiff3);
        System.out.println("answer= " + counterdiff1 * counterdiff3);
    }
}
