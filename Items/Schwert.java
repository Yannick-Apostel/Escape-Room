package Items;

import Spieler.Player;
import helper.ConsoleColors;

public class Schwert implements Weapon {
    private final String name = "Schwert";
    private final boolean isWeapon = true;
    private final boolean isHealingItem = false;
    private int damage = 7;

    @Override
    public void function() {
        System.out.println();

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║               ITEM INFO                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Name: Schwert                          ║");
        System.out.println("║ Typ:  Weapon                           ║");
        System.out.println("║ Schaden: 7                             ║");
        System.out.println("║                                        ║");
        System.out.println("║ Beschreibung:                          ║");
        System.out.println("║ Ein qualitativ hochwertiges Schwert    ║");
        System.out.println("║ mit scharfer Klinge. Es liegt gut in   ║");
        System.out.println("║ der Hand und wirkt sehr zuverlässig.   ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColoredName() {
        return ConsoleColors.BLUE_BRIGHT + this.name + ConsoleColors.RESET;
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
}
