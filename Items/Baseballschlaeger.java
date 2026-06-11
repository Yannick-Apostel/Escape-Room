package Items;

public class Baseballschlaeger implements Item{
    private final String name = "Baseballschlaeger";
    private final boolean isWeapon = true;
    private final boolean isHealingItem = false;

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
}
