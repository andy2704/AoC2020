package com.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day13 {

    private final List<Float> buslines = new ArrayList<>();
    private float myDepart, answerPartOne;

    public void getMyDepart() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        myDepart = Integer.parseInt(scanner.nextLine());
    }

    public void getBuslines () throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        scanner.nextLine();
        String lines = scanner.nextLine();
        String[] splitted = lines.replaceAll("[^\\d]", " ").replaceAll(" +", " ").split(" ");
        for (String s : splitted) {
            buslines.add(Float.parseFloat(s));
        }
    }

    public void getNextBusline() {
        Float[] busDepartTimes = new Float[buslines.size()];
        float buffer;
        float minTime =  buslines.stream().min(Float::compare).orElse( myDepart);
        for (int i = 0; i < buslines.size(); i++) {
            buffer = myDepart / buslines.get(i);
            busDepartTimes[i] = Math.round(buffer) * buslines.get(i) < myDepart ? (Math.round(buffer) + 1) * buslines.get(i) : Math.round(buffer) * buslines.get(i);
            answerPartOne = busDepartTimes[i] - myDepart < minTime ? ((busDepartTimes[i] - myDepart) * buslines.get(i)) : answerPartOne;
            minTime = Math.min(busDepartTimes[i] - myDepart, minTime);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day13 travel = new Day13();
        travel.getMyDepart();
        travel.getBuslines();
        travel.getNextBusline();
        System.out.println("answer part one= " + (int) travel.answerPartOne);
    }
}
