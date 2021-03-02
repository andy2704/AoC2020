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
    private int valueN = 0, valueO = 0, valueS = 0, valueW = 0, distance = 0;

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

    public static void main(String[] args) throws IOException {
        Day12 ship = new Day12();
        ship.getInput();
        ship.navigate(ship.list);
        System.out.println("N= " + ship.valueN);
        System.out.println("E= " + ship.valueO);
        System.out.println("S= " + ship.valueS);
        System.out.println("W= " + ship.valueW);
        ship.manhattenDistance(ship.valueN, ship.valueO, ship.valueS, ship.valueW);
        System.out.println(ship.distance);
    }
}
