package ru.home.geekbrain.controller;

import java.awt.*;

public interface BoardApi {

    // get whole map copy(not reference)
    int[][] getMap();

    // set point to map
    void set(Point point, int value);


    // set point to map
    boolean isEmpty(Point point);

    boolean checkWin(int id);

    boolean isFull();

    // doit board
    void clear();


}
