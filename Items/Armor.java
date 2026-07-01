package Items;

public interface Armor extends Item{
    public int getDefense();

    public boolean isArmor();

    boolean isEnchanted();
    void setEnchanted();
}
