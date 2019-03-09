package ru.home.geekbrain.gui;

import ru.home.geekbrain.controller.implementation.Resetable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainForm extends JFrame implements MainFormApi {

    private boolean haveInit;

    private JPanel gameBoardPanel;
    private JTextField statusBar;
    private List<GameButton> buttonList = new ArrayList<>();
    private int dimension;

    private Consumer<Point> turn;

    public MainForm(int id, Consumer<Point> turn, Resetable reset) {

        haveInit = false;
        this.turn = turn;

        super.setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null); // *** this will center your app ***
        Point tmp = getLocation();
        setLocation(tmp.x + (id-1) * 450 - 200, tmp.y);


        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.Y_AXIS));
        controlPanel.setSize(400, 15);
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(actionEvent -> reset.doit());
        controlPanel.add(newGameButton);

        gameBoardPanel = new JPanel();

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.Y_AXIS));
        statusPanel.setSize(400, 15);
        statusBar = new JFormattedTextField();
        statusPanel.add(statusBar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameBoardPanel, BorderLayout.CENTER);
        getContentPane().add(statusPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void setTitle(String title) {

        super.setTitle("Крестики-нолики: " +  title);
    }

    @Override
    public void setStatus(String text) {
        statusBar.setText(text);
        setVisible(true);
    }

    @Override
    public void showMessage(String text) {

        JOptionPane.showMessageDialog(this, text);
    }

    @Override
    public void displayMap(int[][] map) {

        init(map);

        dimension = map.length;

        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {

                int index = i + j * dimension;

                String value;
                if (map[i][j] == 1)
                    value = "X";
                else if (map[i][j] == 2)
                    value = "O";
                else
                    value = "";

                buttonList.get(index).setText(value);
            }
        }
    }



    private void init(int[][] map) {

        if (haveInit)
            return;

        haveInit = true;

        dimension = map.length;
        int cellSize = 150;

        gameBoardPanel.setLayout(new GridLayout(dimension, dimension));
        gameBoardPanel.setSize(cellSize * dimension, cellSize * dimension);

        for(int i = 0; i < dimension * dimension; i++) {

            GameButton button = new GameButton(i);
            button.setFont(new Font("Arial", Font.BOLD, 40));
            button.addActionListener(this::buttonClicked);
            gameBoardPanel.add(button);
            buttonList.add(button);
        }
        setVisible(true);
    }


    private void buttonClicked(ActionEvent e) {

        int index = ((GameButton)e.getSource()).getButtonIndex();
        Point t = new Point();
        t.y = index / dimension;
        t.x = index % dimension;
        turn.accept(t);
    }




}
