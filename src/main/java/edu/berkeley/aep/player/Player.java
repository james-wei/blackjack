package edu.berkeley.aep.player;

import edu.berkeley.aep.deck.Card;
import edu.berkeley.aep.game.BlackjackEngine;
import edu.berkeley.aep.game.BlackjackMove;

/**
 * Understands the playstyle of a Blackjack player
 *
 * Created by jwei on 4/5/16.
 */
public abstract class Player {

    protected Hand hand;
    protected Card faceUp;
    protected BlackjackEngine engine;

    public Player(BlackjackEngine engine) {
        this.hand = new Hand();
        this.faceUp = null;
        this.engine = engine;
    }

    public void takeCard(Card c) {
        hand.add(c);
        if (hand.size() == 1) {
            faceUp = c;
        }
    }

    public Card faceUp() {
        return faceUp;
    }

    public int score() {
        return hand.score();
    }

    protected int softScore() {
        return hand.softScore();
    }

    public abstract BlackjackMove play();
}
