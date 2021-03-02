package com.aoc2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {
    private List<String> list = new LinkedList<>();
    private int valueN = 0, valueO = 0, valueS = 0, valueW = 0, distance = 0, valuewaypointN = 1, valuewaypointE = 10, valuewaypointS = 0, valuewaypointW = 0, shipPositionE = 0, shipPositionN = 0, shipPositionS = 0, shipPositionW = 0, valuewaypointNBuffer = 0, valuewaypointSBuffer = 0, valuewaypointWBuffer = 0, valuewaypointEBuffer = 0;
    private final Character[] waypointdirects = new Character[]{'E', 'N'};

    public List<String> getInput(){
            try (Stream<String> stream = Files.lines(Paths.get("input.txt"))) {
                list = stream
                        .map(String::toString)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                return null;
            }
        return list;
    }

    public void navigate(List<String> coords) {
        char facing = 'E', action;
        int value;
        while(!coords.isEmpty()) {
            action = coords.get(0).charAt(0);
            value = Integer.parseInt(coords.get(0).replaceAll("[A-Z]", ""));

            switch (action) {
                case 'N':
                    valueN = valueN + value;
                    break;
                case 'E':
                    valueO = valueO + value;
                    break;
                case 'S':
                    valueS = valueS + value;
                    break;
                case 'W':
                    valueW = valueW + value;
                    break;
                case 'L':
                    while (value >= 360) {
                        value = value - 360;
                    }
                    if (value == 90) {
                        switch (facing) {
                            case 'E':
                                facing = 'N';
                                break;
                            case 'N':
                                facing = 'W';
                                break;
                            case 'W':
                                facing = 'S';
                                break;
                            case 'S':
                                facing = 'E';
                                break;
                            default:
                        }
                    } if (value == 180) {
                        switch (facing) {
                            case 'E':
                                facing = 'W';
                                break;
                            case 'N':
                                facing = 'S';
                                break;
                            case 'W':
                                facing = 'E';
                                break;
                            case 'S':
                                facing = 'N';
                                break;
                            default:
                        }
                    } if (value == 270) {
                        switch (facing) {
                            case 'E':
                                facing = 'S';
                                break;
                            case 'N':
                                facing = 'E';
                                break;
                            case 'W':
                                facing = 'N';
                                break;
                            case 'S':
                                facing = 'W';
                                break;
                            default:
                        }
                    }
                    break;
                case 'R':
                    while (value >= 360) {
                        value = value - 360;
                    }
                    if (value == 90) {
                        switch (facing) {
                            case 'E':
                                facing = 'S';
                                break;
                            case 'N':
                                facing = 'E';
                                break;
                            case 'W':
                                facing = 'N';
                                break;
                            case 'S':
                                facing = 'W';
                                break;
                            default:
                        }
                    } if (value == 180) {
                        switch (facing) {
                            case 'E':
                                facing = 'W';
                                break;
                            case 'N':
                                facing = 'S';
                                break;
                            case 'W':
                                facing = 'E';
                                break;
                            case 'S':
                                facing = 'N';
                                break;
                            default:
                        }
                    } if (value == 270) {
                        switch (facing) {
                            case 'E':
                                facing = 'N';
                                break;
                            case 'N':
                                facing = 'W';
                                break;
                            case 'W':
                                facing = 'S';
                                break;
                            case 'S':
                                facing = 'E';
                                break;
                            default:
                        }
                    }
                    break;
                case 'F':
                    if (facing == 'E') {
                        valueO = valueO + value;
                    } if (facing == 'S') {
                        valueS = valueS + value;
                    } if (facing == 'W') {
                        valueW = valueW + value;
                    } if (facing == 'N') {
                        valueN = valueN + value;
                    }
                    break;
                default:
            }
            coords.remove(0);
        }
    }

    public void manhattenDistance(int n, int e, int s, int w) {
        distance = Math.abs(n - s) + Math.abs(w - e);
    }

    public void navigateWaypoint(List<String> coords) {
        char action;
        int value;
        while(!coords.isEmpty()) {
            action = coords.get(0).charAt(0);
            value = Integer.parseInt(coords.get(0).replaceAll("[A-Z]", ""));

            switch (action) {
                case 'N':
                    if (waypointdirects[0] == 'S' || waypointdirects[1] == 'S') {
                        valuewaypointS = valuewaypointS - value;
                    } else valuewaypointN = valuewaypointN + value;
                    break;
                case 'E':
                    if (waypointdirects[0] == 'W' || waypointdirects[1] == 'W') {
                        valuewaypointW = valuewaypointW - value;
                    } else valuewaypointE = valuewaypointE + value;
                    break;
                case 'S':
                    if (waypointdirects[0] == 'N' || waypointdirects[1] == 'N') {
                        valuewaypointN = valuewaypointN - value;
                    } else valuewaypointS = valuewaypointS + value;
                    break;
                case 'W':
                    if (waypointdirects[0] == 'E' || waypointdirects[1] == 'E') {
                        valuewaypointE = valuewaypointE - value;
                    } else valuewaypointW = valuewaypointW + value;
                    break;
                case 'L':
                    while (value >= 360) {
                        value = value - 360;
                    }
                    for (int i = 0; i < waypointdirects.length; i++) {
                        if (value == 90) {
                            switch (waypointdirects[i]) {
                                case 'N':
                                    waypointdirects[i] = 'W';
                                    valuewaypointWBuffer = valuewaypointN;
                                    break;
                                case 'E':
                                    waypointdirects[i] = 'N';
                                    valuewaypointNBuffer = valuewaypointE;
                                    break;
                                case 'W':
                                    waypointdirects[i] = 'S';
                                    valuewaypointSBuffer = valuewaypointW;
                                    break;
                                case 'S':
                                    waypointdirects[i] = 'E';
                                    valuewaypointEBuffer = valuewaypointS;
                                    break;
                                default:
                            }
                        }
                        if (value == 180) {
                            switch (waypointdirects[i]) {
                                case 'E':
                                    waypointdirects[i] = 'W';
                                    valuewaypointWBuffer = valuewaypointE;
                                    break;
                                case 'N':
                                    waypointdirects[i] = 'S';
                                    valuewaypointSBuffer = valuewaypointN;
                                    break;
                                case 'W':
                                    waypointdirects[i] = 'E';
                                    valuewaypointEBuffer = valuewaypointW;
                                    break;
                                case 'S':
                                    waypointdirects[i] = 'N';
                                    valuewaypointNBuffer = valuewaypointS;
                                    break;
                                default:
                            }
                        }
                        if (value == 270) {
                            switch (waypointdirects[i]) {
                                case 'E':
                                    waypointdirects[i] = 'S';
                                    valuewaypointSBuffer = valuewaypointE;
                                    break;
                                case 'N':
                                    waypointdirects[i] = 'E';
                                    valuewaypointEBuffer = valuewaypointN;
                                    break;
                                case 'W':
                                    waypointdirects[i] = 'N';
                                    valuewaypointNBuffer = valuewaypointW;
                                    break;
                                case 'S':
                                    waypointdirects[i] = 'W';
                                    valuewaypointWBuffer = valuewaypointS;
                                    break;
                                default:
                            }
                        }
                    }
                    valuewaypointE = valuewaypointEBuffer;
                    valuewaypointS = valuewaypointSBuffer;
                    valuewaypointW = valuewaypointWBuffer;
                    valuewaypointN = valuewaypointNBuffer;
                        break;

                case 'R':
                    while (value >= 360) {
                        value = value - 360;
                    }
                    for (int i = 0; i < waypointdirects.length; i++) {
                        if (value == 90) {
                            switch (waypointdirects[i]) {
                                case 'E':
                                    waypointdirects[i] = 'S';
                                    valuewaypointSBuffer = valuewaypointE;
                                    break;
                                case 'N':
                                    waypointdirects[i] = 'E';
                                    valuewaypointEBuffer = valuewaypointN;
                                    break;
                                case 'W':
                                    waypointdirects[i] = 'N';
                                    valuewaypointNBuffer = valuewaypointW;
                                    break;
                                case 'S':
                                    waypointdirects[i] = 'W';
                                    valuewaypointWBuffer = valuewaypointS;
                                    break;
                                default:
                            }
                        }
                        if (value == 180) {
                            switch (waypointdirects[i]) {
                                case 'E':
                                    waypointdirects[i] = 'W';
                                    valuewaypointWBuffer = valuewaypointE;
                                    break;
                                case 'N':
                                    waypointdirects[i] = 'S';
                                    valuewaypointSBuffer = valuewaypointN;
                                    break;
                                case 'W':
                                    waypointdirects[i] = 'E';
                                    valuewaypointEBuffer = valuewaypointW;
                                    break;
                                case 'S':
                                    waypointdirects[i] = 'N';
                                    valuewaypointNBuffer = valuewaypointS;
                                    break;
                                default:
                            }
                        }
                        if (value == 270) {
                            switch (waypointdirects[i]) {
                                case 'E':
                                    waypointdirects[i] = 'N';
                                    valuewaypointNBuffer = valuewaypointE;
                                    break;
                                case 'N':
                                    waypointdirects[i] = 'W';
                                    valuewaypointWBuffer = valuewaypointN;
                                    break;
                                case 'W':
                                    waypointdirects[i] = 'S';
                                    valuewaypointSBuffer = valuewaypointW;
                                    break;
                                case 'S':
                                    waypointdirects[i] = 'E';
                                    valuewaypointEBuffer = valuewaypointS;
                                    break;
                                default:
                            }
                        }
                    }
                    valuewaypointE = valuewaypointEBuffer;
                    valuewaypointS = valuewaypointSBuffer;
                    valuewaypointW = valuewaypointWBuffer;
                    valuewaypointN = valuewaypointNBuffer;
                        break;
                case 'F':
                    for (int i = 0; i < waypointdirects.length; i++) {
                        if (waypointdirects[i] == 'E') {
                            shipPositionE = valuewaypointE * value + shipPositionE;
                        }
                        if (waypointdirects[i] == 'S') {
                            shipPositionS = valuewaypointS * value + shipPositionS;
                        }
                        if (waypointdirects[i] == 'W') {
                            shipPositionW = valuewaypointW * value + shipPositionW;
                        }
                        if (waypointdirects[i] == 'N') {
                            shipPositionN = valuewaypointN * value + shipPositionN;
                        }
                    }
                    break;
                default:
            }
            coords.remove(0);
        }
    }

    public static void main(String[] args) throws IOException {
        Day12 ship = new Day12();
        ship.getInput();
        ship.navigate(ship.list);
        ship.manhattenDistance(ship.valueN, ship.valueO, ship.valueS, ship.valueW);
        System.out.println("answer part one= " + ship.distance);
        ship.getInput();
        ship.navigateWaypoint(ship.list);
        ship.manhattenDistance(ship.shipPositionN, ship.shipPositionE, ship.shipPositionS, ship.shipPositionW);
        System.out.println("N= " + ship.shipPositionN);
        System.out.println("E= " + ship.shipPositionE);
        System.out.println("S= " + ship.shipPositionS);
        System.out.println("W= " + ship.shipPositionW);


        System.out.println("answer part two= " + ship.distance);
    }
}
