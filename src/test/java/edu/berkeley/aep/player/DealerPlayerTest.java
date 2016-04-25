package edu.berkeley.aep.player;

import edu.berkeley.aep.deck.Card;
import edu.berkeley.aep.deck.Rank;
import edu.berkeley.aep.deck.Suite;
import edu.berkeley.aep.game.BlackjackEngine;
import edu.berkeley.aep.game.BlackjackMove;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by jwei on 4/25/16.
 */
public class DealerPlayerTest {
    @Test
    public void dealerShouldBustWithHandValue22() {
        BlackjackEngine mockEngine = Mockito.mock(BlackjackEngine.class);
        DealerPlayer dp = new DealerPlayer(mockEngine);
        dp.takeCard(new Card(Rank.JACK, Suite.SPADES));
        dp.takeCard(new Card(Rank.QUEEN, Suite.SPADES));
        dp.takeCard(new Card(Rank.KING, Suite.SPADES));
        assertEquals(BlackjackMove.BUST, dp.play());
    }

    @Test
    public void dealerShouldStandWithBlackjack() {
        BlackjackEngine mockEngine = Mockito.mock(BlackjackEngine.class);
        DealerPlayer dp = new DealerPlayer(mockEngine);
        dp.takeCard(new Card(Rank.TEN, Suite.SPADES));
        dp.takeCard(new Card(Rank.ACE, Suite.SPADES));
        assertEquals(BlackjackMove.STAND, dp.play());
    }

    @Test
    public void dealerShouldHitWithHandValue10() {
        BlackjackEngine mockEngine = Mockito.mock(BlackjackEngine.class);
        DealerPlayer dp = new DealerPlayer(mockEngine);
        dp.takeCard(new Card(Rank.FOUR, Suite.SPADES));
        dp.takeCard(new Card(Rank.SIX, Suite.SPADES));
        assertEquals(BlackjackMove.HIT, dp.play());
    }
}