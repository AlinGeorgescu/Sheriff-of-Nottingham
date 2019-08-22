package player;

import good.GoodBag;

import java.util.List;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public interface IPlayer {
    boolean hasIllegal();

    int numIllegals();

    void refill();

    void playMerchant();

    void playEvenGreedy();

    void addBonusOfGood(int good, IPlayer player);

    void controlBag(IPlayer opponent);

    void playSherrif(IPlayer[] opponents);

    int freqInHand(Integer good);

    int freqOnStand(Integer good);

    GoodBag getBag();

    void addInBag(int good);

    void cleanBag();

    void addBribe(int count);

    int getCoins();

    void addCoins(int sum);

    List<Integer> getAssetsInHand();

    void removeAssetInHand(Integer good);

    void addAssetOnMerchantStand(Integer good);
}
