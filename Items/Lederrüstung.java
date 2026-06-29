package Items;

import Spieler.Player;
import helper.ConsoleColors;

public class Lederrüstung implements Armor {
    private final String name = "Lederruestung";
    private final boolean isWeapon = false;
    private final boolean isArmor = true;
    private final boolean isHealingItem = false;
    private int defense = 2;

    @Override
    public void function() {
        System.out.println();

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║               ITEM INFO                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Name: Lederrüstung                     ║");
        System.out.println("║ Typ:  Rüstung                          ║");
        System.out.println("║ Schaden: "+defense+"                             ║");
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
        return ConsoleColors.YELLOW_BRIGHT + this.name + ConsoleColors.RESET;
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
    public int getDefense() {
        return this.defense;
    }

    @Override
    public void use(Player player, Item useItem) {

    }

    @Override
    public boolean isArmor() {
        return this.isArmor;
    }
    //TODO raum hat leeren rüstungsständer, wenn diese rüstung im raum geused wird dann kannst du die rüstung darauf setzen, das öffnet ein secret, und zwar öffnet sich ein raum mit einer lootkiste
}
