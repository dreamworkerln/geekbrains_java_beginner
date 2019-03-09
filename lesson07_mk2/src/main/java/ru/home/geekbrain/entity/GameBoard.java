package ru.home.geekbrain.entity;

public class GameBoard  {

    public int size;
    public int[][] map;

    public GameBoard(int size) {

        this.size = size;
        map = new int [size][size];
    }

    public void clear() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = 0;
            }
        }
    }
}
