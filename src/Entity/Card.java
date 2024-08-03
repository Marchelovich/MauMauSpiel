package Entity;

import static Entity.Card.Value.*;

public class Card {

    private Color color;

    public enum Color {
        Herz,
        Karo,
        Pik,
        Kreuz
    }
    private Value value;
    public enum Value {
        Sieben,
        Acht,
        Neun,
        Yehn,
        Bube,
        Dame,
        König,
        Ass
    }

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + ":" + value;
    }

    public boolean isValidMove(Card card) {
        return this.color.equals(card.getColor())
                || this.value.equals(card.getValue())
                || card.value.equals(Bube);
    }
}
