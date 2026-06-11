package Items;

public class Heilungstrank implements Item{
    private final String name = "Heilungstrank";
    private final boolean isWeapon = false;
    private final boolean isHealingItem = true;

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
    public boolean getIsHealingItem() { return this.isHealingItem; }
}
