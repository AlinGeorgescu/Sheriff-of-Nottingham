package good;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public final class Good {
    private static final String NOTHING = "Nothing";
    private final String good;
    private final String bonusQuantityAndGood;
    private final int kingBonus;
    private final int queenBonus;
    private final int profit;
    private final int penalty;
    private final boolean isLegal;

    Good(final String good, final int kingBonus, final int queenBonus, final int profit,
                final int penalty, final String bonusQuantityAndGood, final boolean isLegal) {
        this.good = good;
        this.kingBonus = kingBonus;
        this.queenBonus = queenBonus;
        this.profit = profit;
        this.penalty = penalty;
        this.bonusQuantityAndGood = bonusQuantityAndGood;
        this.isLegal = isLegal;
    }

    public String getGood() {
        return good;
    }

    public int getKingBonus() {
        return kingBonus;
    }

    public int getQueenBonus() {
        return queenBonus;
    }

    public int getProfit() {
        return profit;
    }

    public int getPenalty() {
        return penalty;
    }

    public int getBonusQuantity() {
        if (!bonusQuantityAndGood.equals(NOTHING)) {
            return Integer.parseInt(bonusQuantityAndGood.substring(0, 1));
        } else {
            return 0;
        }
    }

    public int getBonusGood() {
        if (!bonusQuantityAndGood.equals(NOTHING)) {
            return Integer.parseInt(bonusQuantityAndGood.substring(2));
        } else {
            return 0;
        }
    }

    public boolean isLegal() {
        return isLegal;
    }
}
