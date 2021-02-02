package com.company;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_EMPTY = '*';
    public static final int DOTS_TO_WIN = 4;
    public static final int SIZE = 5;
    public static char[][] map;

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        modeAgainstAI();
    }


    private static void modeAgainstAI() {
        int count = 0;
        initMap();
        while (true) {
            printMap();
            humanTurn();
            count++;
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                printMap();
                break;
            }
            aiTurn();
            count++;
            if (checkWin(DOT_O)) {
                System.out.println("Победил компьютер");
                printMap();
                break;
            }
            if (count == Math.pow(SIZE, 2)) {
                printMap();
                break;
            }
        }
    }


    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (char[] row : map) {
            Arrays.fill(row, DOT_EMPTY);
        }
    }

    private static void printMap() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }
    private static void humanTurn() {
        int x = -1;
        int y = -1;
        do {
            System.out.println();
            System.out.println("Введите координаты");
            System.out.println("Введите X");
            x = scanner.nextInt() - 1;
            System.out.println("Введите Y");
            y = scanner.nextInt() - 1;
        }
        while (isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    private static void aiTurn() {
        int x = -1;
        int y = -1;
        boolean aiwin = false;
        boolean humanWin = false;
            if (!aiwin) {
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (!isCellValid(i, j)) {
                            map[i][j] = DOT_X;
                            if (checkWin(DOT_X)) {
                                x = i;
                                y = j;
                                humanWin = true;
                            }
                            map[i][j] = DOT_EMPTY;
                        }
                    }
                }
            }

            if (!aiwin && !humanWin) {
                do {
                    x = new Random().nextInt(SIZE);
                    y = new Random().nextInt(SIZE);
                }
                while (isCellValid(x, y));
            }
            map[x][y] = DOT_O ;
    }


    private static boolean isCellValid(int x, int y) {
        return x >= 0 && y >= 0 && x < SIZE && y < SIZE && map[x][y] != DOT_EMPTY;
    }



    private static boolean checkLine(int start_x, int start_y, int dx, int dy, char symbol) {
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[start_x + i * dx][start_y + i * dy] != symbol)
                return false;
        }
        return true;
    }

    private static boolean checkWin(char symbol) {
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            // проверяем строки
            if (checkLine(i, 0, 0, 1, symbol)) return true;
            // проверяем столбцы
            if (checkLine(0, i, 1, 0, symbol)) return true;
        }
        // проверяем диагонали
        if (checkLine(0, 0, 1, 1, symbol)) return true;
        if (checkLine(0, DOTS_TO_WIN - 1, 1, -1, symbol)) return true;
        return false;
    }

}
