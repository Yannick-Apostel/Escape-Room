package Items;

import Spieler.Player;

public class Staerketrank implements Item {
    private static final String ORANGE = "\033[38;5;208m";
    private static final String RESET = "\033[0m";
    private final String name = "Staerketrank";
    private final boolean isWeapon = false;
    private final boolean isHealingItem = false;
    private int attackBoost = 5;

    @Override
    public void function() {
        System.out.println();

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║               ITEM INFO                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Name: Stärketrank                      ║");
        System.out.println("║ Typ:  Item                             ║");
        System.out.println("║ Schaden: +"+attackBoost+"                            ║");
        System.out.println("║                                        ║");
        System.out.println("║ Beschreibung:                          ║");
        System.out.println("║ Eine kleine Flasche mit grünem         ║");
        System.out.println("║ Gebraeu. Erhöht deinen Angriff.        ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColoredName() {
        return ORANGE + this.name + RESET;
    }

    @Override
    public boolean getIsWeapon() {
        return this.isWeapon;
    }

    @Override
    public boolean getIsHealingItem() {
        return this.isHealingItem;
    }

    public int getAttackBoost() {return this.attackBoost;}

    @Override
    public void use(Player player, Item useItem) {
        player.setAttackDamage(player.getAttackDamage() + attackBoost);
        System.out.println("Du machst jetzt "+attackBoost+" mehr Schaden!");
    }
}
