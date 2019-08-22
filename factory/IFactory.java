package factory;

import good.GoodList;
import player.IPlayer;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public interface IFactory {
    IPlayer createPlayer(Strategy strategy, GoodList deck);
}
