package Entity;

public class ComputerPlayer extends AbstractPlayer {

    public ComputerPlayer(String name) {
        super(name);
    }

    public Card takeTurn(Card topCard) {
        for (int i = 0; i < getHand().size(); i++) {
            if (getHand().get(i).isValidMove(topCard)) {
                return playCard(i);
            }
        }

        return null;
    }

    public Card.Color choiceColor() {
        if (!hand.isEmpty()) {
            return hand.getFirst().getColor();
        }
        return Card.Color.Herz;
    }
}
