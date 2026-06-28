package Karte;

import RaumSystem.Room;
import Spieler.Player;

import java.util.ArrayList;

public class Karte {

    private static final int WIDTH = 6;
    private static final int HEIGHT = 6;

    private String[][] karte;
    private String[][] spielerKarte;
    private static final String HELL_GRUEN = "\u001B[92m";
    private static final String RESET = "\u001B[0m";

    public Karte(ArrayList<Room> rooms) {
        createKarte(rooms);
    }

    private void createKarte(ArrayList<Room> rooms) {
        karte = new String[WIDTH][HEIGHT];
        spielerKarte = new String[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                karte[x][y] = "NULL";
                spielerKarte[x][y] = "hidden";

                for (Room room : rooms) {
                    if (room.getX() == x && room.getY() == y) {
                        karte[x][y] = room.getName();
                    }
                }
            }
        }
    }

    public void setExplored(Room room) {
        int x = room.getX();
        int y = room.getY();

        spielerKarte[x][y] = room.getName();
    }

    public void setHidden(int x, int y) {
        if (x >= 0 && x < spielerKarte.length && y >= 0 && y < spielerKarte[x].length) {
            spielerKarte[x][y] = "hidden";
        }
    }

    private void setUnknownAt(int x, int y) {
        if (x >= 0 && x < spielerKarte.length && y >= 0 && y < spielerKarte[x].length) {
            if(spielerKarte[x][y].equals("hidden")) {
                spielerKarte[x][y] = "unknown";
            }
        }
    }

    public void setUnknown(Room currentRoom) {
        int x = currentRoom.getX();
        int y = currentRoom.getY();

        setUnknownAt(x, y + 1); // north
        setUnknownAt(x, y - 1); // south
        setUnknownAt(x + 1, y); // east/o
        setUnknownAt(x - 1, y);
    }

    public void spielerKarte(Player player) {
        String trennlinie = "+---------------+";
        String leeresFeld = " ".repeat(17);

        for (int y = HEIGHT - 1; y >= 0; y--) {
            for (int x = 0; x < WIDTH; x++) {
                String feld = spielerKarte[x][y];

                if (feld.equals("hidden")) {
                    System.out.print(leeresFeld);
                } else {
                    System.out.print(trennlinie);
                }
            }

            System.out.println();

            for (int x = 0; x < WIDTH; x++) {
                String feld = spielerKarte[x][y];

                if (feld.equals("hidden")) {
                    System.out.print(leeresFeld);
                } else {
                    String feldText = formatFeld(feld);
                    int freiePlaetze = 15 - feldText.length();
                    int links = freiePlaetze / 2;
                    int rechts = freiePlaetze - links;

                    if(player.getCurrentRoom().getName().equals(feld)) {
                        feldText = HELL_GRUEN + feldText + RESET;
                    }


                    System.out.print("|" + " ".repeat(links) + feldText + " ".repeat(rechts) + "|");
                }
            }

            System.out.println();

            for (int x = 0; x < WIDTH; x++) {
                String feld = spielerKarte[x][y];

                if (feld.equals("hidden")) {
                    System.out.print(leeresFeld);
                } else {
                    System.out.print(trennlinie);
                }
            }

            System.out.println();
        }
    }

    private String formatFeld(String wert) {
        if (wert.equals("unknown")) {
            return "???";
        }

        return wert;
    }

    public void fullKarte() {
        String trennlinie = "+---------------+";
        String leeresFeld = " ".repeat(17);

        for (int y = HEIGHT - 1; y >= 0; y--) {
            for (int x = 0; x < WIDTH; x++) {
                String roomName = karte[x][y];

                if (roomName.equals("NULL")) {
                    System.out.print(leeresFeld);
                } else {
                    System.out.print(trennlinie);
                }
            }

            System.out.println();

            for (int x = 0; x < WIDTH; x++) {
                String roomName = karte[x][y];

                if (roomName.equals("NULL")) {
                    System.out.print(leeresFeld);
                } else {
                    int freiePlaetze = 15 - roomName.length();
                    int links = freiePlaetze / 2;
                    int rechts = freiePlaetze - links;

                    System.out.print("|" + " ".repeat(links) + roomName + " ".repeat(rechts) + "|");
                }
            }

            System.out.println();

            for (int x = 0; x < WIDTH; x++) {
                String roomName = karte[x][y];

                if (roomName.equals("NULL")) {
                    System.out.print(leeresFeld);
                } else {
                    System.out.print(trennlinie);
                }
            }

            System.out.println();
        }
    }
}

