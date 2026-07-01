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

        if (!isEnchanted) {
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
        } else {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║               ITEM INFO                ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ Name: Schwert"+ ConsoleColors.PURPLE_BOLD_BRIGHT +"(Verzaubert)"+ConsoleColors.RESET+"              ║");
            System.out.println("║ Typ:  Waffe                            ║");
            System.out.println("║ Schaden: "+ConsoleColors.PURPLE_BOLD_BRIGHT+damage+ConsoleColors.RESET+"                             ║");
            System.out.println("║ Lebensraub: 2                          ║");
            System.out.println("║                                        ║");
            System.out.println("║ Beschreibung:                          ║");
            System.out.println("║ Ein qualitativ hochwertiges Schwert    ║");
            System.out.println("║ mit verzauberter Klinge. Es liegt gut  ║");
            System.out.println("║ in der Hand und wirkt sehr zuverlässig.║");
            System.out.println("║ Klaut dem Gegner ein Teil seines       ║");
            System.out.println("║ Lebens mit jedem Angriff.              ║");
            System.out.println("╚════════════════════════════════════════╝");

        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColoredName() {
        if (isEnchanted) {
            return ConsoleColors.BLUE_BRIGHT + "★" + getName() + "★" + ConsoleColors.RESET;
        }
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
