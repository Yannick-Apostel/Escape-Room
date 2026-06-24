package RaumSystem;

import Items.Baseballschlaeger;
import Items.Heilungstrank;
import Items.Item;
import Items.Schlussel;
import RaumSystem.Event.Event;
import RaumSystem.Event.Gegner;
import RaumSystem.Event.verletztePerson;

import java.util.ArrayList;
import java.util.Scanner;

public class Room {

    private String name;
    private String description;
    private boolean istVerschlossen;
    private ArrayList<Item> items;
    private Event event;
    private int x;
    private int y;
    private String mapState = "hidden";

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

    public Room(String name, String description, boolean istVerschlossen, char item, char event, int x, int y){
        this.items = new ArrayList<>();
        this.name = name;
        this.description = description;
        this.istVerschlossen = istVerschlossen;
        this.x = x;
        this.y = y;
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
        if (direction.equals("e") || direction.equals("o")) return east;
        if (direction.equals("w")) return west;
        return null;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getMapState() {
        return mapState;
    }

    public void setMapState(String mapState) {
        this.mapState = mapState;
    }

    public boolean getIstVerschlossen(){return this.istVerschlossen;}
    public void setIstVerschlossen(){this.istVerschlossen = !this.istVerschlossen;}

    private void addItemToList(char name){
        if(name == 'S'){
            Schlussel schlussel = new Schlussel();
            items.add(schlussel);
        } else if(name == 'B'){
            Baseballschlaeger basi = new Baseballschlaeger();
            items.add(basi);
        } else if(name == 'H'){
            Heilungstrank heilTrank = new Heilungstrank();
            items.add(heilTrank);
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
        }else if(event == 'V'){
            this.event = new verletztePerson();
        }else{
            this.event = null;
        }
    }

    public boolean isForcedEvent() {
        if (this.event != null) {
            return this.event.isForced();
        }
        return false;
    }

    public boolean hasEvent(){
        if(this.event != null)
            return true;

        return false;
    }

    public void startEvent(ArrayList<Item> inventar){
        Scanner scanner = new Scanner(System.in);

        if(hasEvent()){
            if(isForcedEvent()) {
                this.event.description();
                this.event.aktion(inventar);
            } else {
                this.event.description();
                System.out.print("> ");
                String eventInput = scanner.nextLine();

                if (eventInput.equals("y")) {
                    this.event.aktion(inventar);
                }

            }


            //TODO Kompatibilität mit anderen Events(nicht nur Gegner event)
//            ArrayList<Item> weapons = new ArrayList<>();
//            for(Item item : inventar){
//                if(item.getIsWeapon()){
//                    weapons.add(item);
//                }
//            }
//
//            if(weapons.isEmpty()){
//                System.out.println("Du kannst deinen Gegner nicht angreifen.");
//                System.out.println("Suche etwas womit du deinen Gegner angreifen kannst!");
//            }else{
//                event.angriff();
//                this.event = null;
//            }
        }
    }
}


