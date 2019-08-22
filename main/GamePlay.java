package main;

import factory.PlayerFactory;
import factory.Strategy;
import good.GoodList;
import player.IPlayer;

import java.util.Collections;
import java.util.List;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
class GamePlay {
    private static final int THREE = 3;

    private final IPlayer[] players;
    private List<String> playerNames;

    GamePlay(final GameInput gameInput) {
        GoodList deck = new GoodList(gameInput.getAssetIds());

        playerNames = gameInput.getPlayerNames();
        players = new IPlayer[playerNames.size()];
        int i = 0;
        for (String name : playerNames) {
            players[i] = PlayerFactory.INSTANCE.createPlayer(Strategy.valueOf(name), deck);
            ++i;
        }
    }

    void play() {
        int greedyRounds = 0;

        for (int i = 0; i < 2 * players.length; ++i) {
            int currentSherrif = i % players.length;
            IPlayer[] opponents = new IPlayer[players.length - 1];
            int k = 0;

            for (int j = 0; j < players.length; ++j) {
                if (j != currentSherrif) {
                    players[j].playMerchant();
                    if (Strategy.valueOf(playerNames.get(j)).equals(Strategy.greedy)) {
                        ++greedyRounds;
                    }
                    if ((Strategy.valueOf(playerNames.get(j)).equals(Strategy.greedy))
                            && (greedyRounds % 2 == 0)) {
                        players[j].playEvenGreedy();
                    }
                    opponents[k] = players[j];
                    ++k;
                    players[j].refill();
                }
            }

            players[currentSherrif].playSherrif(opponents);
        }
    }

    void calculateScore() {
        int[] numAssetsOfType = new int[players.length];

        for (int i = 0; i <= THREE; ++i) {
            for (int j = 0; j < players.length; ++j) {
                numAssetsOfType[j] = players[j].freqOnStand(i);
            }

            int kingElems = 0;
            int queenElems = 0;
            for (int j = 0; j < players.length; ++j) {
                if (numAssetsOfType[j] > kingElems) {
                    queenElems = kingElems;
                    kingElems =  numAssetsOfType[j];
                }
                if (numAssetsOfType[j] > queenElems && numAssetsOfType[j] < kingElems) {
                    queenElems = numAssetsOfType[j];
                }
            }

            if (kingElems == 0) {
                for (IPlayer player : players) {
                    player.addCoins(GoodList.getValue(i).getKingBonus());
                }
            } else {
                for (int j = 0; j < players.length; ++j) {
                    if (numAssetsOfType[j] == kingElems) {
                        players[j].addCoins(GoodList.getValue(i).getKingBonus());
                    }
                    if (numAssetsOfType[j] == queenElems) {
                        players[j].addCoins(GoodList.getValue(i).getQueenBonus());
                    }
                }
            }
        }
    }

    void printScore() {
        for (int i = 0; i < players.length; ++i) {
            for (int j = 0; j < players.length; ++j) {
                if (players[i].getCoins() > players[j].getCoins()) {
                    IPlayer aux = players[i];
                    players[i] = players[j];
                    players[j] = aux;

                    Collections.swap(playerNames, i, j);
                }
            }
        }

        for (int i = 0; i < players.length; ++i) {
            System.out.println(playerNames.get(i).toUpperCase() + ": " + players[i].getCoins());
        }
    }
}
