package Items;

import Spieler.Player;
import helper.ConsoleColors;

public class Schwert implements Weapon {
    private final String name = "Schwert";
    private final boolean isWeapon = true;
    private final boolean isHealingItem = false;
    private int damage = 7;
    private boolean isEnchanted = false;

    @Override
    public void function() {
        System.out.println();

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║               ITEM INFO                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Name: Schwert                          ║");
        System.out.println("║ Typ:  Waffe                            ║");
        System.out.println("║ Schaden: "+damage+"                             ║");
        System.out.println("║                                        ║");
        System.out.println("║ Beschreibung:                          ║");
        System.out.println("║ Ein qualitativ hochwertiges Schwert    ║");
        System.out.println("║ mit scharfer Klinge. Es liegt gut in   ║");
        System.out.println("║ der Hand und wirkt sehr zuverlässig.   ║");
        System.out.println("╚════════════════════════════════════════╝");
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
    public boolean getIsHealingItem() {
        return this.isHealingItem;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void use(Player player, Item useItem) {

    }

    @Override
    public boolean isEnchanted() {
        return this.isEnchanted;
    }

    @Override
    public void setEnchanted() {
        if (!this.isEnchanted) {
            this.damage += 2;
            this.isEnchanted = true;
        }
    }
}
