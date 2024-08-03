package IO;

import Entity.AbstractPlayer;
import Entity.Card;
import Entity.Statistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Entity.Statistic.StatisticParameter.*;

public class ConsoleOutput implements MauMauOutput {
    @Override
    public void showTopCard(Card card) {
        System.out.printf("Top-Karte ist: %s:%s \n", card.getColor(), card.getValue());
    }

    @Override
    public void showWinner(AbstractPlayer player) {
        System.out.printf("Der Spieler: \"%s\" hat gewonnen \n", player.getName());
    }

    @Override
    public void gameIsOver(Statistic statistic) {
        System.out.println("Das Spiel ist beendet");
        HashMap<AbstractPlayer, HashMap<Statistic.StatisticParameter, Integer>> playersStatistic = statistic.getPlayersStatistic();
        for (AbstractPlayer player: playersStatistic.keySet()) {
            HashMap<Statistic.StatisticParameter, Integer> playerStatistic = playersStatistic.get(player);
            System.out.printf("Statistiken von \"%s\": \n", player.getName());
            System.out.printf("Erste Plätze: %s \n", playerStatistic.get(FirstPlaceCount));
            System.out.printf("Zweite Plätze: %s\n", playerStatistic.get(SecondPlaceCount));
            System.out.printf("Werte den Karten: %s \n", playerStatistic.get(ValueRestCards));
        }
    }

    @Override
    public void newRound() {
        System.out.println("Beginn einer neuen Runde");
    }

    @Override
    public void nextPlayerDrawsCards(AbstractPlayer player, int count) {
        System.out.printf("Der Spieler: \"%s\" zieht %s Karten \n", player.getName(), count);
    }

    @Override
    public void showHand(List<Card> hand) {
        System.out.println("Ihre Karten:");
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            System.out.printf("%s: %s:%s \n", i, card.getColor(), card.getValue());
        }
    }

    @Override
    public void showPlayersTurn(AbstractPlayer player, ArrayList<AbstractPlayer> players) {
        System.out.printf("Der Spieler: \"%s\" ist an der Reihe \n", player.getName());
        for (AbstractPlayer element: players) {
            if (element.equals(player)) {
                continue;
            }
            System.out.printf("Der Spieler: \"%s\" hat %s Karten \n", element.getName(), element.getHand().size());
        }
    }

    @Override
    public void showRetrievedCard(Card card) {
        System.out.printf("Sie habem %s:%s herausgenommen \n", card.getColor(), card.getValue());
    }

    @Override
    public void incorrectTurn() {
        System.out.println("Ungültiger Zug. Ziehe eine Karte.");
    }

    @Override
    public void nextPlayerSkipTurn(AbstractPlayer player) {
        System.out.printf("Der nächste Spieler %s ist nicht an der Reihe \n", player.getName());
    }

    @Override
    public void queueChangeDirection() {
        System.out.println("Die Spielwarteschlange ändert die Richtung");
    }
}
