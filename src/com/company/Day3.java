package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String row = reader.readLine();

        int count = 0;
        while (row != null){
            count++;
            row = reader.readLine();
        }

        BufferedReader reader2 = new BufferedReader(new FileReader("input.txt"));
        String row2 = reader2.readLine();
        int xmax = row2.length();

        Character[][] field = new Character[count][xmax];
        for (int i = 0; i < count; i++){
            for (int j = 0; j < row2.length(); j++){
                field [i][j] = row2.charAt(j);
            }
            row2 = reader2.readLine();
        }

        int x = 0, y = 1, answer1 = 0;
        while (y < count){
            x = x + 1;
            if (x > xmax - 1){
                x = x - xmax;
            }
            char check = field[y][x];
            if (check == '#'){
                answer1++;
            }
            y = y + 1;
        }

        x = 0;
        y = 1;
        int answer2 = 0;
        while (y < count){
            x = x + 3;
            if (x > xmax - 1){
                x = x - xmax;
            }
            char check = field[y][x];
            if (check == '#'){
                answer2++;
            }
            y = y + 1;
        }

        x = 0;
        y = 1;
        int answer3 = 0;
        while (y < count){
            x = x + 5;
            if (x > xmax - 1){
                x = x - xmax;
            }
            char check = field[y][x];
            if (check == '#'){
                answer3++;
            }
            y = y + 1;
        }

        x = 0;
        y = 1;
        int answer4 = 0;
        while (y < count){
            x = x + 7;
            if (x > xmax - 1){
                x = x - xmax;
            }
            char check = field[y][x];
            if (check == '#'){
                answer4++;
            }
            y = y + 1;
        }

        x = 0;
        y = 2;
        int answer5 = 0;
        while (y < count){
            x = x + 1;
            if (x > xmax - 1){
                x = x - xmax;
            }
            char check = field[y][x];
            if (check == '#'){
                answer5++;
            }
            y = y + 2;
        }

        int trees = answer1 * answer2 * answer3 * answer4 * answer5;

        System.out.println("answer1 = " + answer1);
        System.out.println("answer2 = " + answer2);
        System.out.println("answer3 = " + answer3);
        System.out.println("answer4 = " + answer4);
        System.out.println("answer5 = " + answer5);
        System.out.println("trees = " + trees);

        /*for (int i = 0; i < count; i++){
            for (int j = 0; j < count; j++){ //fix it!!!!
                System.out.print(field[i][j]);
            }
            System.out.print("\n");
        }*/
    }
}
