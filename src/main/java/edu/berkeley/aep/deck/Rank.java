package edu.berkeley.aep.deck;

/**
 * Understands the rank of a playing card
 *
 * Created by jwei on 4/5/16.
 */
public enum Rank {
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    NINE("nine"),
    TEN("ten"),
    JACK("jack"),
    QUEEN("queen"),
    KING("king"),
    ACE("ace");

    private final String name;

    Rank(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
