package IO;

import Entity.Card;

public interface MauMauInput {
    int getUserChoice();

    boolean addSecondPlayers();

    Card.Color userChoiceColor();

    boolean newRound();
}
