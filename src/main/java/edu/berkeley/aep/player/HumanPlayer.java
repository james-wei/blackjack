package edu.berkeley.aep.player;

import edu.berkeley.aep.deck.Card;
import edu.berkeley.aep.game.BlackjackEngine;
import edu.berkeley.aep.game.BlackjackMove;

import java.io.BufferedReader;
import java.io.IOException;
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
    private boolean catchExceptions;

    public HumanPlayer(BlackjackEngine engine) {
        this(engine, System.in, false, true);
    }

    protected HumanPlayer(BlackjackEngine engine, InputStream in, boolean mute,
                       boolean catchExceptions) {
        super(engine);
        this.in = in;
        this.mute = mute;
        this.catchExceptions = catchExceptions;
    }

    public BlackjackMove play() {
        if (!mute) printGameState();
        if (score() == BlackjackEngine.BUST) {
            if (!mute) System.out.println("Oops, you've busted!");
            return BlackjackMove.BUST;
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while (true) {
                try {
                    if (!mute) System.out.println("What would you like to do? Type 'H' to hit or 'S' to stand.");
                    return interpretUserInput(br.readLine());
                } catch (Exception e) {
                    if (!mute) System.out.println("An error occurred while processing your input. Please try again.");
                    if (!catchExceptions) throw new RuntimeException(e);
                }
            }
        }
    }

    private void printGameState() {
        System.out.println("Dealer's face-up card: " + engine.opponentFaceUp(this).toString());
        System.out.println("Your hand:");
        for (Card c : hand) {
            System.out.println("\t" + c.toString());
        }
    }

    private BlackjackMove interpretUserInput(String input) throws IOException {
        char moveStr = input.toLowerCase().charAt(0);
        switch (moveStr) {
            case 'h':
                return BlackjackMove.HIT;
            case 's':
                return BlackjackMove.STAND;
            default:
                return readWords(input);
        }
    }

    private BlackjackMove readWords(String input) throws IOException {
        input = input.toLowerCase();
        boolean hit = input.contains("hit");
        boolean stand = input.contains("stand");
        if (hit && !stand) {
            return BlackjackMove.HIT;
        } else if (!hit && stand) {
            return BlackjackMove.STAND;
        } else {
            throw new IOException("Invalid input: " + input);
        }
    }
}
