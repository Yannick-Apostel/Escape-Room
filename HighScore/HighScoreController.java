package HighScore;

import helper.ConsoleColors;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HighScoreController {

    private int highscore;
    private final String name;
    private final String dateiName = "highscore.csv";

    public HighScoreController(Scanner input) {
        System.out.print("Bitte tippe deinen " + ConsoleColors.WHITE_UNDERLINED + "Namen" + ConsoleColors.RESET + " ein: ");
        try {
            name = input.nextLine().trim();
            System.out.println();
        } catch (RuntimeException e) {
            throw new RuntimeException("Fehler beim parsen der Namenseingabe");
        }
    }

    public void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateiName, true))) {
            writer.write(this.name+";"+this.highscore+";"+ LocalDateTime.now());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben der Datei: " + e.getMessage());
        }
    }

    public void loadHighscore() {
        File highscoreDatei = new File(dateiName);
        String tabellenFormat = "| %-15s | %-10s | %-20s |%n";


        if (!highscoreDatei.exists()) {
            System.out.println("Keine Highscore-Datei gefunden.");
            return;
        }

        System.out.println("+-----------------+------------+----------------------+");
        System.out.printf(tabellenFormat, "NAME", "HIGHSCORE", "DATUM/UHRZEIT");
        System.out.println("+-----------------+------------+----------------------+");

        try (BufferedReader br = new BufferedReader(new FileReader(highscoreDatei))) {
            String zeile;
            boolean ersteZeile = true;


            DateTimeFormatter zielFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

            while ((zeile = br.readLine()) != null) {
                if (ersteZeile) {
                    ersteZeile = false;
                    if (zeile.equalsIgnoreCase("NAME;HIGHSCORE;DATUMTUHRZEIT")) continue;
                }

                String[] daten = zeile.split(";");

                if (daten.length == 3) {
                    String name = daten[0];
                    String highscore = daten[1];
                    String rohesDatum = daten[2];
                    String anzuzeigendesDatum;

                    try {

                        LocalDateTime datumObjekt = LocalDateTime.parse(rohesDatum);
                        anzuzeigendesDatum = datumObjekt.format(zielFormat);
                    } catch (DateTimeParseException e) {

                        anzuzeigendesDatum = rohesDatum;
                    }


                    System.out.printf(tabellenFormat, name, highscore, anzuzeigendesDatum);
                }
            }
            System.out.println("+-----------------+------------+----------------------+");

        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
    }

    public void addHighScore(int add) {
        this.highscore += add;
    }

    public int getHighscore() {
        return this.highscore;
    }

    public String getName() { return this.name; }
}
