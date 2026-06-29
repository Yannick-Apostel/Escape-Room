package Items;

import Spieler.Player;
import helper.ConsoleColors;

public class Heilungstrank implements Item{
    private final String name = "Heilungstrank";
    private final boolean isWeapon = false;
    private final boolean isHealingItem = true;
    private int healing = 15;

    @Override
    public void function() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║               ITEM INFO                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Name: Heilungstrank                    ║");
        System.out.println("║ Typ:  Item                             ║");
        System.out.println("║ Heilung: "+healing+"                            ║");        //Platz hinter nummer: "                            "
        System.out.println("║                                        ║");
        System.out.println("║ Beschreibung:                          ║");
        System.out.println("║ Eine kleine Flasche mit roter          ║");
        System.out.println("║ Flüssigkeit. Sie kann Wunden heilen.   ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColoredName() {
        return ConsoleColors.RED_BRIGHT + this.name + ConsoleColors.RESET;
    }

    @Override
    public boolean getIsWeapon() {
        return this.isWeapon;
    }

    @Override
    public boolean getIsHealingItem() { return this.isHealingItem; }

    public int getHealing() { return this.healing; }

    @Override
    public void use(Player player, Item useItem) {
        player.setLeben(player.getLeben() + healing);
        System.out.println("Du hast "+healing+" Leben geheilt!");
    }
}
