package RaumSystem.Event;

import Items.Item;

import java.util.ArrayList;
import helper.ConsoleColors;

public class Gegner implements Event{
    private final String name = "Zombie";
    private final boolean forcedEvent = true;
    private int attackDamage = 2;
    private int leben = 15;

    @Override
    public void description() {
        System.out.println("Ich bin ein "+ ConsoleColors.YELLOW + "Zombie" + ConsoleColors.RESET);
        System.out.println("Ich mach dich platt, wenn du es nicht tust!");
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
