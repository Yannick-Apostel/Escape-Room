package Items;

import HighScore.HighScoreController;
import Spieler.Player;

public interface Item {
     String name = "";
     boolean isWeapon = false;
     boolean isHealingItem = false;

    //TODO: Funktion mit Usecase hinzufügen
    public void function();

    public String getName();
    public String getColoredName();
    public boolean getIsWeapon();
    public boolean getIsHealingItem();
    public void use(Player player, Item useItem, HighScoreController highScoreController);
}
