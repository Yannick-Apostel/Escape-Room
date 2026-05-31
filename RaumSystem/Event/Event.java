package RaumSystem.Event;

import Items.Item;

import java.util.ArrayList;

public interface Event {
    String name = "";
    boolean forcedEvent = false;

    //TODO: Funktion mit Usecase hinzufügen
    public void description();

    public void aktion(ArrayList<Item> inventory);

    public String getName();

    public boolean isForced();
}
