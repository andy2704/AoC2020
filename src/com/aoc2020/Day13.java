package com.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Day13 {

    private final List<String> buslines = new ArrayList<>();
    private float myDepart, answerPartOne;
    private long answerPartTwo;

    public void getMyDepart() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        myDepart = Integer.parseInt(scanner.nextLine());
    }

    public void getBuslines() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        scanner.nextLine();
        String lines = scanner.nextLine();
        String[] splitted = lines.replaceAll("[^\\d]", " ").replaceAll(" +", " ").split(" ");
        buslines.addAll(Arrays.asList(splitted));
    }

    public void getBuslinesPartTwo() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        scanner.nextLine();
        String lines = scanner.nextLine();
        String[] splitted = lines.split(",");
        buslines.clear();
        Collections.addAll(buslines, splitted);
    }

    public void getNextBusline() {
        Float[] busDepartTimes = new Float[buslines.size()];
        long minTime = buslines.stream().mapToLong(Long::parseLong).min().orElse((long) myDepart);
        for (int i = 0; i < buslines.size(); i++) {
            busDepartTimes[i] = Math.round(myDepart / Float.parseFloat(buslines.get(i))) * Float.parseFloat(buslines.get(i)) < myDepart ? (Math.round(myDepart / Float.parseFloat(buslines.get(i))) + 1) * Float.parseFloat(buslines.get(i)) : Math.round(myDepart / Float.parseFloat(buslines.get(i))) * Float.parseFloat(buslines.get(i));
            answerPartOne = busDepartTimes[i] - myDepart < minTime ? ((busDepartTimes[i] - myDepart) * Float.parseFloat(buslines.get(i))) : answerPartOne;
            minTime = (long) Math.min(busDepartTimes[i] - myDepart, minTime);
        }
    }

    public void calcBusContest() {
        long timestamprange = Long.parseLong(buslines.get(0));
        long add = Long.parseLong(buslines.get(0));
        
        for (int i = 1; i < buslines.size(); i++) {
            long checksumBusslines;
            
            if (!buslines.get(i).equals("x")) {
            	checksumBusslines = Long.parseLong(buslines.get(i));
		        while ((timestamprange + i) % checksumBusslines != 0) {
		        	timestamprange += add;
		        }
		        add *= checksumBusslines;
            }
        }
        answerPartTwo = timestamprange;    
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day13 travel = new Day13();
        travel.getMyDepart();
        travel.getBuslines();
        travel.getNextBusline();
        System.out.println(travel.buslines);
        System.out.println("answer part one= " + (int) travel.answerPartOne);

        travel.getBuslinesPartTwo();
        System.out.println(travel.buslines);
        travel.calcBusContest();
        System.out.println("answer part two= " + travel.answerPartTwo);
    }
}
