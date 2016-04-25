package edu.berkeley.aep.game;

import edu.berkeley.aep.deck.Card;
import edu.berkeley.aep.deck.Rank;
import edu.berkeley.aep.player.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Understands the value of cards in a game of Blackjack
 *
 * Created by jwei on 4/5/16.
 */
public class BlackjackScorer {
    public static int score(Hand hand) {
        List<Integer> scores = generateScores(hand);
        if (scores.size() == 0) {
            return BlackjackEngine.BUST;
        } else {
            return Collections.max(scores);
        }
    }

    public static int softScore(Hand hand) {
        List<Integer> scores = generateScores(hand);
        if (scores.size() == 0) {
            return BlackjackEngine.BUST;
        } else {
            return Collections.min(scores);
        }
    }

    private static List<Integer> generateScores(Hand hand) {
        int nonAceScore = 0;
        int numAces = 0;
        for (Card c : hand) {
            if (c.getRank().equals(Rank.ACE)) {
                numAces++;
            } else {
                nonAceScore += scoreCard(c);
            }
        }
        int[] aceScores = scoreAces(numAces);

        List<Integer> scores = new ArrayList<Integer>();
        for (int aceScore : aceScores) {
            if (nonAceScore + aceScore <= BlackjackEngine.THRESHOLD) {
                scores.add(nonAceScore + aceScore);
            }
        }

        return scores;
    }

    private static int scoreCard(Card c) {
        switch (c.getRank()) {
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case ACE:
                return 11;
            default:
                return -1;
        }
    }

    private static int[] scoreAces(int numAces) {
        if (numAces <= 0 || numAces > 4) {
            return new int[1];
        } else {
            int[] score = { numAces, 10 + numAces };
            return score;
        }
    }
}
