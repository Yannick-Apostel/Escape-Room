package Spieler;

import RaumSystem.Room;

import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventar;
    private int maxSizeInventar =5;

    public Player(Room startRoom) {
        currentRoom = startRoom;
        inventar = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
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
        }
        else{
            System.out.println("Dein Inventar ist voll. Bitte leere dein Inventar, bevor du ein neues Item hinzufügst");
        }
    }
}
