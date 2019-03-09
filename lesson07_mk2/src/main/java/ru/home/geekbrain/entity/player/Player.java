package ru.home.geekbrain.entity.player;

import ru.home.geekbrain.controller.implementation.Resetable;
import ru.home.geekbrain.entity.UserTurnResult;

import java.awt.*;
import java.util.Comparator;
import java.util.function.Consumer;

public abstract class Player implements Comparator<Player>{

    protected int id;

    // Функция обратного вызова для GameCtrl о том, что игрок сделал ход
    private Consumer<UserTurnResult> turn;

    public Player(int id, Consumer<UserTurnResult> turn, Resetable reset) {
        this.id = id;

        // Даем игроку функцию обратно вызова, чтобы он мог оповестить
        // GameCtrl о том, что он сделал ход
        this.turn = turn;
    }

    public int getId() {
        return id;
    }



    /**
     * Display map to player
     */
    public abstract void consumeMap(int[][] map);



    /**
     * Display message to player
     */
    public abstract void consumeMessage(String message);


    /**
     * Делай ход
     */
    public abstract void startTurn();

    /**
     * Жди
     */
    public void waitForTurn() {}

    

    /**
     * Отправляем результат хода GameCtrl
     */
    protected void sendTurn(Point point) {

        UserTurnResult res = new UserTurnResult(id, point);
        turn.accept(res);
    }


    @Override
    public int compare(Player p1, Player p2) {
        return Integer.compare(p1.getId(), p2.getId());
    }



}
