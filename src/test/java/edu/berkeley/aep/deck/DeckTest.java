package edu.berkeley.aep.deck;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created by jwei on 4/5/16.
 */
public class DeckTest {

    private static final int DECK_SIZE = 52;

    @Test
    public void deckShouldHave52Cards() {
        Deck d = new Deck();
        for (int i = 0; i < DECK_SIZE; i++) {
            d.deal();
        }
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotBeAbleToDealFromEmptyDeck() {
        Deck d = new Deck();
        for (int i = 0; i < DECK_SIZE; i++) {
            d.deal();
        }
        d.deal();
    }

    @Test
    public void deckShouldBeShuffled() {
        Deck d1 = new Deck();
        Deck d2 = new Deck();
        int numEqual = 0;

        // Expected number of cards in the same position is 1.
        for (int i = 0; i < DECK_SIZE; i++) {
            if (d1.deal().equals(d2.deal())) {
                numEqual++;
            }
        }
        // Test should pass with high probability
        assertTrue(numEqual < 5);
    }

    @Test
    public void deckShouldHaveUniqueCards() {
        Map<Card, Boolean> cardMap = new HashMap<Card, Boolean>();
        Deck d = new Deck();
        Card c;
        for (int i = 0; i < DECK_SIZE; i++) {
            c = d.deal();
            if (cardMap.containsKey(c)) {
                fail("Deck should not have a duplicate card");
            } else {
                cardMap.put(c, true);
            }
        }
    }

    @Test
    public void allDecksShouldHaveSameCards() {
        Map<Card, Boolean> cardMap = new HashMap<Card, Boolean>();
        Deck d = new Deck();
        for (int i = 0; i < DECK_SIZE; i++) {
            cardMap.put(d.deal(), true);
        }
        Deck d2 = new Deck();
        for (int i = 0; i < DECK_SIZE; i++) {
            if (!cardMap.containsKey(d2.deal())) {
                fail("All decks should contain the same cards");
            }
        }
    }
}
