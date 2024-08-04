import Entity.*;
import IO.MauMauInput;
import IO.MauMauOutput;

import java.util.ArrayList;
import java.util.List;

public class Game {

    static final int HUMAN_SKIP_TURN_CHOICE = -1;

    private final Deck deck;
    private final ArrayList<AbstractPlayer> players;
    private final ArrayList<AbstractPlayer> finishedPlayers;
    private Card topCard = null;
    private Card priviosCard = null;
    private final MauMauInput input;
    private final MauMauOutput output;
    private int currentPlayerIndex = 0;
    private int gamesCount = 0;
    private boolean queueDirection = true;
    private final Statistic statistic;

    public Game(Deck deck, ArrayList<AbstractPlayer> players, MauMauInput input, MauMauOutput output, Statistic statistic) {
        this.deck = deck;
        this.players = players;
        this.input = input;
        this.output = output;
        this.statistic = statistic;
        finishedPlayers = new ArrayList<>();
        initializeUserHand();
        start();
    }

    private void initializeUserHand() {
        for (AbstractPlayer player : players) {
            for (int i = 0; i < 5; i++) {
                player.drawCard(deck.drawCard());
            }
        }
    }

    private void setNewTopCard(Card card) {
        priviosCard = topCard;
        topCard = card;
    }

    private AbstractPlayer getNextPlayer() {
        return players.get(getNextPlayerIndex());
    }

    private AbstractPlayer getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private int getNextPlayerIndex() {
        if (queueDirection) {
            return (currentPlayerIndex + 1) % players.size();
        } else {
            return currentPlayerIndex - 1 >= 0 ? (currentPlayerIndex + 1) % players.size() : players.size() - 1;
        }
    }

    private void changeDirection() {
        queueDirection = !queueDirection;
    }

    private void start() {
        setNewTopCard(deck.drawCard());
        List<AbstractPlayer> playersInGame = players;
        while (doesGameGoOn()) {
            AbstractPlayer currentPlayer = playersInGame.get(currentPlayerIndex);
            output.showTopCard(topCard);
            output.showPlayersTurn(currentPlayer, players);
            takeTurn(currentPlayer);
            if (currentPlayer.hasWon()) {
                finishedPlayers.add(currentPlayer);
                playersInGame.remove(currentPlayer);
                output.showWinner(currentPlayer);
                writeWinner(currentPlayer);
            }
            currentPlayerIndex = getNextPlayerIndex();
        }

        statistic.increasePlayerRestCards(players);
        output.gameIsOver(statistic);
    }

    private void writeWinner(AbstractPlayer currentPlayer) {
        if (finishedPlayers.size() == 1) {
            statistic.increasePlayerVictory(currentPlayer);
        } else {
            statistic.increasePlayerSecondPlace(currentPlayer);
        }
    }

    private void takeTurn(AbstractPlayer player) {
        if (player instanceof ComputerPlayer) {
            computerTakeTurn((ComputerPlayer) player);
        } else {
            humanTakeTurn((HumanPlayer) player);
        }
    }

    private void computerTakeTurn(ComputerPlayer player) {
        Card playedCard = player.takeTurn(topCard);
        if (playedCard != null) {
            setNewTopCard(playedCard);
        } else {
            if (!deck.isEmpty()) {
                Card retrievedCard = deck.drawCard();
                player.drawCard(retrievedCard);
                output.showRetrievedCard(retrievedCard);
                if (topCard.isValidMove(retrievedCard)) {
                    setNewTopCard(retrievedCard);
                }
            }
        }

        handleSpecialCard();
    }

    private void humanTakeTurn(HumanPlayer player) {
        output.showHand(player.getHand());
        int choice = input.getUserChoice();
        if (choice == HUMAN_SKIP_TURN_CHOICE) {
            if (!deck.isEmpty()) {
                Card retrievedCard = deck.drawCard();
                player.drawCard(retrievedCard);
                output.showRetrievedCard(retrievedCard);
                if (topCard.isValidMove(retrievedCard)) {
                    setNewTopCard(retrievedCard);
                    handleSpecialCard();
                }
            }
        } else if (topCard.isValidMove(player.getHand().get(choice))) {
            setNewTopCard(player.playCard(choice));
            handleSpecialCard();
        } else {
            output.incorrectTurn();
            if (!deck.isEmpty()) {
                player.drawCard(deck.drawCard());
            }
        }
    }



    private boolean doesGameGoOn() {
        return this.finishedPlayers.size() < this.players.size();
    }

    public void startNewRound() {
        gamesCount++;
        topCard = priviosCard = null;
        queueDirection = true;
        output.newRound();
        finishedPlayers.clear();
        deck.initializeDeck();
        initializeUserHand();
        currentPlayerIndex = gamesCount % players.size();
        start();
    }

    private void handleSpecialCard() {
        switch (topCard.getValue()) {
            case Sieben:
                handleCardSeven();
                break;
            case Acht:
                handleCardEight();
                break;
            case Neun:
                handleCardNine();
                break;
            case Bube:
                handleCardBube();
                break;
        }
    }

    private void handleCardSeven() {
        AbstractPlayer nextPlayer = getNextPlayer();
        int count = priviosCard.getValue() == Card.Value.Sieben ? 4 : 2;
        output.nextPlayerDrawsCards(nextPlayer, count);
        for (int i = 0; i < count; i++) {
            if (!deck.isEmpty()) {
                nextPlayer.drawCard(deck.drawCard());
            } else {
                break;
            }
        }
    }

    private void handleCardEight() {
        output.nextPlayerSkipTurn(getNextPlayer());
        currentPlayerIndex++;
    }

    private void handleCardNine() {
        output.queueChangeDirection();
        changeDirection();
    }

    private void handleCardBube() {
        AbstractPlayer player = getCurrentPlayer();
        Card.Color color;

        if (player instanceof HumanPlayer) {
            color = input.userChoiceColor();
        } else {
            color = ((ComputerPlayer) player).choiceColor();
        }

        topCard.setColor(color);
    }
}