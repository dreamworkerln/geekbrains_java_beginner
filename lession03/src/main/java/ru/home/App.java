package ru.home;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Hello world!
 *
 */
class App
{

    // here map[1][2] mean x=1; y=2 like in 2D plot(x coordinate goes first)
    static char[][] map;

    static final int SIZE = 3;
    static final char DOT_EMPTY = ' ';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';

    static final boolean SILLY_MODE = false;

    static final Scanner scanner = new Scanner(System.in);
    static final ThreadLocalRandom random = ThreadLocalRandom.current();



    public static void main(String[] args) {


        final String FRIENDSHIP = "Friendship";
        final String FLAWLESS_VICTORY = "Flawless victory";
        final String FATALITY = "Fatality!";

        initMap();

        printMap();

        //boolean endGame = false;
        while(true) {



            // -------------------------------------------

            humanTurn();

            printMap();

            if (checkWin(DOT_X)) {
                System.out.println(FLAWLESS_VICTORY);
                break;
            }

            if (isMapFull()) {
                System.out.println(FRIENDSHIP);
                break;
            }

            // --------------------------------------------

            computerTurn();

            printMap();

            if (checkWin(DOT_O)) {
                System.out.println(FATALITY);
                break;
            }

            if (isMapFull()) {
                System.out.println(FRIENDSHIP);
                break;
            }



        }
    }


    private static void initMap() {

        map = new char[SIZE][SIZE];

        for(int i = 0; i < SIZE; i ++){
            for(int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }



    /**
     * Will clear when run in standalone console "java -jar lession03.jar"
     */
    static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    private static void printMap() {

        clearScreen();

        // header -----------------------------------------

        for(int i = 0; i <= SIZE; i++) {
            if (i == 0) {
                System.out.print( i +"|");
            }
            else {
                System.out.print(i + "|");
            }
        }
        System.out.println();

        for(int i = 0; i <= SIZE * 2 +1 ; i++) {
            System.out.print("-");
        }

        System.out.println();

        // -------------------------------------------------

        for(int j = 0; j < SIZE; j++) {

            System.out.print((j + 1) + "|");

            for(int i = 0; i < SIZE; i++){

                System.out.print(map[i][j] + "|");
            }

            System.out.println();
            for(int i = 0; i <= SIZE * 2 +1 ; i++) {
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println();
    }



    static boolean isCellValid(int x, int y){
        boolean result = true;

        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        }
        else if(map[x][y] != DOT_EMPTY){
            result = false;
        }

        return result;
    }




    private static void computerTurn(){



        int x = -1;
        int y = -1;

        if(SILLY_MODE){



            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while(!isCellValid(x, y));

        }


        else {

            // http://www.ntu.edu.sg/home/ehchua/programming/java/javagame_tictactoe_ai.html

            int[][] costMap = new int[SIZE][SIZE];

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {

                    // skip occupied
                    if (map[i][j] != DOT_EMPTY)
                        continue;

                    int my;
                    int enemy;
                    int cost =0;

                    // check row
                    my = 0;
                    enemy = 0;
                    for (int k = 0; k < SIZE; k++) {

                        if (map[k][j] == DOT_O) {
                            my++;
                        }
                        if (map[k][j] == DOT_X) {
                            enemy++;
                        }
                    }
                    cost += calcCost(my, enemy);

                    // check column
                    my = 0;
                    enemy = 0;
                    for (int k = 0; k < SIZE; k++) {


                        if (map[i][k] == DOT_O) {
                            my++;
                        }
                        if (map[i][k] == DOT_X) {
                            enemy++;
                        }
                    }
                    cost += calcCost(my, enemy);


                    // check main diagonal
                    my = 0;
                    enemy = 0;
                    if(i == j) {
                        for (int k = 0; k < SIZE; k++) {

                            if (map[k][k] == DOT_O) {
                                my++;
                            }

                            if (map[k][k] == DOT_X) {
                                enemy++;
                            }
                        }
                        cost += calcCost(my, enemy);
                    }
                    // check secondary diagonal
                    my = 0;
                    enemy = 0;
                    if(i == SIZE - 1 - j) {
                        for (int k = 0; k < SIZE; k++) {

                            if (map[k][SIZE - 1 - k] == DOT_O) {
                                my++;
                            }

                            if (map[k][SIZE - 1 - k] == DOT_X) {
                                enemy++;
                            }
                        }
                        cost += calcCost(my, enemy);
                    }

                    // check center  // <= didn't help much
                    if (i== 1 && j== 1) {
                        cost++;
                    }



                    costMap[i][j] = cost;
                }

            }

            // get most valuable move

            int max = 0;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {

                    if (costMap[i][j] > max) {

                        max = costMap[i][j];
                        x = i;
                        y = j;
                    }

                }

            }
        }

        //System.out.println("Компьютер выбрал ячейку " + (x + 1) + " " + (y + 1));
        map[x][y] = DOT_O;
    }


    static int calcCost(int my, int enemy) {

        int result = 0;


        if(my == 2) {
            result = 10;
        }
        if(my == 1) {
            result = 5;
        }

        if(enemy == 2) {
            result = 20;
        }
        if(enemy == 1) {
            result = 4;
        }

        return result;
    }





    static boolean checkWin(char playerSymbol) {

        boolean result = false;

        if(
                (map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)) {

            result = true;
        }

        return result;
    }


    static boolean isMapFull() {

        boolean result = true;

        outer:
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                if (map[i][j] == DOT_EMPTY) {

                    result = false;
                    break outer;
                }
            }
        }

        return result;
    }


    private static void humanTurn() {
        int x, y;

        boolean firstTry = true;

        do {

            x = -1;
            y = -1;

            if (!firstTry) {
                System.out.println("Неправильный ввод");
            }
            firstTry = false;

            System.out.println("Используйте keypad");


            // Use keypad as greed
            switch (scanner.nextInt()) {

                case 1:
                    x = 0;
                    y = 2;
                    break;

                case 2:
                    x = 1;
                    y = 2;
                    break;

                case 3:
                    x = 2;
                    y = 2;
                    break;

                case 4:
                    x = 0;
                    y = 1;
                    break;

                case 5:
                    x = 1;
                    y = 1;
                    break;

                case 6:
                    x = 2;
                    y = 1;
                    break;

                case 7:
                    x = 0;
                    y = 0;
                    break;

                case 8:
                    x = 1;
                    y = 0;

                    break;

                case 9:
                    x = 2;
                    y = 0;
                    break;
            }
        }
        while(!isCellValid(x, y));

        map[x][y] = DOT_X;
    }

}
