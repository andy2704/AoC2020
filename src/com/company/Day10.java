package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day10 {
    public static void main(String[] args) throws IOException {
        List<Integer> input = new ArrayList<>();

        /*
        int startnumber = 0, counterdiff1 = 0, counterdiff2 = 0, counterdiff3 = 0;
        Scanner scanner = new Scanner(new FileReader("input.txt"));
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

         */

//-----------------------------------part two---------------------------------

        int lineNumber = 0;
        boolean endfile = false;

        while (!endfile) { //read the whole file, next time InputStream
            try (Stream<String> inlines = Files.lines(Paths.get("input.txt"))) {
                input.add(Integer.parseInt(inlines.skip(lineNumber).findFirst().get())); //checksum
            } catch (NoSuchElementException e) {
                //e.printStackTrace();
                endfile = true;
                System.out.println("endfile part two");
            }
            lineNumber++;
        }

        input.sort(Comparator.naturalOrder());
        System.out.println(input);

        //important to use long instead of int, bc range of int is too low
        long[] ways = new long[input.get(input.size() - 1) + 1];
        ways[0] = 1;

        /*
        * found some big brain move on the internet
        * looking for possible ways and count
        * filling the ways array in a not normal order
        * default data type value for long is 0
         */
        for (int i = 0; i < input.size(); i++) {
            long x = input.get(i) >= 3 ? ways[input.get(i) - 3] : 0;
            long y = input.get(i) >= 2 ? ways[input.get(i) - 2] : 0;
            long z = input.get(i) >= 1 ? ways[input.get(i) - 1] : 0;
            //System.out.println("x= " + x + " y= " + y + " z= " + z);
            ways[input.get(i)] = x + y + z;
            //System.out.println(ways[input.get(i)]);
        }
        System.out.println(ways[ways.length - 1]);

        /*
        * example to shorten the if else
        long x = input.get(0) >= 3 ? sums[input.get(0) - 3] : 0; //boolean ? true : false
        * long version
        if (input.get(0) >= 3) {
            sums[input.get(0)] = sums[input.get(0)] - 3;
        } else {
            sums[input.get(0)] = 0;
        }
         */
    }
}
