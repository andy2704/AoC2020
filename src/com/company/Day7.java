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
        //System.out.println(bags);

//------------------------------Part Two-------------------------------
        //build searchQueue

        Pattern childNumberPattern = Pattern.compile("\\d+");
        Pattern childNewParentPattern = Pattern.compile("[a-zA-Z]+ [a-zA-Z]+");
        Queue<String> searchQ = new LinkedList<>();

        for (Object child : bags.get("shiny gold")) { //get key 'shiny gold' and look for all direct childs
            Matcher childNumberMatcher = childNumberPattern.matcher(child.toString());
            childNumberMatcher.find();

            Matcher childNewParentMatcher = childNewParentPattern.matcher(child.toString());
            childNewParentMatcher.find();

            int number = Integer.parseInt(childNumberMatcher.group());
            String childColor = childNewParentMatcher.group();

            count+= number;

            for (int i = 0; i < number; i++) {
                searchQ.add(childColor); //color in Queue = number found
            }
        }

        // go through the tree and count the numbers
        while (!searchQ.isEmpty()) {
            String nextSearch = searchQ.poll();
            for (Object child : bags.get(nextSearch)) {
                Matcher childNumberMatcher = childNumberPattern.matcher(child.toString());
                childNumberMatcher.find();

                Matcher childNewParentMatcher = childNewParentPattern.matcher(child.toString());
                childNewParentMatcher.find();

                int number = Integer.parseInt(childNumberMatcher.group());
                String childColor = childNewParentMatcher.group();

                count+= number;

                for (int i = 0; i < number; i++) {
                    searchQ.add(childColor); //color in Queue = number found
                }
            }
        }

/*--------------------------------Part One--------------------------------

        Queue<String> searchQueue = new LinkedList<>();
        for (Map.Entry<String, List> entry : bags.entrySet()) {
            String k = entry.getKey();
            //List v = entry.getValue();
            if (Pattern.matches(".*shiny gold.*", entry.getValue().toString())){ //true if "shiny gold" is found anywhere in the value
                //System.out.println("key: " + k + ", value: " + v);
                searchQueue.add(k);
                bags.put(k, null);
                count++;
            }
        }
        while (!searchQueue.isEmpty()){
            String nextSearch = searchQueue.poll();
            for (Map.Entry<String, List> entry : bags.entrySet()) {
                String k = entry.getKey();
                //List v = entry.getValue();
                if (entry.getValue() != null &&  Pattern.matches(".*" + nextSearch + ".*", entry.getValue().toString())){
                    //System.out.println("key: " + k + ", value: " + v);
                    searchQueue.add(k);
                    bags.put(k, null);
                    count++;
                }
            }
        }
*/
        System.out.println("count = " + count);
        scanner.close();
    }
}
