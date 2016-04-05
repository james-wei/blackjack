package edu.berkeley.aep.deck;

/**
 * Understands the rank and suite of a playing card.
 *
 * Created by jwei on 4/5/16.
 */
public class Card {
    private Suite suite;
    private Rank rank;

    public Card(Rank r, Suite s) {
        this.suite = s;
        this.rank = r;
    }

    public String toString() {
        return rank.toString() + " of " + suite.toString();
    }
}
