package player;

import good.GoodList;

import java.util.ArrayList;
import java.util.List;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public class Bribed extends APlayer {
    private static final int TWO = 2;
    private static final int FIVE = 5;
    private static final int TEN = 10;

    public Bribed(final GoodList deck) {
        super(deck);
    }

    @Override
    public void playMerchant() {
        if (!hasIllegal()) {
            super.playMerchant();
            return;
        }

        if (getCoins() < FIVE) {
            super.playMerchant();
            return;
        }

        cleanBag();

        List<Integer> aux = new ArrayList<>();
        if ((numIllegals() <= TWO) || (getCoins() < TEN)) {
            addBribe(FIVE);
            addCoins(-FIVE);
            for (int good : getAssetsInHand()) {
                if (!GoodList.getValue(good).isLegal()) {
                    addInBag(good);
                    aux.add(good);
                }
            }
            for (Integer good : aux) {
                removeAssetInHand(good);
            }
        } else {
            addBribe(TEN);
            addCoins(-TEN);
            for (int good : getAssetsInHand()) {
                if (!GoodList.getValue(good).isLegal() && getBag().getCount() < FIVE) {
                    addInBag(good);
                    aux.add(good);
                }
            }
            for (Integer good : aux) {
                removeAssetInHand(good);
            }
        }
    }
}
