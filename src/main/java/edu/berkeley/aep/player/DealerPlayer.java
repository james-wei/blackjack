package edu.berkeley.aep.player;

import edu.berkeley.aep.game.BlackjackEngine;
import edu.berkeley.aep.game.BlackjackMove;

/**
 * Understands the playstyle of a Blackjack dealer
 *
 * Created by jwei on 4/9/16.
 */
public class DealerPlayer extends Player {

    private static final int MINSCORE = 17;

    public DealerPlayer(BlackjackEngine engine) {
        super(engine);
    }

    public BlackjackMove play() {
        if (score() == BlackjackEngine.BUST) {
            return BlackjackMove.BUST;
        } else {
            return BlackjackMove.STAND;
        }
    }
}
