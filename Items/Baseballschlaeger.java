package Items;

import Spieler.Player;
import helper.ConsoleColors;
import helper.Sleeper;
import Items.Item;

import java.util.Scanner;

public class Baseballschlaeger implements Weapon{
    private final String name = "Baseballschlaeger";
    private final boolean isWeapon = true;
    private final boolean isHealingItem = false;
    private int damage = 3;
    private boolean isEnchanted = false;

    @Override
    public void function() {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║              ITEM INFO             ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ Name: Baseballschlaeger            ║");
        System.out.println("║ Typ:  Waffe                        ║");
        System.out.println("║ Schaden: "+damage+"                         ║");   //Platz hinter nummer: "                         "
        System.out.println("║                                    ║");
        System.out.println("║ Beschreibung:                      ║");
        System.out.println("║ Ein stabiler Baseballschläger aus  ║");
        System.out.println("║ Holz. Nicht elegant, aber effektiv.║");
        System.out.println("╚════════════════════════════════════╝");
    }

    @Override
    public String getName() {
        if (isEnchanted) {
            return "★" + this.name + "★";
        }
        return this.name;
    }

    @Override
    public String getColoredName() {
        return ConsoleColors.BLUE_BRIGHT + getName() + ConsoleColors.RESET;
    }

    @Override
    public boolean getIsWeapon() {
        return this.isWeapon;
    }

    @Override
    public boolean getIsHealingItem() {return this.isHealingItem;}

    @Override
    public int getDamage() {return this.damage;}

    @Override
    public void use(Player player, Item useItem) {
        Scanner scanner = new Scanner(System.in);

        if (player.getCurrentRoom().getX() == 1 && player.getCurrentRoom().getY() == 1) {
            System.out.println("Willst du die verletzte Person verkloppen? [y/n]");

            String answer = scanner.nextLine();

            if (answer.equals("y")) {
                System.out.println("Die verletzte Person wehrt sich, aber du besiegst ihn.");
                Sleeper.sleep(1000);
                System.out.println("Du findest einen Gegenstand in seiner Tasche!");
                System.out.println("Du findest einen Upgradestein!");
                player.addItemErfolgreich(new UpgradeStein());
                //TODO weitermachen
            }
        }
    }

    @Override
    public boolean isEnchanted() {
        return isEnchanted;
    }

    @Override
    public void setEnchanted() {
        if (!this.isEnchanted) {
            this.damage += 2;
            this.isEnchanted = true;
        }
    }
}
