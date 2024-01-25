package org.acme;

public enum Cours {
    Null(0),
    Anglais(1),
    Mathematiques(2),
    SVT(3),
    Histoires(4),
    Sport(5);

    public final int value;

    Cours(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Cours getCours(int value) {
        for (Cours cours : values()) {
            if (cours.value == value) {
                return cours;
            }
        }
        return Cours.Null;
    }
}