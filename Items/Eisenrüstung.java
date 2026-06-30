package Items;

import Spieler.Player;
import helper.ConsoleColors;

public class Eisenrüstung implements Armor {
    private final String name = "Eisenruestung";
    private final boolean isWeapon = false;
    private final boolean isArmor = true;
    private final boolean isHealingItem = false;
    private int defense = 5;
    private boolean isEnchanted = false;

    @Override
    public void function() {
        System.out.println();

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║               ITEM INFO                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Name: Eisenruestung                    ║");
        System.out.println("║ Typ:  Rüstung                          ║");
        System.out.println("║ Schaden: "+defense+"                             ║");
        System.out.println("║                                        ║");
        System.out.println("║ Beschreibung:                          ║");
        System.out.println("║ Eine schwere Eisenrüstung schützt      ║");
        System.out.println("║ dich mit stabilen Metallplatten vor    ║");
        System.out.println("║ harten Treffern im Kampf.              ║");
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
        return ConsoleColors.YELLOW_BRIGHT + getName() + ConsoleColors.RESET;
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

    @Override
    public boolean isEnchanted() {
        return this.isEnchanted;
    }

    @Override
    public void setEnchanted() {
        if (!this.isEnchanted) {
            this.defense += 4;
            this.isEnchanted = true;
        }
    }
    //TODO raum hat leeren rüstungsständer, wenn diese rüstung im raum geused wird dann kannst du die rüstung darauf setzen, das öffnet ein secret, und zwar öffnet sich ein raum mit einer lootkiste
}
