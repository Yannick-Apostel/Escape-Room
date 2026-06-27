package RaumSystem.Event;

import Items.Item;

import java.util.ArrayList;
import helper.ConsoleColors;
import Spieler.Player;

public class BossGegner implements Enemy{
    private final String name = "Boss";
    private final boolean forcedEvent = false;
    private int attackDamage = 5;
    private int leben = 30;

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
}
