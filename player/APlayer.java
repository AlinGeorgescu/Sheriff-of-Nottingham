package player;

import good.Good;
import good.GoodBag;
import good.GoodList;

import java.util.ArrayList;
import java.util.List;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
abstract class APlayer implements IPlayer {
    private static final int INITIAL_COINS = 50;
    private static final int FOUR = 4;
    private static final int SIX = 6;

    private GoodBag bag;
    private int coins;
    private final List<Integer> assetsInHand;
    private final List<Integer> assetsOnMerchantStand;
    private final GoodList deck;

    APlayer(final GoodList deck) {
        bag = new GoodBag();
        coins = INITIAL_COINS;
        assetsInHand = new ArrayList<>(SIX);
        assetsOnMerchantStand = new ArrayList<>();
        this.deck = deck;
        refill();
    }

    @Override
    public boolean hasIllegal() {
        for (int good : getAssetsInHand()) {
            if (!GoodList.getValue(good).isLegal()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int numIllegals() {
        int n = 0;

        for (int good : getAssetsInHand()) {
            if (!GoodList.getValue(good).isLegal()) {
                ++n;
            }
        }

        return n;
    }

    @Override
    public void refill() {
        while (assetsInHand.size() < SIX) {
            assetsInHand.add(deck.drawCard());
        }
    }

    @Override
    public void playMerchant() {
        int[] freq = new int[FOUR];
        int max = 0;
        int card = -1;

        bag.cleanBag();

        for (int good : assetsInHand) {
            if (good < FOUR) {
                ++freq[good];
            }
        }

        for (int i = 0; i < FOUR; ++i) {
            if (freq[i] > max) {
                max = freq[i];
            }
        }
        int maxProfit = 0;
        for (int good : assetsInHand) {
            if (good < FOUR) {
                int profit = GoodList.getValue(good).getProfit();
                if (freq[good] == max && profit > maxProfit) {
                    card = good;
                    maxProfit = profit;
                    bag.setDeclaredType(good);
                }
            }
        }

        if (card == -1) {
            max = 0;
            for (int good : assetsInHand) {
                int profit = GoodList.getValue(good).getProfit();
                if (profit > max) {
                    max = profit;
                    card = good;
                    bag.setDeclaredType(0);
                }
            }
            bag.addInBag(card);
            assetsInHand.remove(new Integer(card));
        } else {
            while ((assetsInHand.contains(card)) && (bag.getCount() <= FOUR)) {
                bag.addInBag(card);
                assetsInHand.remove(new Integer(card));
            }
        }
    }

    @Override
    public void playEvenGreedy() {
    }

    @Override
    public void addBonusOfGood(final int good, final IPlayer player) {
        Good asset = GoodList.getValue(good);
        if (!asset.isLegal()) {
            for (int i = 0; i < asset.getBonusQuantity(); ++i) {
                player.addAssetOnMerchantStand(asset.getBonusGood());
                player.addCoins(GoodList.getValue(asset.getBonusGood()).getProfit());
            }
        }
    }

    @Override
    public void controlBag(final IPlayer opponent) {
        GoodBag opponentBag = opponent.getBag();
        opponent.addCoins(opponentBag.getBribe());
        List<Integer> confiscated = opponentBag.control();
        int penalty = 0;

        if (confiscated.isEmpty()) {
            Good asset = GoodList.getValue(opponentBag.getDeclaredType());
            penalty = opponentBag.getCount() * asset.getPenalty();
            this.addCoins(-penalty);
            opponent.addCoins(penalty);
        } else {
            for (int good : confiscated) {
                Good asset = GoodList.getValue(good);
                penalty += asset.getPenalty();
                deck.addCard(good);
            }
            this.addCoins(penalty);
            opponent.addCoins(-penalty);
        }

        for (int good : opponentBag.getContent()) {
            opponent.addAssetOnMerchantStand(good);
            opponent.addCoins(GoodList.getValue(good).getProfit());
            addBonusOfGood(good, opponent);
        }
    }

    @Override
    public void playSherrif(final IPlayer[] opponents) {
        for (IPlayer player : opponents) {
            this.controlBag(player);
        }
    }

    @Override
    public int freqInHand(final Integer good) {
        int n = 0;

        for (Integer i : assetsInHand) {
            if (i.equals(good)) {
                ++n;
            }
        }

        return n;
    }

    @Override
    public int freqOnStand(final Integer good) {
        int n = 0;

        for (Integer i : assetsOnMerchantStand) {
            if (i.equals(good)) {
                ++n;
            }
        }

        return n;
    }

    @Override
    public GoodBag getBag() {
        return bag;
    }

    @Override
    public void addInBag(final int good) {
        bag.addInBag(good);
    }

    @Override
    public void cleanBag() {
        bag.cleanBag();
    }

    @Override
    public void addBribe(final int count) {
        bag.bribe(count);
    }

    @Override
    public int getCoins() {
        return coins;
    }

    @Override
    public void addCoins(final int sum) {
        coins += sum;
    }

    @Override
    public List<Integer> getAssetsInHand() {
        return assetsInHand;
    }

    @Override
    public void removeAssetInHand(final Integer good) {
        assetsInHand.remove(good);
    }

    @Override
    public void addAssetOnMerchantStand(final Integer good) {
        assetsOnMerchantStand.add(good);
    }
}
