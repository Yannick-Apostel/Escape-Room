package Spieler;

import Items.Item;
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

        currentRoom.startEvent(inventar);
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
                writer.write("Items:" );
                for(Item item: inventar){
                    writer.write(item.getName()+";");
                }
                writer.close();
            } catch(IOException e) {
                System.out.println("Fehler beim Speichern der Datei.");
            }
        }
        else{
            System.out.println("Dein Inventar ist voll. Bitte leere dein Inventar, bevor du ein neues Item hinzufügst");
        }
    }

    public boolean existiertItemImInventar(String name){
        for(Item element : this.inventar){
            if(element.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private  int getIndexFromItemImInventar(String name){
        if(existiertItemImInventar(name)){
           for(int i =0; i<this.inventar.size(); i++){
               if(this.inventar.get(i).getName().equals(name)){
                   return i;
               }
           }
        }
        throw new RuntimeException("Fehler - Item konnte nicht gefunden werde");
    }

    public void deleteItemFromInventar(String name){
        int index = getIndexFromItemImInventar(name);
        this.inventar.remove(index);
    }


}
