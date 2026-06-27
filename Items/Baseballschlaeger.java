package Items;

public class Baseballschlaeger implements Weapon{
    private final String name = "Baseballschlaeger";
    private final boolean isWeapon = true;
    private final boolean isHealingItem = false;
    private int damage = 3;

    @Override
    public void function() {

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
