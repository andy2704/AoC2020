package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {

    public static void main(String[] args) throws IOException {

        FileReader fr1 = new FileReader("input.txt");
        BufferedReader reader1 = new BufferedReader(fr1);
        FileReader fr2 = new FileReader("input.txt");
        BufferedReader reader2 = new BufferedReader(fr2);

        int count = 0;
        String value = reader1.readLine();

        while (value != null){
            value = reader1.readLine();
            count = count + 1;
        }

        Integer[] entries = new Integer[count];
        for (int i = 0; i < count; i++){
            entries[i] = Integer.parseInt(reader2.readLine());
        }

        boolean stop = false;
        for (int i = 0; (i < entries.length) && !stop; i++){
            for (int j = i + 1; (j < entries.length) && !stop; j++){
                for (int k = i + 2; k < entries.length; k++)
                if (entries[i] + entries[j] + entries[k] == 2020){
                    System.out.println("value1 = " + entries[i]);
                    System.out.println("value2 = " + entries[j]);
                    System.out.println("value3 = " + entries[k]);
                    int answer = entries[i] * entries[j] * entries[k];
                    System.out.println("answer = " + answer);
                    stop = true;
                }
            }
        }

        reader1.close();
        reader2.close();
    }
}
