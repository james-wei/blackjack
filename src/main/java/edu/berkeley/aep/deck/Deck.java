package edu.berkeley.aep.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Understands a shuffled deck of playing cards.
 *
 * Created by jwei on 4/5/16.
 */
public class Deck {
    private Iterator<Card> deck;

    public Deck() {
        List<Card> deckList = new ArrayList<Card>();
        for (Suite s : Suite.values()) {
            for (Rank r : Rank.values()) {
                deckList.add(new Card(r, s));
            }
        }
        Collections.shuffle(deckList);
        this.deck = deckList.iterator();
    }

    public Card deal() {
        if (deck.hasNext()) {
            return deck.next();
        } else {
            throw new RuntimeException("Cannot deal from an empty deck");
        }
    }
}
