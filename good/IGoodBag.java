package good;

import java.util.List;

/**
 * @project Sheriff of Nottingham
 * @author  Alin-Andrei Georgescu
 * (C)Copyright 2018
 */
interface IGoodBag {
    List<Integer> control();

    void cleanBag();

    int getCountOf(Integer good);

    void addInBag(int good);

    List<Integer> getContent();

    void bribe(int b);

    int getBribe();

    void setDeclaredType(int declaredType);

    int getDeclaredType();

    int getCount();
}
