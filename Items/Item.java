package Items;

public interface Item {
     String name = "";
     boolean isWeapon = false;
     boolean isHealingItem = false;

    //TODO: Funktion mit Usecase hinzufügen
    public void function();

    public String getName();
    public boolean getIsWeapon();
    public boolean getIsHealingItem();
}
