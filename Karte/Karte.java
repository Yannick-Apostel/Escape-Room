package Karte;

import RaumSystem.Room;
import java.util.ArrayList;

public class Karte {

    private String[][] karte;
    private String[][] spielerKarte;

    public Karte(ArrayList<Room> rooms) {
        createKarte(rooms);
    }

    private void createKarte(ArrayList<Room> rooms) {
        karte = new String[6][6];

        for (int x = 0; x < 6; x++) {
            for(int y = 0; y < 6; y++) {
                karte[x][y] = "NULL";

                for (Room room : rooms) {
                    if (room.getX() == x && room.getY() == y) {
                        karte[x][y] = room.getName();
                    }
                }
            }
        }
    }

    public void setHidden(int x, int y) {
        if (x >= 0 && x < spielerKarte.length && y >= 0 && y < spielerKarte[x].length) {
            spielerKarte[x][y] = "hidden";
        }
    }

    private void setUnknownAt(int x, int y) {

    }

    public void setUnknown(Room currentRoom) {
        int x = currentRoom.getX();
        int y = currentRoom.getY();

        setUnknownAt(x, y + 1); // north
        setUnknownAt(x, y - 1); // south
        setUnknownAt(x + 1, y); // east/o
        setUnknownAt(x - 1, y);
    }

    public void spielerKarte() {

    }

    public void fullKarte() {
        String trennlinie = "+---------------+";
        String leeresFeld = " ".repeat(17);

        for (int y = 5; y >= 0; y--) {
            for (int x = 0; x < 6; x++) {
                String roomName = karte[x][y];

                if (roomName.equals("NULL")) {
                    System.out.print(leeresFeld);
                } else {
                    System.out.print(trennlinie);
                }
            }

            System.out.println();

            for (int x = 0; x < 6; x++) {
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

            for (int x = 0; x < 6; x++) {
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



