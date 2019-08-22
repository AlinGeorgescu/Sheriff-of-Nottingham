package good;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public final class GoodList {
    private static final int ZERO_CONST = 0;
    private static final int ONE_CONST = 1;
    private static final int TWO_CONST = 2;
    private static final int THREE_CONST = 3;
    private static final int FOUR_CONST = 4;
    private static final int FIVE_CONST = 5;
    private static final int SEVEN_CONST = 7;
    private static final int EIGHT_CONST = 8;
    private static final int NINE_CONST = 9;
    private static final int TEN_CONST = 10;
    private static final int ELEVEN_CONST = 11;
    private static final int TWELVE_CONST = 12;
    private static final int FIFTEEN_CONST = 15;
    private static final int TWENTY_CONST = 20;
    private static final String APPLE = "Apple";
    private static final String CHEESE = "Cheese";
    private static final String BREAD = "Bread";
    private static final String CHICKEN = "Chicken";
    private static final String SILK = "Silk";
    private static final String PEPPER = "Pepper";
    private static final String BARREL = "Barrel";
    private static final String SILK_BONUS = "3*1";
    private static final String PEPPER_BONUS = "2*3";
    private static final String BARREL_BONUS = "2*2";
    private static final String NOTHING = "Nothing";
    private static final boolean TRUE = true;
    private static final boolean FALSE = false;

    private List<Integer> assetsIds;

    private static final HashMap<Integer, Good> MODIFIABLE_MAP = new HashMap<Integer, Good>() {{
        put(ZERO_CONST, new Good(APPLE, TWENTY_CONST, TEN_CONST, TWO_CONST, TWO_CONST,
                NOTHING, TRUE));
        put(ONE_CONST, new Good(CHEESE, FIFTEEN_CONST, TEN_CONST, THREE_CONST, TWO_CONST,
                NOTHING, TRUE));
        put(TWO_CONST, new Good(BREAD, FIFTEEN_CONST, TEN_CONST, FOUR_CONST, TWO_CONST,
                NOTHING, TRUE));
        put(THREE_CONST, new Good(CHICKEN, TEN_CONST, FIVE_CONST, FOUR_CONST, TWO_CONST,
                NOTHING, TRUE));
        put(TEN_CONST, new Good(SILK, ZERO_CONST, ZERO_CONST, NINE_CONST, FOUR_CONST,
                SILK_BONUS, FALSE));
        put(ELEVEN_CONST, new Good(PEPPER, ZERO_CONST, ZERO_CONST, EIGHT_CONST, FOUR_CONST,
                PEPPER_BONUS, FALSE));
        put(TWELVE_CONST, new Good(BARREL, ZERO_CONST, ZERO_CONST, SEVEN_CONST, FOUR_CONST,
                BARREL_BONUS, FALSE));
    }};
    
    private static final Map DECODE = Collections.unmodifiableMap(MODIFIABLE_MAP);

    public GoodList(final List<Integer> assetsIds) {
        this.assetsIds = assetsIds;
    }

    public int drawCard() {
        if (assetsIds.isEmpty()) {
            return -1;
        } else {
            int good = assetsIds.get(0);
            assetsIds.remove(0);
            return good;
        }
    }

    public void addCard(final int good) {
        assetsIds.add(good);
    }

    public static Good getValue(final int key) {
        return (Good) DECODE.get(key);
    }
}
