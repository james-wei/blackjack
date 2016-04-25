package edu.berkeley.aep.game;

import edu.berkeley.aep.deck.Card;
import edu.berkeley.aep.deck.Deck;
import edu.berkeley.aep.player.DealerPlayer;
import edu.berkeley.aep.player.HumanPlayer;
import edu.berkeley.aep.player.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Understands the events in a game of Blackjack
 *
 * Created by jwei on 4/9/16.
 */
public class BlackjackEngine {

    public static final int THRESHOLD = 21;
    public static final int BUST = 22;

    private Player dealer;
    private Player human;
    private Deck deck;

    private int humanWin;
    private int dealerWin;
    private int tieGames;

    public static void main(String[] args) {
        BlackjackEngine bje = new BlackjackEngine();
        bje.run();
    }

    public BlackjackEngine() {
        humanWin = 0;
        dealerWin = 0;
        tieGames = 0;
        resetState();
    }

    public void runGame() {
        startNewGame();
        playTurn(human);
        playTurn(dealer);
        System.out.println("Human score: " + Integer.toString(human.score()));
        System.out.println("Dealer score: " + Integer.toString(dealer.score()));
    }

    public void run() {
        printWelcomeMsg();
        boolean keepPlaying = true;
        while (keepPlaying) {
            runGame();
            updateScore();
            printScore();
            keepPlaying = continuePlaying();
        }
        System.out.println("\nThanks for playing!");
    }

    public Card opponentFaceUp(Player p) {
        if (p != null) {
            return other(p).faceUp();
        } else {
            return null;
        }
    }

    public int opponentScore(Player p) {
        if (p != null) {
            return other(p).score();
        } else {
            return -1;
        }
    }

    private Player other(Player p) {
        if (p.equals(dealer)) {
            return human;
        } else if (p.equals(human)) {
            return dealer;
        } else {
            return null;
        }
    }

    private void printWelcomeMsg() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("You will be playing a series of games against a " +
                "dealer. Feel free to stop at any time.\n");
    }

    private void updateScore() {
        if (human.score() == dealer.score()) {
            tieGames++;
        } else if (human.score() == BUST && dealer.score() != BUST) {
            dealerWin++;
        } else if (dealer.score() == BUST && human.score() != BUST) {
            humanWin++;
        } else if (dealer.score() > human.score()) {
            dealerWin++;
        } else {
            humanWin++;
        }
    }

    private void printScore() {
        System.out.println("The overall score is now:");
        System.out.println("\tYour wins: " + Integer.toString(humanWin));
        System.out.println("\tDealer wins: " + Integer.toString(dealerWin));
        System.out.println("\tTies: " + Integer.toString(tieGames));
    }

    private boolean continuePlaying() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char continueStr;
        while (true) {
            try {
                System.out.println("Would you like to keep playing? Type 'Y' to" +
                                   " continue or 'N' to stop playing.");
                continueStr = br.readLine().toLowerCase().charAt(0);
                switch (continueStr) {
                    case 'y':
                        return true;
                    case 'n':
                        return false;
                    default:
                        throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("An error occurred while processing your input. Please try again.");
            }
        }
    }

    private void startNewGame() {
        resetState();
        dealStartingHands();
    }

    private void resetState() {
        dealer = new DealerPlayer(this);
        human = new HumanPlayer(this);
        deck = new Deck();
    }

    private void dealStartingHands() {
        human.takeCard(deck.deal());
        dealer.takeCard(deck.deal());
        human.takeCard(deck.deal());
        dealer.takeCard(deck.deal());
    }

    private void playTurn(Player p) {
        BlackjackMove move;
        while (true) {
            move = p.play();
            switch(move) {
                case HIT:
                    p.takeCard(deck.deal());
                    break;
                case STAND:
                case BUST:
                    return;
            }
        }
    }
}
