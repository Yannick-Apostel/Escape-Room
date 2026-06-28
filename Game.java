import HighScore.HighScoreController;
import Items.Item;
import RaumSystem.Room;
import Spieler.Player;
import Karte.Karte;
import helper.ConsoleColors;
import helper.Sleeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player;
    private boolean running;
    private ArrayList<Room> rooms;
    private Karte Karte;
    private HighScoreController highScoreController;

    //TODO Konstruktor erstellen
    public Game(Scanner input) throws FileNotFoundException {
        rooms = new ArrayList<>();
        rooms.add(new Room("START", "Ein initialer Startraum"));
        player = new Player(rooms.get(0), input);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(scanner);
        game.start(scanner);

    }

    public void start(Scanner scanner) throws FileNotFoundException {
        rooms = new ArrayList<>();
        highScoreController = new HighScoreController(scanner);
        filterCSVInput(readCSV(), scanner);
        Karte = new Karte(rooms);
        Karte.setExplored(player.getCurrentRoom());
        Karte.setUnknown(player.getCurrentRoom());

        /*
        if(checkRoomConnection()){
            System.out.println("Alle Raeume haben einen gueltigen Ausgang");
        }else{
            System.out.println("FEHLER - Raeume haben Fehler im Ausgang");
        }*/


        running = true;

        Sleeper.sleep(1000);
        System.out.println("Willkommen bei" + ConsoleColors.PURPLE_BOLD );
        Sleeper.sleep(1000);
        System.out.println();
        String escapeCampus = """
                 /        |/      \\ /      \\ /      \\/       \\/        |       /      \\ /      \\/  \\     /  /       \\/  |  /  |/      \\\s
                $$$$$$$$//$$$$$$  /$$$$$$  /$$$$$$  $$$$$$$  $$$$$$$$/       /$$$$$$  /$$$$$$  $$  \\   /$$ $$$$$$$  $$ |  $$ /$$$$$$  |
                $$ |__   $$ \\__$$/$$ |  $$/$$ |__$$ $$ |__$$ $$ |__          $$ |  $$/$$ |__$$ $$$  \\ /$$$ $$ |__$$ $$ |  $$ $$ \\__$$/\s
                $$    |  $$      \\$$ |     $$    $$ $$    $$/$$    |         $$ |     $$    $$ $$$$  /$$$$ $$    $$/$$ |  $$ $$      \\\s
                $$$$$/    $$$$$$  $$ |   __$$$$$$$$ $$$$$$$/ $$$$$/          $$ |   __$$$$$$$$ $$ $$ $$/$$ $$$$$$$/ $$ |  $$ |$$$$$$  |
                $$ |_____/  \\__$$ $$ \\__/  $$ |  $$ $$ |     $$ |_____       $$ \\__/  $$ |  $$ $$ |$$$/ $$ $$ |     $$ \\__$$ /  \\__$$ |
                $$       $$    $$/$$    $$/$$ |  $$ $$ |     $$       |      $$    $$/$$ |  $$ $$ | $/  $$ $$ |     $$    $$/$$    $$/\s
                $$$$$$$$/ $$$$$$/  $$$$$$/ $$/   $$/$$/      $$$$$$$$/        $$$$$$/ $$/   $$/$$/      $$/$$/       $$$$$$/  $$$$$$/ \s
                
                
                
                """;

        System.out.println(escapeCampus);
        Sleeper.sleep(1000);
        System.out.println(ConsoleColors.RESET+"Tippe" + ConsoleColors.GREEN_BOLD_BRIGHT + " 'hilfe'" + ConsoleColors.RESET + " für Befehle.");
        System.out.println(player.getCurrentRoom().getDescription());

        while (running) {
            try {
                Karte.spielerKarte(player);
                System.out.print("> ");
                // Überprüfen, ob der Scanner noch offen ist
                if (scanner.hasNextLine()) {
                    String command = scanner.nextLine();
                    handleCommand(command);
                } else {
                    // Wenn hasNextLine() false ist, ist der Stream oft zu Ende oder geschlossen
                    break;
                }
            } catch (IllegalStateException e) {
                System.err.println("Der Scanner wurde unerwartet geschlossen.");
                break;
            } catch (Exception e) {
                System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
                e.printStackTrace();
            }

        }

        // scanner.close();
    }

    private void handleCommand(String command) {
        command = command.trim();
        if (!command.isEmpty()) {
            if (command.equals("hilfe")) {
                System.out.println("Folgende Eingaben sind valide:"+ ConsoleColors.GREEN_BOLD_BRIGHT+"'hilfe', 'schau', 'gehe n|s|o|w', 'inventar', 'inspect <Item>', 'benutze <Item>,' 'status'"+ ConsoleColors.RESET);
            } else if (command.equals("schau")) {
                System.out.println(player.getCurrentRoom().getDescription());
            } else if (command.equals("status")) {
                player.showLeben();
                if(player.playerWeapon() == null) {
                    System.out.println("Du machst gerade: " + ConsoleColors.ORANGE + player.getAttackDamage() + " Schaden pro Angriff." + ConsoleColors.RESET);
                } else {
                    System.out.println("Du machst gerade: " + ConsoleColors.ORANGE + player.getAttackDamage() + "(+" + player.playerWeapon().getDamage() + ")" + " Schaden pro Angriff." + ConsoleColors.RESET);
                }

            } else if (command.equals("ende")) {
                running = false;
            } else if (command.startsWith("benutze")) {
                String itemName = command.substring(8).trim();

                for (Item item : player.getInventar()) {
                    if (item.getName().equals(itemName)) {
                        item.use(player, item);
                        player.deleteItemFromInventar(itemName);
                        return;
                    }
                }

            } else if(command.startsWith("inspect")) {
                String itemName = command.substring(8).trim();

                for (Item item : player.getInventar()) {
                    if (item.getName().equals(itemName)) {
                        item.function();
                        return;
                    }
                }
                System.out.println("Dieses Item existiert nicht.");

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
                    System.out.println((ConsoleColors.RED_BACKGROUND+ ConsoleColors.WHITE_BOLD_BRIGHT+  "Dort ist kein Ausgang!"+ConsoleColors.RESET));
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
                        if(nextRoom.getIsEnde()){
                            endeErreicht();
                            running = false;
                        } else {
                            player.setCurrentRoom(nextRoom);
                            System.out.println(player.getCurrentRoom().getDescription());
                            Karte.setExplored(player.getCurrentRoom());

                            highScoreController.addHighScore(player.getCurrentRoom().getPunkte());

                            player.getCurrentRoom().setMapState("explored");
                            Karte.setUnknown(player.getCurrentRoom());

                            if (nextRoom.hasRoomItems()) {
                                this.player.addItemToInventar();
                            }
                        }
                    } else {
                        if (this.player.existiertItemImInventar("Schlussel")) {
                            System.out.println("Du hast einen" + ConsoleColors.GREEN_UNDERLINED + " Schlüssel" + ConsoleColors.RESET+" - du schließt die Tür auf.");
                            this.player.deleteItemFromInventar("Schlussel");

                            player.setCurrentRoom(nextRoom);
                            System.out.println(player.getCurrentRoom().getDescription());

                            Karte.setExplored(player.getCurrentRoom());
                            Karte.setUnknown(player.getCurrentRoom());

                            player.getCurrentRoom().setIstVerschlossen();

                            if(player.getCurrentRoom().getIsEnde()){
                                endeErreicht();
                                running = false;
                            }else {
                                if (nextRoom.hasRoomItems()) {
                                    this.player.addItemToInventar();
                                }
                            }
                        } else {
                            System.out.println("Diese Tür ist"+ConsoleColors.RED_BRIGHT+ " verschlossen"+ConsoleColors.RESET+". Suche nach einen "+ConsoleColors.GREEN_BRIGHT+"Schlüssel"+ConsoleColors.RESET+"!");
                        }

                    }

                }

            } else if (command.equals("inventar")) {
                player.zeigeInventar();
            } else {
                System.out.println("Unbekannter Befehl! Tippe 'hilfe'");
            }

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
            //scanner.close();
            return lines;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Datei konnte nicht gefunden werden! " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Es ist ein Fehler beim Laden der .csv aufgetreten! " + e.getMessage());
        }
    }

    private void filterCSVInput(ArrayList<String> lines, Scanner input) {
        ArrayList<String> rooms = new ArrayList<>();
        ArrayList<String> exits = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(";");
            switch (parts[0]) {
                case "START" -> createStartroomAndPlayer(parts[1] + "," + parts[2] + "," + parts[6] + "," + parts[7] + "," + parts[8], input); // Startraum ist nicht verschlossen und hat keine Events
                case "ROOM" -> rooms.add(parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + parts[7] + "," + parts[8]);
                case "EXIT" -> exits.add(parts[1] + "," + parts[2] + "," + parts[3]);
                case "ENDE" -> createEndRoom(parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + parts[7] + "," + parts[8]);
            }
        }

        createRooms(rooms);
        createExits(exits);
    }

    private void createRooms(ArrayList<String> rooms) {
        for (String line : rooms) {
            String[] parts = line.split(",", 8);
            String name = parts[0];
            String description = parts[1];
            boolean istVerschlossen = Boolean.parseBoolean(parts[2]);
            char item = Character.toUpperCase(parts[3].charAt(0));
            char event = Character.toUpperCase(parts[4].charAt(0));
            int x = Integer.parseInt(parts[5]);
            int y = Integer.parseInt(parts[6]);
            int punkte = Integer.parseInt(parts[7]);

            this.rooms.add(new Room(name, description, istVerschlossen, item, event, x, y, punkte, false));
        }
    }

    private void createStartroomAndPlayer(String start, Scanner input) {
        String[] parts = start.split(",", 5);
        String name = parts[0];
        String description = parts[1];
        int x = Integer.parseInt(parts[2]);
        int y = Integer.parseInt(parts[3]);
        int punkte = Integer.parseInt(parts[4]);
        this.rooms.add(new Room(name, description, false, 'N', 'N', x, y, punkte, false));

        player = new Player(findRoom(name), input);
    }
    private void createEndRoom(String ende){
        String[] parts = ende.split(",", 8);
        String name = parts[0];
        String description = parts[2];
        int x = Integer.parseInt(parts[5]);
        int y = Integer.parseInt(parts[6]);
        int punkte = Integer.parseInt(parts[7]);
        this.rooms.add(new Room(name, description, false, 'N', 'N', x, y, punkte, true));
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

    public void endeErreicht(){
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+ "DU HAST DEN AUSGANG GEFUNDEN!!!");
        System.out.println("GLÜCKWUNSCH");
        System.out.println("DU HAST "+ highScoreController.getHighscore() +" PUNKTE ERREICHT"+ ConsoleColors.RESET);
        highScoreController.saveHighScore();
        highScoreController.loadHighscore();
    }

}
