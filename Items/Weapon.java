package Items;

public interface Weapon extends Item{
    int getDamage();
    boolean isEnchanted();
    void setEnchanted();
}
