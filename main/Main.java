package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public final class Main {
    private Main() {
    }

    private static final class GameInputLoader {
        private final String mInputPath;

        private GameInputLoader(final String path) {
            mInputPath = path;
        }

        GameInput load() {
            List<Integer> assetsIds = new ArrayList<>();
            List<String> playerOrder = new ArrayList<>();

            try {
                BufferedReader inStream = new BufferedReader(new FileReader(mInputPath));
                String assetIdsLine = inStream.readLine().replaceAll("[\\[\\] ']", "");
                String playerOrderLine = inStream.readLine().replaceAll("[\\[\\] ']", "");

                for (String strAssetId : assetIdsLine.split(",")) {
                    assetsIds.add(Integer.parseInt(strAssetId));
                }

                playerOrder.addAll(Arrays.asList(playerOrderLine.split(",")));
                inStream.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return new GameInput(assetsIds, playerOrder);
        }
    }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0]);
        GameInput gameInput = gameInputLoader.load();

        GamePlay game = new GamePlay(gameInput);
        game.play();
        game.calculateScore();
        game.printScore();
    }
}
