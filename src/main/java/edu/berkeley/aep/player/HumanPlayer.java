package edu.berkeley.aep.player;

import edu.berkeley.aep.deck.Card;
import edu.berkeley.aep.game.BlackjackEngine;
import edu.berkeley.aep.game.BlackjackMove;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Understands the playstyle of a human Blackjack player
 *
 * Created by jwei on 4/9/16.
 */
public class HumanPlayer extends Player {

    private InputStream in;
    private boolean mute;

    public HumanPlayer(BlackjackEngine engine) {
        this(engine, System.in, false);
    }

    public HumanPlayer(BlackjackEngine engine, InputStream in, boolean mute) {
        super(engine);
        this.in = in;
        this.mute = mute;
    }

    public BlackjackMove play() {
        if (!mute) printGameState();
        BlackjackMove move = null;

        if (score() == BlackjackEngine.BUST) {
            if (!mute) System.out.println("Oops, you've busted!");
            move = BlackjackMove.BUST;
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            char moveStr;
            while (move == null) {
                try {
                    if (!mute) System.out.println("What would you like to do? Type 'H' to hit or 'S' to stand.");
                    moveStr = br.readLine().toLowerCase().charAt(0);
                    switch (moveStr) {
                        case 'h':
                            move = BlackjackMove.HIT;
                            break;
                        case 's':
                            move = BlackjackMove.STAND;
                            break;
                        default:
                            throw new Exception();
                    }
                } catch (Exception e) {
                    if (!mute) System.out.println("An error occurred while processing your input. Please try again.");
                }
            }
        }
        return move;
    }

    private void printGameState() {
        System.out.println("Dealer's face-up card: " + engine.opponentFaceUp(this).toString());
        System.out.println("Your hand:");
        for (Card c : hand) {
            System.out.println("\t" + c.toString());
        }
    }
}
