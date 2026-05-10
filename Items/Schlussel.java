package Items;

public class Schlussel implements Item{

    private final String name = "Schlussel";
    private final boolean isWeapon = false;

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
