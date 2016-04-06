package edu.berkeley.aep.player;

import edu.berkeley.aep.deck.Card;
import edu.berkeley.aep.game.BlackjackScorer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Understands a set of cards in a player's hand.
 *
 * Created by jwei on 4/5/16.
 */
public class Hand implements Iterable<Card> {

    private Collection<Card> cards;

    public Hand() {
        cards = new HashSet<Card>();
    }

    public void add(Card c) {
        cards.add(c);
    }

    public int size() {
        return cards.size();
    }

    public int score() {
        return BlackjackScorer.score(this);
    }

    public int softScore() {
        return BlackjackScorer.softScore(this);
    }

    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
