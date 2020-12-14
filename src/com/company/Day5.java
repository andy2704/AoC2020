package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day5 {

    private int seatrow, seatcolumn, seatid, highestseatid, myid;
    private final ArrayList<Integer> list = new ArrayList<>();
    private boolean found = false;

    public int binarysearchrow(String row){
        double seatrowmin = 0;
        double seatrowmax = 127;
        Character[] search = new Character[row.length()];
        for (int i = 0; i < row.length() - 3; i++){
            search[i] = row.charAt(i);
            if (search[i] == 'F'){
                seatrowmax = (seatrowmax + seatrowmin) / 2;
                seatrow = (int) seatrowmax;
            } else if (search[i] == 'B') {
                seatrowmin = Math.ceil((seatrowmin + seatrowmax) / 2);
                seatrow = (int) seatrowmin;
            }
        }
        return seatrow;
    }

    public int binarysearchcolumn(String row){
        double seatcolumnmin = 0;
        double seatcolumnmax = 7;
        Character[] search = new Character[row.length()];
        for (int i = 7; i < row.length(); i++) {
            search[i] = row.charAt(i);
            if (search[i] == 'R') {
                seatcolumnmin = Math.ceil((seatcolumnmax + seatcolumnmin) / 2);
                seatcolumn = (int) seatcolumnmin;
            } else if (search[i] == 'L') {
                seatcolumnmax =(seatcolumnmax + seatcolumnmin) / 2;
                seatcolumn = (int) seatcolumnmax;
            }
        }
        return seatcolumn;
    }

    public int getseatid (int row, int colum){
        seatid = row * 8 + colum;
        return seatid;
    }

    public int gethighestseatid (int seatid, int seatidold){
        if (seatid <= seatidold){
            highestseatid = seatidold;
        } else {
            highestseatid = seatid;
        }
        return highestseatid;
    }

    public ArrayList<Integer> makelist (int id){
        list.add(id);
        return list;
    }

    public int checkids (ArrayList<Integer> ids){
        for (int i = 0;  !ids.isEmpty() && !found; i++){
            if (ids.get(i)+1 != ids.get(i+1)){
                myid = ids.get(i)+1;
                found = true;
            }
        }
        return myid;
    }

    public static void main (String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String row = reader.readLine();
        int seatidold = 0;
        Day5 plane = new Day5();

        while (row != null) {
            plane.binarysearchrow(row);
            plane.binarysearchcolumn(row);
            plane.getseatid(plane.seatrow, plane.seatcolumn);
            plane.makelist(plane.seatid);

            //plane.gethighestseatid(plane.seatid, seatidold);
            //seatidold = plane.highestseatid;

            //System.out.println(plane.seatrow);
            //System.out.println(plane.seatcolumn);
            //System.out.println("ID: " + plane.seatid);
            //System.out.println(plane.highestseatid);

            row = reader.readLine();
        }
        Collections.sort(plane.list);
        plane.checkids(plane.list);
        System.out.println(plane.myid);


        /*for (int s : plane.list){
            System.out.println(s);
        }*/
        //System.out.println(plane.highestseatid);
        reader.close();
    }
}
