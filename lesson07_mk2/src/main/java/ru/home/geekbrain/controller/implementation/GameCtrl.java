package ru.home.geekbrain.controller.implementation;

import ru.home.geekbrain.controller.BoardApi;
import ru.home.geekbrain.entity.player.AIPlayer;
import ru.home.geekbrain.entity.player.HumanPlayer;
import ru.home.geekbrain.entity.player.Player;
import ru.home.geekbrain.entity.UserTurnResult;


import java.awt.*;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameCtrl implements Runnable {

    private List<Player> playerList;
    private BoardApi boardApi;

    // с помощью этой блокировки
    // будем ождать пользователя, пока он походит
    private final Lock lock = new ReentrantLock();
    private final Condition haveTurn = lock.newCondition();

    // результат хода игрока
    private Point userResult;
    private Player currentPlayer;
    private boolean reset;


    public GameCtrl() {

        boardApi = new BoardCtrl();

        playerList = new ArrayList<>();


        //id = 1 - ходит первым за X
        //id = 2 - ходит вторым за O

        // Человек
        playerList.add(new HumanPlayer(2, this::getUserTurn, this::resetGame));

        // Компутер
        playerList.add(new AIPlayer(1, this::getUserTurn, null));

        // Можно Компутер заменить на Второго человека

        // Второй человек
        //playerList.add(new HumanPlayer(1, this::getUserTurn, this::resetGame));




        playerList.sort((player, t1) -> player.compare(player, t1));


    }

    @Override
    public void run() {

        //noinspection InfiniteLoopStatement
        while (true)
            newGame();
    }

    private void newGame() {

        // 0. Display map

        // 1. Ask user to move

        // 2. Display results

        // 3. Check endgame

        boardApi.clear();

        boolean win;
        boolean draw;
        reset = false;

        // 2. Display results - отправляем игрокам карту
        broadcastMap();

        while (true) {



            for (Player player : playerList) {

                // 1. Ask user to move - просим игрока походить
                // другого просим подождать ждет
                newTurn(player);

                // 2. Display results - отправляем игрокам карту
                broadcastMap();


                // 3. Check endgame win
                win = boardApi.checkWin(player.getId());

                if (win) {
                    player.consumeMessage("You are winner");

                    for (Player tmp : playerList) {
                        if (tmp!= player)
                            tmp.consumeMessage("You are looser");
                    }
                }

                // 3. Check endgame draw
                draw = boardApi.isFull();

                if (!win && draw)
                    for (Player tmp : playerList) 
                        tmp.consumeMessage("Draw");

                if (win || draw || reset)
                    return;
            }

        }

    }




    // новый ход
    private void newTurn(Player player) {

        for (Player tmp : playerList) {

            if (tmp != player)
                tmp.waitForTurn();


        }
        
        askUserToTurn(player);
    }
    

    /**
     * Просим игрока походить и ждем его хода
     */
    private void askUserToTurn(Player player) {

        // 1. Просим игрока походить (ждем с java.concurrent-приседушками)

        lock.lock(); // вход в критическую секцию -----------------------

        currentPlayer = player;
        userResult = null;

        // давай ходи
        player.startTurn();


        while (userResult == null && !reset) {

            try {
                // Тут ожидаем хода пользователя
                haveTurn.await();
            } catch (InterruptedException ignore) {}
        }


        lock.unlock(); // выход из критической секции --------------------
    }

    private void broadcastMap() {

        for (Player player : playerList)
                player.consumeMap(boardApi.getMap());

    }



    /**
     * "Этот код выполняется в потоке Player
     */
    private void getUserTurn(UserTurnResult res) {

        // Если нам ответил тот игрок, чей ход мы ждем (а не юзер просто так кнопки тыкал, пока комп ходит)
        // И туда можно ходить
        if (currentPlayer.getId() == res.id &&
            boardApi.isEmpty(res.point)) {

            lock.lock(); // вход в критическую секцию -----------------------

            userResult = res.point;

            // Сохраняем в map
            boardApi.set(userResult, res.id);
            
            // Сигналим GameCtrl о том, что пользователь сделал ход (там отпустит haveTurn.await())
            haveTurn.signal();

            lock.unlock(); // выход из критической секции --------------------
        }

        // Не оповещаем игрока о неверном (влом)
    }

    private void resetGame() {

        lock.lock();
        reset = true;
        haveTurn.signal();
        lock.unlock();
    }

}
