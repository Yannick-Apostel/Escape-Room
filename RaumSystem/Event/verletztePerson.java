package RaumSystem.Event;

import Items.Item;

import java.util.ArrayList;

public class verletztePerson implements Event{
    private final String name = "person";
    private final boolean forcedEvent = false;

    @Override
    public void description() {
        System.out.println("Eine Person liegt in der Ecke des Raumes.");
        System.out.println("Sie scheint verletzt zu sein.");
        System.out.println("Möchtest du die ansprechen?");
    }

    @Override
    public void aktion(ArrayList<Item> inventory) {

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isForced() {
        return this.forcedEvent;
    }
}
