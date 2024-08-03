import Entity.*;
import IO.ConsoleInput;
import IO.ConsoleOutput;
import IO.MauMauInput;
import IO.MauMauOutput;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        ArrayList<AbstractPlayer> players = new ArrayList<>();

        players.add(new HumanPlayer("Player 1"));
        players.add(new ComputerPlayer("Computer 1"));

        MauMauOutput output = new ConsoleOutput();
        MauMauInput input = new ConsoleInput();

        if (input.addSecondPlayers()) {
            players.add(new HumanPlayer("Player 2"));
        } else {
            players.add(new ComputerPlayer("Computer 2"));
        }
        Collections.shuffle(players);

        Statistic statistic = new Statistic(players);

        Game game = new Game(deck, players, input, output, statistic);

        while (input.newRound()) {
            game.startNewRound();
        }
    }
}
