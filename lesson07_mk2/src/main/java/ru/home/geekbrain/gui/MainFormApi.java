package ru.home.geekbrain.gui;

public interface MainFormApi {

    void setTitle(String title);

    void setStatus(String text);

    void showMessage(String text);

    void displayMap(int[][] map);
}
