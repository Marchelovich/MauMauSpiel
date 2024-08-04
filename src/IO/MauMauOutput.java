package IO;

import Entity.AbstractPlayer;
import Entity.Card;
import Entity.Statistic;

import java.util.ArrayList;
import java.util.List;

public interface MauMauOutput {
    void showTopCard(Card card);

    void showWinner(AbstractPlayer player);

    void gameIsOver(Statistic statistic);

    void newRound();

    void nextPlayerDrawsCards(AbstractPlayer nextPlayer, int count);

    void showHand(List<Card> hand);

    void showPlayersTurn(AbstractPlayer player, ArrayList<AbstractPlayer> players);

    void showRetrievedCard(Card retrievedCard);

    void incorrectTurn();

    void nextPlayerSkipTurn(AbstractPlayer abstractPlayer);

    void queueChangeDirection();
}
