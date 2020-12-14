package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Day7 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        TreeMap<Integer, String> tree = new TreeMap<>();
        String row = reader.readLine();

        while (row != null) {
            row = "1 " + row;
            String[] splitted = row.split("bags contain\\s*|bag,\\s*|bags,\\s*|bag.\\s*|bags.\\s*");

            for (String s : splitted) {
                System.out.println(s);
            }
            System.out.println(splitted.length);
            row = reader.readLine();
        }
        reader.close();
    }
}
