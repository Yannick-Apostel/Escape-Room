package RaumSystem;

import Items.Item;
import Items.Schlussel;
import RaumSystem.Event.Event;
import RaumSystem.Event.Gegner;

import java.util.ArrayList;

public class Room {

    private String name;
    private String description;
    private boolean istVerschlossen;
    private ArrayList<Item> items;
    private Event event;

    private Room north;
    private Room south;
    private Room east;
    private Room west;

    @Deprecated
    public Room(String name, String description) {
        this.name = name;
        this.description = description;

        //Muss für den Startraum initialisiert werden, da sonst Nullpointer Exception geworfen wird
        this.items = new ArrayList<>();
    }

    public Room(String name, String description, boolean istVerschlossen, char item, char event){
        this.items = new ArrayList<>();
        this.name = name;
        this.description = description;
        this.istVerschlossen = istVerschlossen;
        addItemToList(item);
        createEvent(event);
    }

    public void setExits(Room north, Room south, Room east, Room west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public void setExits(String direction, Room room) {
        if (direction.toLowerCase().equals("n")) {
            this.north = room;
        } else if (direction.toLowerCase().equals("e")) {
            this.east = room;
        } else if (direction.toLowerCase().equals("s")) {
            this.south = room;
        } else if (direction.toLowerCase().equals("w")) {
            this.west = room;
        }
    }

    public Room getExit(String direction) {
        if (direction.equals("n")) return north;
        if (direction.equals("s")) return south;
        if (direction.equals("o")) return east;
        if (direction.equals("w")) return west;
        return null;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public boolean getIstVerschlossen(){return this.istVerschlossen;}
    public void setIstVerschlossen(){this.istVerschlossen = !this.istVerschlossen;}

    private void addItemToList(char name){
        if(name == 'S'){
            Schlussel schlussel = new Schlussel();
            items.add(schlussel);
        }
    }

    public ArrayList<Item> getItemsFromRoom(){
        return this.items;
    }

    public void removeItemsFromRoom(){
        //Nach Aufsammeln der Items soll der Raum keine Items mehr beinhalten
        this.items.clear();
    }

    public boolean hasRoomItems(){
        if(this.items.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public void createEvent(char event){
        if(event == 'Z'){
            this.event= new Gegner();
            System.out.println("Zombie erstellt");
        }else{
            this.event = null;
        }
    }

    private boolean hasEvent(){
        if(this.event != null)
            return true;

        return false;
    }

    public void startEvent(){
        if(hasEvent()){
            this.event.function();
        }
    }
}


