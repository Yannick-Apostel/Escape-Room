package Items;

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
        System.out.println("║ Ein altes, leicht angerostetes         ║");
        System.out.println("║ Schwert. Es wirkt noch gefährlich.     ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public String getName() {
        return this.name;
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
}
