package ru.home.geekbrain.gui;

import javax.swing.*;

public class GameButton extends JButton {

    private int buttonIndex;

    public GameButton(int buttonIndex) {
        this.buttonIndex = buttonIndex;
    }

    public int getButtonIndex() {
        return buttonIndex;
    }
}
