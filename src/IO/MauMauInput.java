package IO;

import Entity.Card;

public interface MauMauInput {
    Integer getUserChoice();

    boolean addSecondPlayers();

    Card.Color userChoiceColor();

    boolean newRound();
}
