package Entity;

import java.util.ArrayList;
import java.util.HashMap;

import static Entity.Statistic.StatisticParameter.*;

public class Statistic {

    public enum StatisticParameter {
        FirstPlaceCount,
        SecondPlaceCount,
        ValueRestCards
    }

    private final HashMap<AbstractPlayer, HashMap<StatisticParameter, Integer>> playersStatistic;

    public Statistic(ArrayList<AbstractPlayer> players) {
        playersStatistic = new HashMap<>();
        for (AbstractPlayer player: players) {
            HashMap<StatisticParameter, Integer> statistic = new HashMap<>();
            statistic.put(FirstPlaceCount, 0);
            statistic.put(SecondPlaceCount, 0);
            statistic.put(ValueRestCards, 0);
            playersStatistic.put(player, statistic);
        }
    }

    public HashMap<AbstractPlayer, HashMap<StatisticParameter, Integer>> getPlayersStatistic() {
        return playersStatistic;
    }

    public void increasePlayerRestCards(ArrayList<AbstractPlayer> players) {
        for (AbstractPlayer player: players) {
            HashMap<StatisticParameter, Integer> statistic = playersStatistic.get(player);
            statistic.put(ValueRestCards, statistic.get(ValueRestCards) + getValueRestCards(player));
        }
    }

    public void increasePlayerVictory(AbstractPlayer player) {
        HashMap<StatisticParameter, Integer> statistic = playersStatistic.get(player);
        statistic.put(FirstPlaceCount, statistic.get(FirstPlaceCount) + 1);
    }

    public void increasePlayerSecondPlace(AbstractPlayer player) {
        HashMap<StatisticParameter, Integer> statistic = playersStatistic.get(player);
        statistic.put(SecondPlaceCount, statistic.get(SecondPlaceCount) + 1);
    }

    private Integer getValueRestCards(AbstractPlayer player) {
        int sume = 0;

        for (Card card: player.getHand()) {
            switch (card.getValue()) {
                case Card.Value.Ass: {
                    sume += 11;
                    break;
                }
                case Card.Value.Yehn: {
                    sume += 10;
                    break;
                }
                case Card.Value.König: {
                    sume += 4;
                    break;
                }
                case Card.Value.Dame: {
                    sume += 3;
                    break;
                }
                case Card.Value.Bube: {
                    sume += 2;
                    break;
                }
            }
        }

        return sume;
    }
}
