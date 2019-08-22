package factory;

import good.GoodList;
import player.IPlayer;
import player.Basic;
import player.Bribed;
import player.Greedy;
import player.Wizard;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public final class PlayerFactory implements IFactory {
    public static final PlayerFactory INSTANCE = new PlayerFactory();

    private PlayerFactory() {
    }

    @Override
    public IPlayer createPlayer(final Strategy strategy, final GoodList deck) {
        switch (strategy) {
            case bribed:
                return new Bribed(deck);

            case basic:
                return new Basic(deck);

            case greedy:
                return new Greedy(deck);

            default:
                return new Wizard(deck);
        }
    }
}
