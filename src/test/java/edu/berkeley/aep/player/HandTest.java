package edu.berkeley.aep.player;

import edu.berkeley.aep.deck.Card;
import edu.berkeley.aep.deck.Rank;
import edu.berkeley.aep.deck.Suite;
import edu.berkeley.aep.game.BlackjackScorer;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Created by jwei on 4/5/16.
 */
public class HandTest {
    private static Suite[] suits = Suite.values();

    @Test
    public void handShouldStartEmpty() {
        Hand h = new Hand();
        for (Card c : h) fail("Hand should be empty");
    }

    @Test
    public void handShouldCorrectlyAddCards() {
        Card c1 = new Card(Rank.TWO, Suite.DIAMONDS);
        Card c2 = new Card(Rank.FOUR, Suite.HEARTS);
        Card c3 = new Card(Rank.KING, Suite.SPADES);
        Card c4 = new Card(Rank.JACK, Suite.CLUBS);

        Hand h = new Hand();
        h.add(c1);
        h.add(c2);
        h.add(c3);
        h.add(c4);

        Map<Card, Boolean> cardMap = new HashMap<Card, Boolean>();
        cardMap.put(c1, false);
        cardMap.put(c2, false);
        cardMap.put(c3, false);
        cardMap.put(c4, false);

        for (Card c : h) {
            if (!cardMap.containsKey(c)) {
                fail("Hand should not contain a card that was not added");
            }
            if (cardMap.get(c)) {
                fail("Hand should not contain duplicate card");
            }
            cardMap.put(c, true);
        }
    }

    @Test
    public void scoreOf4QShouldBe14() {
        Rank[] values = {Rank.QUEEN, Rank.FOUR};
        Hand h = createHand(values);
        assertEquals(14, h.score());
        assertEquals(14, h.softScore());
    }

    @Test
    public void scoreOfQKShouldBe20() {
        Rank[] values = {Rank.QUEEN, Rank.KING};
        Hand h = createHand(values);
        assertEquals(20, h.score());
        assertEquals(20, h.softScore());
    }

    @Test
    public void scoreOfJQKShouldBeBust() {
        Rank[] values = {Rank.JACK, Rank.QUEEN, Rank.KING};
        Hand h = createHand(values);
        assertEquals(BlackjackScorer.BUST, h.score());
        assertEquals(BlackjackScorer.BUST, h.softScore());
    }

    @Test
    public void scoreOf4AShouldBe15() {
        Rank[] values = {Rank.FOUR, Rank.ACE};
        Hand h = createHand(values);
        assertEquals(15, h.score());
    }

    @Test
    public void softScoreOf4AShouldBe5() {
        Rank[] values = {Rank.FOUR, Rank.ACE};
        Hand h = createHand(values);
        assertEquals(5, h.softScore());
    }

    @Test
    public void scoreOfAAShouldBe12() {
        Rank[] values = {Rank.ACE, Rank.ACE};
        Hand h = createHand(values);
        assertEquals(12, h.score());
    }

    @Test
    public void softScoreOfAAShouldBe2() {
        Rank[] values = {Rank.ACE, Rank.ACE};
        Hand h = createHand(values);
        assertEquals(2, h.softScore());
    }

    @Test
    public void scoreOfAA10ShouldBe12() {
        Rank[] values = {Rank.ACE, Rank.ACE, Rank.TEN};
        Hand h = createHand(values);
        assertEquals(12, h.score());
        assertEquals(12, h.softScore());
    }

    private Hand createHand(Rank[] values) {
        Hand h = new Hand();
        Card c;
        int i = 0;
        for (Rank r : values) {
            c = new Card(r, suits[i]);
            h.add(c);
            i = (i + 1) % (suits.length);
        }
        return h;
    }
}
