package RaumSystem.Event;

import Items.Item;

import java.util.ArrayList;

public class Gegner implements Event{
    private final String name = "Zombie";
    private final boolean forcedEvent = true;

    @Override
    public void description() {
        System.out.println("Ich bin ein Zombie!");
        System.out.println("Ich mach die platt, wenn du es nicht tust!");
    }

    @Override
    public void aktion(ArrayList<Item> inventory) {
        boolean hasWeapon = false;

        for(Item item : inventory){
            if(item.getIsWeapon()){
                hasWeapon = true;
            }
        }

        if (hasWeapon) {
            System.out.println("Zombie besiegt!");
        } else {
            System.out.println("TOT!");
        }
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
