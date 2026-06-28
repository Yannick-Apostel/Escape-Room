package RaumSystem.Event;

import Items.Item;

import java.util.ArrayList;
import helper.ConsoleColors;
import Spieler.Player;

public class Gegner implements Enemy{
    private String name = "Zombie";
    private final boolean forcedEvent = true;
    private int attackDamage = 2;
    private int leben = 15;

    @Override
    public void description() {
        System.out.println("Ich bin ein "+ ConsoleColors.YELLOW + "Zombie" + ConsoleColors.RESET);
        System.out.println("Ich mach dich platt, wenn du es nicht tust!");
    }

    @Override
    public void aktion(ArrayList<Item> inventory, Player player) {
        new Kampf(player, this);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isForced() {
        return this.forcedEvent;
    }

    @Override
    public int getattackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getEnemyHealth() {
        return this.leben;
    }

    public void setName(String name) {
        this.name = name;
    }
}
