package edu.berkeley.aep.player;

import edu.berkeley.aep.deck.Card;
import edu.berkeley.aep.deck.Rank;
import edu.berkeley.aep.deck.Suite;
import edu.berkeley.aep.game.BlackjackEngine;
import edu.berkeley.aep.game.BlackjackMove;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by jwei on 4/25/16.
 */
public class HumanPlayerTest {

    private BlackjackEngine mockEngine;
    private Card dealerFaceUp;
    private String userInput;
    private InputStream is;
    private HumanPlayer hp;

    @Before
    public void initMocks() {
        dealerFaceUp = new Card(Rank.ACE, Suite.SPADES);
        mockEngine = Mockito.mock(BlackjackEngine.class);
        when(mockEngine.opponentFaceUp(any(Player.class))).thenReturn(dealerFaceUp);
    }

    @Test(timeout=100)
    public void characterHShouldYieldHitMove() {
        userInput = "H\n";
        is = new ByteArrayInputStream(userInput.getBytes());
        hp = new HumanPlayer(mockEngine, is, true, false);
        assertEquals(BlackjackMove.HIT, hp.play());
        userInput = "h\n";
        is = new ByteArrayInputStream(userInput.getBytes());
        hp = new HumanPlayer(mockEngine, is, true, false);
        assertEquals(BlackjackMove.HIT, hp.play());
    }

    @Test(timeout=100)
    public void characterSShouldYieldStandMove() {
        userInput = "S\n";
        is = new ByteArrayInputStream(userInput.getBytes());
        hp = new HumanPlayer(mockEngine, is, true, false);
        assertEquals(BlackjackMove.STAND, hp.play());
        userInput = "s\n";
        is = new ByteArrayInputStream(userInput.getBytes());
        hp = new HumanPlayer(mockEngine, is, true, false);
        assertEquals(BlackjackMove.STAND, hp.play());
    }

    @Test(timeout=100)
    public void bustedHandShouldYieldBustMove() {
        userInput = "S\n";
        is = new ByteArrayInputStream(userInput.getBytes());
        hp = new HumanPlayer(mockEngine, is, true, false);
        hp.takeCard(new Card(Rank.JACK, Suite.SPADES));
        hp.takeCard(new Card(Rank.QUEEN, Suite.SPADES));
        hp.takeCard(new Card(Rank.KING, Suite.SPADES));
        assertEquals(BlackjackMove.BUST, hp.play());
    }

    @Test(timeout=100)
    public void sentenceWithWordHitShouldYieldHitMove() {
        userInput = "I would like to hit, please.";
        is = new ByteArrayInputStream(userInput.getBytes());
        hp = new HumanPlayer(mockEngine, is, true, false);
        assertEquals(BlackjackMove.HIT, hp.play());
    }

    @Test(timeout=100)
    public void sentenceWithWordStandShouldYieldHitMove() {
        userInput = "I would like to stand, please.";
        is = new ByteArrayInputStream(userInput.getBytes());
        hp = new HumanPlayer(mockEngine, is, true, false);
        assertEquals(BlackjackMove.STAND, hp.play());
    }

    @Test(timeout=100, expected = RuntimeException.class)
    public void garbledInputShouldThrowCaughtException() {
        userInput = "jfalkdifqwerqwenivj";
        is = new ByteArrayInputStream(userInput.getBytes());
        hp = new HumanPlayer(mockEngine, is, true, false);
        hp.play();
    }

    @Test(timeout=100, expected = RuntimeException.class)
    public void indecisiveInputShouldThrowCaughtException() {
        userInput = "I would like to hit and stand at the same time.";
        is = new ByteArrayInputStream(userInput.getBytes());
        hp = new HumanPlayer(mockEngine, is, true, false);
        hp.play();
    }
}
