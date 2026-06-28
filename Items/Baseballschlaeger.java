package Items;

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
        System.out.println("║ Ein rostiger Schlüssel. Er sieht   ║");
        System.out.println("║ aus, als könnte er eine alte Tür   ║");
        System.out.println("║ öffnen.                            ║");
        System.out.println("╚════════════════════════════════════╝");
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
    public boolean getIsHealingItem() {return this.isHealingItem;}

    @Override
    public int getDamage() {return this.damage;}
}
