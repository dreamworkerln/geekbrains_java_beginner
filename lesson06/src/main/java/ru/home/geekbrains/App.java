package ru.home.geekbrains;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * Честно взято с <a href="https://www.javacodex.com/Swing/Calculator">javacodex.com</a>
     */
    public static void main( String[] args )
    {
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setSize(400, 300);
        frame.setResizable(false);


        Container contentPane = frame.getContentPane();
        contentPane.add(new Calculator());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // *** this will center your app ***
        frame.setVisible(true);

    }
}
