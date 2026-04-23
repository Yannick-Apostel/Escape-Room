
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player;
    private boolean running;
    private ArrayList<Room> rooms;

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
        if (command.equals("hilfe")) {
            System.out.println("Folgende Eingaben sind valide: 'hilfe', 'schau', 'gehe', 'n|s|o|w'");
        } else if (command.equals("schau")) {
            System.out.println(player.getCurrentRoom().getDescription());

        } else if (command.equals("ende")) {
            running = false;
        } else if (command.substring(0, 4).equals("gehe")) {

            String direction = command.substring(5, 6);
            Room nextRoom = player.getCurrentRoom().getExit(direction);

            if (nextRoom == null) {
                System.out.println(("Dort ist kein Ausgang"));
            } else {
                player.setCurrentRoom(nextRoom);
                System.out.println(player.getCurrentRoom().getDescription());
            }

        } else {
            System.out.println("Unbekannter Fehler! Tippe 'hilfe'");
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
                case "START" -> createStartroomAndPlayer(parts[1] + "," + parts[2]);
                case "ROOM" -> rooms.add(parts[1] + "," + parts[2]);
                case "EXIT" -> exits.add(parts[1] + "," + parts[2] + "," + parts[3]);
            }
        }

        createRooms(rooms);
        createExits(exits);
    }

    private void createRooms(ArrayList<String> rooms) {
        for (String line : rooms) {
            String[] parts = line.split(",", 2);
            String name = parts[0];
            String description = parts[1];
            this.rooms.add(new Room(name, description));
        }
    }

    private void createStartroomAndPlayer(String start) {
        String[] parts = start.split(",", 2);
        String name = parts[0];
        String description = parts[1];
        this.rooms.add(new Room(name, description));

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

    private Room findRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    private boolean checkRoomConnection(){
        if(!this.rooms.isEmpty()){
            try {
                boolean verbunden = true;
                for(int i=0; i<rooms.size(); i++){
                    Room currentRoom = this.rooms.get(i);
                    Room[] possibleExits = new Room[4];
                    possibleExits[0] = currentRoom.getExit("n");
                    possibleExits[1] = currentRoom.getExit("o");
                    possibleExits[2] = currentRoom.getExit("s");
                    possibleExits[3] = currentRoom.getExit("w");

                    if(possibleExits[0]!=null && possibleExits[0].getExit("s").getName().equals(currentRoom.getName())){
                        verbunden = true;
                    } else if (possibleExits[1]!=null && possibleExits[1].getExit("w").getName().equals(currentRoom.getName())) {
                        verbunden = true;
                    } else if (possibleExits[2]!=null && possibleExits[2].getExit("n").getName().equals(currentRoom.getName())) {
                        verbunden = true;
                    } else if (possibleExits[3]!=null && possibleExits[3].getExit("o").getName().equals(currentRoom.getName())) {
                        verbunden = true;
                    }else{
                        verbunden = false;
                    }
                    if(!verbunden){
                        return verbunden;
                    }}
            } catch (NullPointerException e) {}

        }else{
            throw new NullPointerException("Die Liste Rooms ist leer - keine Kontrolle moeglich!");
        }

        return false;
    }


}
