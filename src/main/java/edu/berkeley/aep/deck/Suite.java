package edu.berkeley.aep.deck;

/**
 * Understands the suite of a playing card
 *
 * Created by jwei on 4/5/16.
 */
public enum Suite {
    DIAMONDS("diamonds"),
    CLUBS("clubs"),
    HEARTS("hearts"),
    SPADES("spades");

    private final String name;

    Suite(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
