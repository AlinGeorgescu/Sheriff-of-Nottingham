package good;

import java.util.ArrayList;
import java.util.List;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
public final class GoodBag implements IGoodBag {
    private List<Integer> inBag;
    private int bribe;
    private int declaredType;

    public GoodBag() {
        inBag = new ArrayList<>();
    }

    @Override
    public List<Integer> control() {
        List<Integer> confiscated = new ArrayList<>();

        for (int good : inBag) {
            if (good != declaredType) {
                confiscated.add(good);
            }
        }
        inBag.removeAll(confiscated);

        return confiscated;
    }

    @Override
    public void cleanBag() {
        inBag.clear();
        bribe = 0;
        declaredType = 0;
    }

    @Override
    public int getCountOf(final Integer good) {
        int n = 0;

        for (Integer i : inBag) {
            if (i.equals(good)) {
                ++n;
            }
        }

        return n;
    }

    @Override
    public void addInBag(final int good) {
        inBag.add(good);
    }

    @Override
    public List<Integer> getContent() {
        return inBag;
    }

    @Override
    public void bribe(final int b) {
        this.bribe = b;
    }

    @Override
    public int getBribe() {
        return bribe;
    }

    @Override
    public void setDeclaredType(final int declaredType) {
        this.declaredType = declaredType;
    }

    @Override
    public int getDeclaredType() {
        return declaredType;
    }

    @Override
    public int getCount() {
        return inBag.size();
    }
}
