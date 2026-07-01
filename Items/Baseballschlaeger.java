package Items;

import HighScore.HighScoreController;
import Spieler.Player;
import helper.ConsoleColors;
import helper.PlayerReady;
import helper.Sleeper;

import java.util.Scanner;

public class Baseballschlaeger implements Weapon{
    private final String name = "Baseballschlaeger";
    private final boolean isWeapon = true;
    private final boolean isHealingItem = false;
    private int damage = 3;
    private boolean isEnchanted = false;

    @Override
    public void function() {
        if(this.isEnchanted) {
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║              ITEM INFO             ║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║ Name: Baseballschlaeger"+ ConsoleColors.PURPLE_BOLD_BRIGHT +"(Verzaubert)"+ConsoleColors.RESET+"║");
            System.out.println("║ Typ:  Waffe                        ║");
            System.out.println("║ Schaden: "+ConsoleColors.PURPLE_BOLD_BRIGHT+damage+ConsoleColors.RESET+"                         ║");   //Platz hinter nummer: "                         "
            System.out.println("║                                    ║");
            System.out.println("║ Beschreibung:                      ║");
            System.out.println("║ Ein verzauberter Baseballschläger  ║");
            System.out.println("║ aus Holz. Er leuchtet leicht       ║");
            System.out.println("╚════════════════════════════════════╝");
        } else {
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║              ITEM INFO             ║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║ Name: Baseballschlaeger            ║");
            System.out.println("║ Typ:  Waffe                        ║");
            System.out.println("║ Schaden: "+damage+"                         ║");   //Platz hinter nummer: "                         "
            System.out.println("║                                    ║");
            System.out.println("║ Beschreibung:                      ║");
            System.out.println("║ Ein stabiler Baseballschläger aus  ║");
            System.out.println("║ Holz. Nicht elegant, aber effektiv.║");
            System.out.println("╚════════════════════════════════════╝");
        }

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColoredName() {
        if (isEnchanted) {
            return ConsoleColors.BLUE_BRIGHT + "★" + getName() + "★" + ConsoleColors.RESET;
        }
        return ConsoleColors.BLUE_BRIGHT + getName() + ConsoleColors.RESET;
    }

    @Override
    public boolean getIsWeapon() {
        return this.isWeapon;
    }

    @Override
    public boolean getIsHealingItem() {return this.isHealingItem;}

    @Override
    public int getDamage() {return this.damage;}

    @Override
    public void use(Player player, Item useItem, HighScoreController highScoreController) {
        Scanner scanner = new Scanner(System.in);

        if (player.getCurrentRoom().getX() == 1 && player.getCurrentRoom().getY() == 1) {
            System.out.println("Willst du die verletzte Person verkloppen? [y/n]");

            String answer = scanner.nextLine();

            if (answer.equals("y")) {
                System.out.println("Die verletzte Person wehrt sich, aber du besiegst ihn.");
                Sleeper.sleep(1000);
                System.out.println("Du findest einen Gegenstand in seiner Tasche!");
                System.out.println("Du findest einen Upgradestein!");
                player.addItemErfolgreich(new UpgradeStein());
                highScoreController.addHighScore(40);
            }
        } else if(player.getCurrentRoom().getName().equals("Bibliothek")) {
            System.out.println("Du zerschlägst das Fenster mit deinem "+ConsoleColors.BLUE_BRIGHT+"Baseballschläger"+ConsoleColors.RESET+ " das Fenster!");
            Sleeper.sleep(1000);
            System.out.println("Du lehnst dich aus dem Fenster und siehst das zum Osten der Ausgang ist!");
            System.out.println("Jedoch siehst du auch das der Ausgang von einem Monster bewacht wird");
            System.out.println(PlayerReady.checkIsReady(player));
            highScoreController.addHighScore(25);
        }

    }

    @Override
    public boolean isEnchanted() {
        return isEnchanted;
    }

    @Override
    public void setEnchanted() {
        if (!this.isEnchanted) {
            this.damage += 2;
            this.isEnchanted = true;
        }
    }
}
