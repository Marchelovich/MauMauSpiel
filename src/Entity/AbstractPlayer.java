package Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {
    protected List<Card> hand;

    protected String name;

    public String getName() {
        return this.name;
    }

    public AbstractPlayer(String name) {
        hand = new ArrayList<>();
        this.name = name;
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public Card playCard(int index) {
        return hand.remove(index);
    }

    public boolean hasWon() {
        return hand.isEmpty();
    }

    public void clearHand() {
        hand.clear();
    }


}