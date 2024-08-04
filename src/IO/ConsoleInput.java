package IO;

import Entity.Card;

import java.util.Scanner;

public class ConsoleInput implements MauMauInput {
    private final Scanner scanner;

    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    @Override
    public Integer getUserChoice() {
        System.out.println("Wählen Sie eine Kartennummer oder geben Sie -1 ein, um eine Karte vom Stapel zu ziehen");
        String choice = scanner.nextLine();
        Integer nummer;

        try {
            nummer = Integer.parseInt(choice);
        } catch (IllegalArgumentException e) {
            System.out.println("Falsche Eingabe. -1 ausgewählt");
            nummer = null;
        }

        return nummer;
    }

    @Override
    public boolean addSecondPlayers() {
        System.out.println("Noch ein Spieler? (ja/nein): ");
        String choice = scanner.nextLine();

        return choice.equals("Ja") || choice.equals("ja") || choice.equals("1");
    }

    @Override
    public Card.Color userChoiceColor() {
        System.out.println("Wähle Farbe (Herz|Karo|Pik|Kreuz): ");
        String choice = scanner.nextLine();
        Card.Color color;

        try {
            color = Card.Color.valueOf(choice);
        } catch (IllegalArgumentException e) {
            System.out.println("Falsche Eingabe. HERZ ausgewählt");
            color = Card.Color.Herz;
        }

        return color;
    }

    @Override
    public boolean newRound() {
        System.out.println("Noch eine Runde? (ja/nein): ");
        String choice = scanner.nextLine();

        return choice.equals("Ja") || choice.equals("ja") || choice.equals("1");
    }
}
