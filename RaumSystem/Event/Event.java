package RaumSystem.Event;

import Items.Item;
import Spieler.Player;

import java.util.ArrayList;

public interface Event {
    String name = "";
    boolean forcedEvent = false;

    //TODO: Funktion mit Usecase hinzufügen
    public void description();

    public void aktion(ArrayList<Item> inventory, Player player);     //Jetzt musste ich jedem Event objekt dem Player mitgeben wegen Kampf, geht das anders?

    public String getName();

    public boolean isForced();
}
