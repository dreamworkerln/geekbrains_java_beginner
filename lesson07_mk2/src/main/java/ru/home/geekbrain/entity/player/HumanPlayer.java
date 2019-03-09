package ru.home.geekbrain.entity.player;

import ru.home.geekbrain.controller.implementation.Resetable;
import ru.home.geekbrain.entity.UserTurnResult;
import ru.home.geekbrain.gui.MainForm;
import ru.home.geekbrain.gui.MainFormApi;

import java.awt.*;
import java.util.function.Consumer;

public class HumanPlayer extends Player {

    private MainFormApi form;


    public HumanPlayer(int id, Consumer<UserTurnResult> turn, Resetable reset) {
        super(id, turn, reset);

        form = new MainForm(id, this::getTurnFromGUI, reset);

        form.setTitle(" игрок" + id);
    }

    /**
     * Сюда приедет оповещение от формы о нажатии кнопки
     */
    private void getTurnFromGUI(Point point) {

        sendTurn(point);
    }


    @Override
    public void startTurn() {

        form.setStatus("Давай ходи");
    }

    @Override
    public void waitForTurn() {

        form.setStatus("Ход врага");
    }

    @Override
    public void consumeMap(int[][] map) {

        form.displayMap(map);
    }

    @Override
    public void consumeMessage(String message) {

        form.showMessage(message);
    }
}
