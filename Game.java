
import Items.Item;
import RaumSystem.Room;
import Spieler.Player;
import Karte.Karte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player;
    private boolean running;
    private ArrayList<Room> rooms;
    private Karte Karte;

    //TODO Konstruktor erstellen
    public Game() throws FileNotFoundException {
        rooms = new ArrayList<>();
        rooms.add(new Room("START", "Ein initialer Startraum"));
        player = new Player(rooms.get(0));
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        Game game = new Game();
        game.start();

    }

    public void start() throws FileNotFoundException {
        rooms = new ArrayList<>();

        filterCSVInput(readCSV());
        Karte = new Karte(rooms);
        Karte.setExplored(player.getCurrentRoom());
        Karte.setUnknown(player.getCurrentRoom());

        /*
        if(checkRoomConnection()){
            System.out.println("Alle Raeume haben einen gueltigen Ausgang");
        }else{
            System.out.println("FEHLER - Raeume haben Fehler im Ausgang");
        }*/

        Scanner scanner = new Scanner(System.in);
        running = true;

        System.out.println("Willkommen bei EscapeCampus!");
        System.out.println("Tippe 'hilfe' für Befehle.");
        System.out.println(player.getCurrentRoom().getDescription());

        while (running) {

            System.out.print("> ");
            String command = scanner.nextLine();

            handleCommand(command);
        }

        scanner.close();
    }

    private void handleCommand(String command) {
        if(!command.isEmpty()){
            if (command.equals("hilfe")) {
                System.out.println("Folgende Eingaben sind valide: 'hilfe', 'schau', 'gehe n|s|o|w', 'inventar', 'karte'");
            } else if (command.equals("schau")) {
                System.out.println(player.getCurrentRoom().getDescription());

            } else if (command.equals("ende")) {
                running = false;
            } else if(command.equals("karte")) {
                Karte.spielerKarte();
            } else if (command.equals("fullmap")) {
                Karte.fullKarte();
            } else if (command.startsWith("gehe")) {
                if (command.length() < 6) {
                    System.out.println("Bitte gib eine Richtung an: gehe n|s|o|w");
                    return;
                }

                String direction = command.substring(5, 6);
                Room nextRoom = player.getCurrentRoom().getExit(direction);

                if (nextRoom == null) {
                    System.out.println(("Dort ist kein Ausgang"));
                    int x = player.getCurrentRoom().getX();
                    int y = player.getCurrentRoom().getY();

                    if (direction.toLowerCase().equals("n")) {
                        Karte.setHidden(x, y + 1);
                    } else if (direction.toLowerCase().equals("s")) {
                        Karte.setHidden(x, y - 1);
                    } else if (direction.toLowerCase().equals("o")) {
                        Karte.setHidden(x + 1, y);
                    } else if (direction.toLowerCase().equals("w")) {
                        Karte.setHidden(x - 1, y);
                    }
                } else {
                        //TODO Fallüberprüfung ob ein Event im Raum ist -> kein Raumwechsel möglich
                    if (!nextRoom.getIstVerschlossen()) {
                        player.setCurrentRoom(nextRoom);
                        Karte.setExplored(player.getCurrentRoom());

                        player.getCurrentRoom().setMapState("explored");
                        Karte.setUnknown(player.getCurrentRoom());

                        if (nextRoom.hasRoomItems()) {
                            this.addItemToInventar();
                        }

                        System.out.println(player.getCurrentRoom().getDescription());
                    } else {
                        if (this.player.existiertItemImInventar("Schlussel")) {
                            System.out.println("Du hast einen Schlüssel - du schließt die Tür auf.");
                            this.player.deleteItemFromInventar("Schlussel");
                            player.setCurrentRoom(nextRoom);
                            Karte.setExplored(player.getCurrentRoom());
                            Karte.setUnknown(player.getCurrentRoom());
                            player.getCurrentRoom().setIstVerschlossen();

                            if (nextRoom.hasRoomItems()) {
                                this.addItemToInventar();
                            }

                            System.out.println(player.getCurrentRoom().getDescription());
                        } else {
                            System.out.println("Diese Tür ist verschlossen. Suche nach einen Schlüssel!");
                        }

                    }

                }

            } else if (command.equals("inventar")) {
                System.out.print("Inventar: ");
                for (Item item : this.player.getInventar()) {
                    System.out.print(item.getName() + " ");
                }
                System.out.println();
            } else {
                System.out.println("Unbekannter Fehler! Tippe 'hilfe'");
            }
        }else{
            System.out.println("Keine Eingabe erkannt! Bitte gebe etwas ein oder tippe 'hilfe'");
        }

    }

    private ArrayList<String> readCSV() throws FileNotFoundException {
        try {
            File file = new File("campus.csv");
            Scanner scanner = new Scanner(file);
            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
            return lines;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Datei konnte nicht gefunden werden! " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Es ist ein Fehler beim Laden der .csv aufgetreten! " + e.getMessage());
        }
    }

    private void filterCSVInput(ArrayList<String> lines) {
        ArrayList<String> rooms = new ArrayList<>();
        ArrayList<String> exits = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(";");
            switch (parts[0]) {
                case "START" -> createStartroomAndPlayer(parts[1] + "," + parts[2] + "," + parts[6] + "," + parts[7]); // Startraum ist nicht verschlossen und hat keine Events
                case "ROOM" -> rooms.add(parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + parts[7]);
                case "EXIT" -> exits.add(parts[1] + "," + parts[2] + "," + parts[3]);
            }
        }

        createRooms(rooms);
        createExits(exits);
    }

    private void createRooms(ArrayList<String> rooms) {
        for (String line : rooms) {
            String[] parts = line.split(",", 7);
            String name = parts[0];
            String description = parts[1];
            boolean istVerschlossen = Boolean.parseBoolean(parts[2]);
            char item = Character.toUpperCase(parts[3].charAt(0));
            char event = Character.toUpperCase(parts[4].charAt(0));
            int x = Integer.parseInt(parts[5]);
            int y = Integer.parseInt(parts[6]);
            this.rooms.add(new Room(name, description, istVerschlossen, item, event, x, y));
        }
    }

    private void createStartroomAndPlayer(String start) {
        String[] parts = start.split(",", 4);
        String name = parts[0];
        String description = parts[1];
        int x = Integer.parseInt(parts[2]);
        int y = Integer.parseInt(parts[3]);

        this.rooms.add(new Room(name, description, false, 'N', 'N', x, y));

        player = new Player(findRoom(name));
    }

    private void createExits(ArrayList<String> exits) {
        for (String line : exits) {
            String[] parts = line.split(",", 3);
            String name = parts[0];
            String direction = parts[1];
            String exitname = parts[2];
            findRoom(name).setExits(direction, findRoom(exitname));
        }
    }
//change for commit
    private Room findRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    private boolean checkRoomConnection() {
        if (!this.rooms.isEmpty()) {
            try {
                boolean verbunden = true;
                for (int i = 0; i < rooms.size(); i++) {
                    Room currentRoom = this.rooms.get(i);
                    Room[] possibleExits = new Room[4];
                    possibleExits[0] = currentRoom.getExit("n");
                    possibleExits[1] = currentRoom.getExit("o");
                    possibleExits[2] = currentRoom.getExit("s");
                    possibleExits[3] = currentRoom.getExit("w");

                    if (possibleExits[0] != null && possibleExits[0].getExit("s").getName().equals(currentRoom.getName())) {
                        verbunden = true;
                    } else if (possibleExits[1] != null && possibleExits[1].getExit("w").getName().equals(currentRoom.getName())) {
                        verbunden = true;
                    } else if (possibleExits[2] != null && possibleExits[2].getExit("n").getName().equals(currentRoom.getName())) {
                        verbunden = true;
                    } else if (possibleExits[3] != null && possibleExits[3].getExit("o").getName().equals(currentRoom.getName())) {
                        verbunden = true;
                    } else {
                        verbunden = false;
                    }
                    if (!verbunden) {
                        return verbunden;
                    }
                }
            } catch (NullPointerException e) {
            }

        } else {
            throw new NullPointerException("Die Liste Rooms ist leer - keine Kontrolle moeglich!");
        }

        return false;
    }

    public void addItemToInventar() {
        System.out.println("Du findest: ");
        ArrayList<Item> itemList = this.player.getCurrentRoom().getItemsFromRoom();
        for (Item item : itemList) {
            System.out.println(item.getName());
            this.player.addItem(item);
        }
        //Methode muss hier aufgerufen werden weil getItemsFromRoom() returned
        this.player.getCurrentRoom().removeItemsFromRoom();
    }


}
