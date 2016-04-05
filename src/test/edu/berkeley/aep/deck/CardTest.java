package edu.berkeley.aep.deck;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by jwei on 4/5/16.
 */
public class CardTest {
    @Test
    public void strReprOfTwoOfClubsShouldBeCorrect() {
        Card c = new Card(Rank.TWO, Suite.CLUBS);
        assertEquals("two of clubs", c.toString());
    }
}
