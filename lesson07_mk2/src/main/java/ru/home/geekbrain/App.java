package ru.home.geekbrain;
import ru.home.geekbrain.controller.implementation.GameCtrl;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        JOptionPane.showMessageDialog(null, "Можно Компутер заменить " +
                                                         "на Второго человека\nСм class GameCtrl");

        Thread child = new Thread(new GameCtrl());
        child.start();

    }
}
