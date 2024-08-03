package Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        Collections.shuffle(cards);
    }

    public void initializeDeck() {
        for (Card.Color color : Card.Color.values()) {
            for (Card.Value value : Card.Value.values()) {
                cards.add(new Card(color, value));
                Collections.shuffle(cards);
            }
        }
    }

    public Card drawCard() {
        return cards.removeLast();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}