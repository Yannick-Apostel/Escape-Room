package Items;

import helper.ConsoleColors;

public class Schlussel implements Item{

    private final String name = "Schlussel";
    private final boolean isWeapon = false;
    private final boolean isHealingItem = false;

    @Override
    public void function() {
        System.out.println();

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║               ITEM INFO                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Name: Schlüssel                        ║");
        System.out.println("║ Typ:  Item                             ║");
        System.out.println("║                                        ║");
        System.out.println("║ Beschreibung:                          ║");
        System.out.println("║ Ein alter Metallschlüssel. Vielleicht  ║");
        System.out.println("║ öffnet er eine verschlossene Tür.      ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColoredName() {
        return ConsoleColors.GREEN_BRIGHT + this.name + ConsoleColors.RESET;
    }

    @Override
    public boolean getIsWeapon() {
        return this.isWeapon;
    }

    @Override
    public boolean getIsHealingItem() {return this.isHealingItem;}
}
