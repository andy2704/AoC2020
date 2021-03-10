package com.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Day6 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String row = reader.readLine();
        //Character[] letter = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        boolean endfile = false;
        Map<Integer, Character> mapbuffer = new LinkedHashMap<>();
        boolean firstentry, bufferempty;
        int count = 0;
        Map<Integer, Character> mapfirstrow = new LinkedHashMap<>();
        Map<Integer, Character> mapsecondrow = new LinkedHashMap<>();

        while (row != null) { //dokumentenschleife
            firstentry = true;
            bufferempty = false;
            mapfirstrow.clear();
            mapsecondrow.clear();

            while (!endfile && !row.equals("")) { //blockschleife
                mapbuffer.clear();
                for (int i = 0; i < row.length(); i++) {
                    mapbuffer.put(i, row.charAt(i));
                }
                if (firstentry) {
                    firstentry = false;
                    mapfirstrow.putAll(mapbuffer);
                } else {
                    mapsecondrow.clear();
                    mapsecondrow.putAll(mapbuffer);
                    mapbuffer.clear();
                    for (int i = 0; i < Math.max(mapfirstrow.size(), mapsecondrow.size()) && !bufferempty; i++){
                        if (mapfirstrow.containsValue(mapsecondrow.get(i))) {
                            mapbuffer.put(i, mapsecondrow.get(i));
                        }
                    }
                    mapfirstrow.clear();
                    mapfirstrow.putAll(mapbuffer);
                    if (mapbuffer.size() == 0){
                        bufferempty = true;
                    }
                }
                row = reader.readLine();
                if (row == null) {
                    endfile = true;
                }
            }
            /*for (int i = 0; i < letter.length; i++){
                if (map.containsValue(letter[i])){
                    count++;
                }
            }*/
            count = count + mapbuffer.size();
            row = reader.readLine();
        }
        System.out.println(count);
        reader.close();
    }
}
