package main;

import java.util.List;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public class GameInput {
    private final List<Integer> mAssetOrder;
    private final List<String> mPlayersOrder;

    public GameInput() {
        mAssetOrder = null;
        mPlayersOrder = null;
    }

    GameInput(final List<Integer> assets, final List<String> players) {
        mAssetOrder = assets;
        mPlayersOrder = players;
    }

    final List<Integer> getAssetIds() {
        return mAssetOrder;
    }

    final List<String> getPlayerNames() {
        return mPlayersOrder;
    }

    public final boolean isValidInput() {
        boolean membersInstantiated = mAssetOrder != null && mPlayersOrder != null;
        boolean membersNotEmpty = mAssetOrder.size() > 0 && mPlayersOrder.size() > 0;

        return membersInstantiated && membersNotEmpty;
    }
}
