package ru.home.geekbrain.entity.player;

import ru.home.geekbrain.controller.implementation.Resetable;
import ru.home.geekbrain.entity.UserTurnResult;

import java.awt.*;
import java.util.function.Consumer;

public class AIPlayer extends Player{

    private int[][] map;


    public AIPlayer(int id, Consumer<UserTurnResult> turn, Resetable reset) {

        super(id, turn, reset);
        map = null;
    }



    @Override
    public void startTurn() {

        Point point = computerTurn();
        sendTurn(point);
    }



    @Override
    public void consumeMap(int[][] map) {

        this.map = map;
    }



    @Override
    public void consumeMessage(String message) {

    }


    private Point computerTurn(){

        int size = map.length;

        int[][] costMap = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                // skip occupied
                if (map[i][j] != 0)
                    continue;

                int my;
                int enemy;
                int free;
                int cost = 0;

                int enemyId = id == 1 ? 2 : 1;

                // check row
                my = 0;
                enemy = 0;
                free = 0;
                for (int k = 0; k < size; k++) {

                    if (map[k][j] == id) {
                        my++;
                    }
                    if (map[k][j] == enemyId) {
                        enemy++;
                    }
                    if (map[k][j] == 0) {
                        free++;
                    }

                }
                cost += calcCost(my, enemy, free);

                // check column
                my = 0;
                enemy = 0;
                free = 0;
                for (int k = 0; k < size; k++) {


                    if (map[i][k] == id) {
                        my++;
                    }
                    if (map[i][k] == enemyId) {
                        enemy++;
                    }
                    if (map[i][k] == 0) {
                        free++;
                    }
                }
                cost += calcCost(my, enemy, free);


                // check main diagonal
                my = 0;
                enemy = 0;
                free = 0;
                if(i == j) {
                    for (int k = 0; k < size; k++) {

                        if (map[k][k] == id) {
                            my++;
                        }

                        if (map[k][k] == enemyId) {
                            enemy++;
                        }
                        if (map[k][k] == 0) {
                            free++;
                        }
                    }
                    cost += calcCost(my, enemy, free);
                }
                // check secondary diagonal
                my = 0;
                enemy = 0;
                free = 0;
                if(i == size - 1 - j) {
                    for (int k = 0; k < size; k++) {

                        if (map[k][size - 1 - k] == id) {
                            my++;
                        }

                        if (map[k][size - 1 - k] == enemyId) {
                            enemy++;
                        }
                        if (map[k][size - 1 - k] == 0) {
                            free++;
                        }
                    }
                    cost += calcCost(my, enemy, free);
                }

//                // check center  // <= didn't help much
//                if (i== 1 && j== 1) {
//                    cost++;
//                }



                costMap[i][j] = cost;
            }

        }

        int x = size / 2;
        int y = size / 2;

        // get most valuable move
        int max = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (costMap[i][j] > max) {

                    max = costMap[i][j];
                    x = i;
                    y = j;
                }

            }

        }

        return new Point(x, y);
    }


    private int calcCost(int my, int enemy, int free) {

        int result = 0;


        if(my == 2) {
            result = 25;
        }
        if(my == 1) {
            result = 5;
        }
        
        if(my == 1 && free == 2) {
            result = 6;
        }

        if(enemy == 2) {
            result = 15;
        }
        if(enemy == 1) {
            result = 4;
        }





        return result;
    }



}
