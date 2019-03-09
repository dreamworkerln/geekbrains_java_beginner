package ru.home.geekbrain.controller.implementation;

import ru.home.geekbrain.controller.BoardApi;
import ru.home.geekbrain.entity.GameBoard;

import java.awt.*;
import java.security.InvalidParameterException;

public class BoardCtrl implements BoardApi {

    private static final int size = 3;

    private GameBoard board;

    public BoardCtrl() {

        board = new GameBoard(size);
    }

    @Override
    public int[][] getMap() {

        int size = board.size;
        int[][] map = board.map;

        int[][] result = new int[size][size];

        for(int i = 0; i < size; i++)
            System.arraycopy(map[i], 0, result[i], 0, size);

        return result;
    }


    @Override
    public boolean isEmpty(Point point) {

        int x = point.x;
        int y = point.y;
        int[][] map = board.map;


        if (x < 0 || x > board.size ||
            y < 0 || y > board.size)
            throw new InvalidParameterException("Index out of bound");

        return map[x][y] == 0;

    }


    @Override
    public void set(Point point, int value) {

        int x = point.x;
        int y = point.y;
        int[][] map = board.map;


        if (x < 0 || x > board.size ||
            y < 0 || y > board.size)
            throw new InvalidParameterException("Index out of bound");

        if (map[x][y] != 0)
            throw new InvalidParameterException("Cell already occupied");

        if (value < 0 || value > 2)
            throw new InvalidParameterException("Invalid value");


        map[x][y] = value;
    }

    @Override
    public boolean isFull() {

        boolean result = true;

        int size = board.size;
        int[][] map = board.map;

        outer:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (map[i][j] == 0) {

                    result = false;
                    break outer;
                }
            }
        }
        return result;
    }




    @Override
    public boolean checkWin(int id) {

        boolean result = false;

        int[][] map = board.map;


        if(
                (map[0][0] == id && map[0][1] == id && map[0][2] == id) ||
                (map[1][0] == id && map[1][1] == id && map[1][2] == id) ||
                (map[2][0] == id && map[2][1] == id && map[2][2] == id) ||
                (map[0][0] == id && map[1][0] == id && map[2][0] == id) ||
                (map[0][1] == id && map[1][1] == id && map[2][1] == id) ||
                (map[0][2] == id && map[1][2] == id && map[2][2] == id) ||
                (map[0][0] == id && map[1][1] == id && map[2][2] == id) ||
                (map[2][0] == id && map[1][1] == id && map[0][2] == id)) {

            result = true;
        }

        return result;
    }





    @Override
    public void clear() {

        board.clear();
    }

}
