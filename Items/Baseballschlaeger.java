package Items;

public class Baseballschlaeger implements Item{
    private final String name = "Baseballschlaeger";
    private final boolean isWeapon = true;

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
}
