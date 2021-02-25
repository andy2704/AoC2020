package com.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String row = reader.readLine();
        int valid = 0;
        boolean endfile = false;

        while (row != null) {
            String passport = "";
            while (!endfile && !row.equals("")) {
                passport = passport + row + " ";
                row = reader.readLine();
                if (row == null) {
                    endfile = true;
                }
            }
            Map<String, String> map = new HashMap<>();
            for (String keyValue : passport.split("\\s")) {
                String[] pairs = keyValue.split(":", 2);
                map.put(pairs[0], pairs[1]);
            }
            if (map.containsKey("hcl") && map.containsKey("iyr") && map.containsKey("hgt") && map.containsKey("pid") && map.containsKey("byr") && map.containsKey("ecl") && map.containsKey("eyr")) {
                int data = Integer.parseInt(map.get("byr"));
                if (1920 <= data && data <= 2002){
                    data = Integer.parseInt(map.get("iyr"));
                    if (2010 <= data && data <= 2020){
                        data = Integer.parseInt(map.get("eyr"));
                        if (2020 <= data && data <= 2030){
                            String data2 = map.get("hgt");
                            if (data2.length() >= 4 && (data2.charAt(2) == 'i' || data2.charAt(3) == 'c')){
                                data2 = map.get("hcl");
                                if (data2.charAt(0) == '#' && data2.length() == 7){
                                    String letter;
                                    int hclsum = 0;
                                    for (int i = 1; i < data2.length(); i++) {
                                        letter = String.valueOf(data2.charAt(i));
                                        if (letter.matches("[0-9]") || letter.matches("[a-f]")) {
                                           hclsum++;
                                        }
                                    }
                                        if (hclsum == 6) {
                                            data2 = map.get("ecl");
                                            if (data2.equals("amb") || data2.equals("blu") || data2.equals("brn") || data2.equals("gry") || data2.equals("grn") || data2.equals("hzl") || data2.equals("oth")) {
                                                data2 = map.get("pid");
                                                if (data2.length() == 9 && data2.matches("\\d+")) {
                                                    valid++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            row = reader.readLine();
        }
        System.out.println("valid passports: " + valid);

        reader.close();



        //System.out.println(map);
        /*for(Map.Entry m:map.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }*/

        /*int valid = 0;
        boolean endfile = false;

        while (row != null) {
            String passport = "";
                while (!endfile && !row.equals("")){
                    passport = passport + " " + row;
                    row = reader.readLine();
                    if (row == null) {
                        endfile = true;
                    }
                }

            String[] splitted = passport.split("\\s");
        /*for (String s : splitted){
            System.out.println(s);
        }*/

           /* String[] check = {"ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt"};
            int checksum = 0;
            for (int i = 1; i < splitted.length; i++) {
                for (int j = 0; j < check.length; j++) {
                    if (splitted[i].contains(check[j])) {
                        checksum++;
                    }
                }
            }
            if (checksum >= 7){
                valid++;
            }
            row = reader.readLine();
        }
        System.out.println("valid passports : " + valid);*/
    }
}
