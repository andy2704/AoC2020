package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        HashMap<String, List> bags = new HashMap<>(); //no order
        Pattern parentPattern = Pattern.compile("\\w+ \\w+"); //regex one word, one word
        Pattern childPattern = Pattern.compile("\\d+ \\w+ \\w+"); //regex one number, one word, one word
        int count = 0;

        while (scanner.hasNext()) {
            String row = scanner.nextLine();
            Matcher parentMatcher = parentPattern.matcher(row); //match the regex against the string
            parentMatcher.find();
            String parent = parentMatcher.group();

            Matcher childMatcher = childPattern.matcher(row);
            List<String> child = new LinkedList<>();
            while (childMatcher.find()) {
                child.add(childMatcher.group());
            }
            bags.put(parent, child);
        }

        //System.out.println("parent = " + parent);
        //System.out.println("child = " + child);
        System.out.println(bags);

        for (Map.Entry<String, List> entry : bags.entrySet()) {
            String k = entry.getKey();
            List v = entry.getValue();
            if (Pattern.matches(".*shiny gold.*", entry.getValue().toString())){ //true if "shiny gold" is found anywhere in the value
                System.out.println("key: " + k + ", value: " + v);
                count++;
            }
        }

        System.out.println("count = " + count);
        scanner.close();
    }
}
