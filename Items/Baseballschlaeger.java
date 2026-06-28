package Items;

import helper.ConsoleColors;

public class Baseballschlaeger implements Weapon{
    private final String name = "Baseballschlaeger";
    private final boolean isWeapon = true;
    private final boolean isHealingItem = false;
    private int damage = 3;

    @Override
    public void function() {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║              ITEM INFO             ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ Name: Baseballschlaeger            ║");
        System.out.println("║ Typ:  Waffe                        ║");
        System.out.println("║ Schaden: 3                         ║");
        System.out.println("║ Beschreibung:                      ║");
        System.out.println("║ Beschreibung:                      ║");
        System.out.println("║ Ein stabiler Baseballschläger aus  ║");
        System.out.println("║ Holz. Nicht elegant, aber effektiv.║");
        System.out.println("╚════════════════════════════════════╝");
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
    public boolean getIsHealingItem() {return this.isHealingItem;}

    @Override
    public int getDamage() {return this.damage;}
}
