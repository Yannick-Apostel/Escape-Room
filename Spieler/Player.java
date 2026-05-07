package Spieler;

import RaumSystem.Room;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventar;
    private int maxSizeInventar = 5;

    public Player(Room startRoom) {
        currentRoom = startRoom;
        inventar = new ArrayList<>();
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
        } catch(IOException e) {
            System.out.println("Fehler beim Speichern der Datei.");
        }

    }

    public ArrayList<Item> getInventar(){
        return  this.inventar;
    }

    public Item getInventar(int index){
        return this.inventar.get(index);
    }

    public void addItem(Item newItem){
        if(this.inventar.size()< maxSizeInventar){
            this.inventar.add(newItem);

            try {
                FileWriter writer = new FileWriter("saveData.csv");
                writer.write("Items;" + this.getInventar(0) + this.getInventar(1) + this.getInventar(2) + this.getInventar(3) + this.getInventar(4));
                writer.close();
            } catch(IOException e) {
                System.out.println("Fehler beim Speichern der Datei.");
            }
        }
        else{
            System.out.println("Dein Inventar ist voll. Bitte leere dein Inventar, bevor du ein neues Item hinzufügst");
        }
    }
}
