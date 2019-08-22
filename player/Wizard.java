package player;

import good.Good;
import good.GoodBag;
import good.GoodList;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public class Wizard extends APlayer {
    private static final int FOUR = 4;

    public Wizard(final GoodList deck) {
        super(deck);
    }

    @Override
    public void playMerchant() {
        boolean playedSuperLegal = false;

        for (int i = 0; i < FOUR; ++i) {
            Good asset = GoodList.getValue(i);
            if (freqInHand(i) > 0) {
                playedSuperLegal = true;
                break;
            }
        }

        super.playMerchant();
        if (!playedSuperLegal) {
            addBribe(1);
        }
    }

    @Override
    public void playSherrif(final IPlayer[] opponents) {
        for (IPlayer player : opponents) {
            GoodBag bag = player.getBag();

            if (bag.getCount() < FOUR && bag.getBribe() == 0) {
                for (int good : bag.getContent()) {
                    player.addAssetOnMerchantStand(good);
                    player.addCoins(GoodList.getValue(good).getProfit());
                }
            } else {
                this.controlBag(player);
            }
        }
    }
}
