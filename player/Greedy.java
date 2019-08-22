package player;

import good.Good;
import good.GoodBag;
import good.GoodList;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public class Greedy extends APlayer {
    public Greedy(final GoodList deck) {
        super(deck);
    }

    @Override
    public void playEvenGreedy() {
        if (hasIllegal()) {
            int max = 0;
            int card = -1;
            for (int  good : getAssetsInHand()) {
                Good asset = GoodList.getValue(good);
                if (!asset.isLegal()) {
                    int profit = asset.getProfit();
                    if (profit > max) {
                        max = profit;
                        card = good;
                    }
                }
            }

            addInBag(card);
            removeAssetInHand(card);
        }
    }

    @Override
    public void playSherrif(final IPlayer[] opponents) {
        for (IPlayer player : opponents) {
            GoodBag bag = player.getBag();
            int bribe = bag.getBribe();
            if (bribe > 0) {
                addCoins(bribe);
                for (int good : bag.getContent()) {
                    player.addAssetOnMerchantStand(good);
                    player.addCoins(GoodList.getValue(good).getProfit());

                    addBonusOfGood(good, player);
                }
            } else {
                this.controlBag(player);
            }
        }
    }
}
