package Spieler;

import Items.Baseballschlaeger;
import Items.Item;
import RaumSystem.Room;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventar;
    private int maxSizeInventar = 5;
    private Scanner input;
    private int Leben = 20;

    public Player(Room startRoom, Scanner input) {
        currentRoom = startRoom;
        inventar = new ArrayList<>();
        this.input = input;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
        try {
            FileWriter writer = new FileWriter("saveData.csv");
            writer.write("Raum;" + room.getName());
            writer.close();
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Datei.");
        }

        currentRoom.startEvent(inventar);
    }

    public ArrayList<Item> getInventar() {
        return this.inventar;
    }

    public Item getInventar(int index) {
        return this.inventar.get(index);
    }

    public boolean addItemErfolgreich(Item newItem) {
        if (this.inventar.size() < maxSizeInventar) {
            if (frageObItemInsInventar(newItem.getName())) {
                if(newItem.getIsWeapon()) {
                    if(playerWeapon() != null) {
                        System.out.println("Du hast schon eine Waffe, willst du sie austauschen? [y/n]");

                    }
                }
                this.inventar.add(newItem);

                try {
                    FileWriter writer = new FileWriter("saveData.csv");
                    writer.write("Items:");
                    for (Item item : inventar) {
                        writer.write(item.getName() + ";");
                    }
                    writer.close();
                    return true;
                } catch (IOException e) {
                    System.out.println("Fehler beim Speichern der Datei.");
                }
            }

        } else {
            return inventarItemErsetzen(newItem);
        }
        return false;
    }

    public boolean inventarItemErsetzen(Item itemZuErstzen) {
        System.out.println("Dein Inventar ist voll. Bitte leere dein Inventar, bevor du ein neues Item hinzufügst");
        System.out.println("Moechtest du das Item mit ein Item aus deinen Inventar tauschen J/N");
        if (frageJNFrage()) {
            zeigeInventar();
            System.out.println("Welches Item möchtest du entfernen? 1-" + inventar.size());
            int indexZumEntfernen = input.nextInt();
            //TODO in while schleife wrappen
            if (indexZumEntfernen > 0 && indexZumEntfernen  <= inventar.size()) { // -1 fur index Verschiebung
                this.getCurrentRoom().addItemToList(inventar.get(indexZumEntfernen-1));
                inventar.set(indexZumEntfernen-1, itemZuErstzen);

                System.out.println("Item erfolgreich ersetzt!");
                return true;
            } else {
                System.out.println("Ungueltiger Index - waehle eine Zahl wischen 1-" + inventar.size());
            }
        } else {
            System.out.println("Dein Inventar bleibt gleich. Fahre fort...");

        }
        return false;
    }

    public boolean existiertItemImInventar(String name) {
        for (Item element : this.inventar) {
            if (element.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private int getIndexFromItemImInventar(String name) {
        if (existiertItemImInventar(name)) {
            for (int i = 0; i < this.inventar.size(); i++) {
                if (this.inventar.get(i).getName().equals(name)) {
                    return i;
                }
            }
        }
        throw new RuntimeException("Fehler - Item konnte nicht gefunden werde");
    }

    public void deleteItemFromInventar(String name) {
        int index = getIndexFromItemImInventar(name);
        this.inventar.remove(index);
    }

    private boolean frageObItemInsInventar(String name) { //Fehler bei fehlerhafte eingabe
        this.input = new Scanner(System.in);
        System.out.println("Soll das Item:" + name + " in das Inventar aufgenommen werden? J/N");
        return frageJNFrage();
    }

    private boolean frageJNFrage() {
        try {
            String in = input.next();
            return in.equalsIgnoreCase("j");
        } catch (Exception e) {
            throw new RuntimeException("Fehler bei Antwortentscheidung einer Ja Nein Frage - " + e.getMessage());
        }
    }

    public void zeigeInventar() {
        System.out.print("Dein Inventar beinhaltet: ");
        for (Item item : inventar) {
            System.out.print(item.getName() + " ");
        }
        System.out.println();
    }

    public void setInventar(ArrayList<Item> newInventar) {
        this.inventar = newInventar;
    }

    public void addItemToInventar() {
        System.out.print("Du findest: " );

        ArrayList<Item> tempItemList = inventar;
        ArrayList<Item> tempRoomItemsVorTausch = getCurrentRoom().getItemsFromRoom();



            for(int j =0; j<tempRoomItemsVorTausch.size(); j++){
                System.out.print(getCurrentRoom().getItemsFromRoom().get(j).getName()+ " ");
                System.out.println();
                if (addItemErfolgreich(getCurrentRoom().getItemsFromRoom().get(j))) {
                  tempRoomItemsVorTausch.remove(j);;
                }
        }
    }
    public int getLeben() { return this.Leben; }
    public void showLeben() {
        System.out.print("Leben: ");
        System.out.print("[");
        for (int i = 0; i < this.Leben; i++) {
            System.out.print("■");
        }
        System.out.println("]");
    }
    public void setLeben(int leben)  { this.Leben = leben; }

    public Item playerWeapon() {
        Item weapon = null;
        
        for(Item item : inventar){
            if(item.getIsWeapon()){
                weapon = item;
            }
        }
        return weapon;
    }
}
