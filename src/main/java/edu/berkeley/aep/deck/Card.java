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

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Card)) return false;
        Card c = (Card) obj;
        return this.suite.equals(c.suite) && this.rank.equals(c.rank);
    }

    @Override
    public int hashCode() {
        long v = Double.doubleToLongBits(17 * suite.hashCode() * suite.hashCode() + 51 * rank.hashCode());
        return (int)(v^(v>>>32));
    }

    @Override
    public String toString() {
        return rank.toString() + " of " + suite.toString();
    }
}
