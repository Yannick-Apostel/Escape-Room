package HighScore;

import helper.ConsoleColors;

import java.util.Scanner;

public class HighScoreController {

    private int highscore;
    private final String name;

    public HighScoreController(Scanner input) {
        System.out.print("Bitte tippe deinen " + ConsoleColors.WHITE_UNDERLINED + "Namen" + ConsoleColors.RESET + " ein: ");
        try {
            name = input.next();
            System.out.println();
        } catch (RuntimeException e) {
            throw new RuntimeException("Fehler beim parsen der Namenseingabe");
        }
    }

    public void saveHighScore(){
        //TODO Hier Highsore im Dokument schreiben und speichern
    }

    public void loadHighscore(){
        //TODO Hier Highscore aus dem Dokument lesen und auflisten
    }

    public void addHighScore(int add) {
        this.highscore += add;
    }

    public int getHighscore(){
        return this.highscore;
    }
}
