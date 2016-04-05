package edu.berkeley.aep.deck;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jwei on 4/5/16.
 */
public class CardTest {
    @Test
    public void strReprOfTwoOfClubsShouldBeCorrect() {
        Card c = new Card(Rank.TWO, Suite.CLUBS);
        assertEquals("two of clubs", c.toString());
    }

    @Test
    public void aceOfSpadesShouldEqualAceOfSpades() {
        Card c1 = new Card(Rank.ACE, Suite.SPADES);
        Card c2 = new Card(Rank.ACE, Suite.SPADES);
        assertTrue(c1.equals(c2));
        assertTrue(c2.equals(c1));
    }

    @Test
    public void threeOfDiamondsShouldNotEqualThreeOfHearts() {
        Card c1 = new Card(Rank.THREE, Suite.DIAMONDS);
        Card c2 = new Card(Rank.THREE, Suite.HEARTS);
        assertFalse(c1.equals(c2));
        assertFalse(c2.equals(c1));
    }
}
