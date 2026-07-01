package Items;

import HighScore.HighScoreController;
import Spieler.Player;
import helper.ConsoleColors;

import java.util.Scanner;

public class UpgradeStein implements Item{

    private final String name = "Upgradestein";
    private final boolean isWeapon = false;
    private final boolean isHealingItem = false;

    @Override
    public void function() {
        System.out.println();

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║               ITEM INFO                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Name: Upgradestein                     ║");
        System.out.println("║ Typ:  Item                             ║");
        System.out.println("║                                        ║");
        System.out.println("║ Beschreibung:                          ║");
        System.out.println("║ Ein Seltenes Relikt! Versuche es zu    ║");
        System.out.println("║ benutzen um deine Waffen oder deine    ║");
        System.out.println("║ Rüstung zu verzaubern!                 ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColoredName() {
        return ConsoleColors.PURPLE_BOLD_BRIGHT + this.name + ConsoleColors.RESET;
    }

    @Override
    public boolean getIsWeapon() {
        return this.isWeapon;
    }

    @Override
    public boolean getIsHealingItem() {return this.isHealingItem;}

    @Override
    public void use(Player player, Item useItem, HighScoreController highScoreController) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Du bist dabei einen " + ConsoleColors.PURPLE_BOLD_BRIGHT + this.name + ConsoleColors.RESET + " zu benutzen! Willst du das wirklich? [y/n]");
        String answer = scanner.nextLine();

        if(answer.equals("y")) {
            System.out.println("Welches Item willst du verzaubern?");

            String answer2 = scanner.nextLine();

            for (Item item : player.getInventar()) {
                if (item.getName().equals(answer2)) {
                    if (item instanceof Weapon weapon) {
                        weapon.setEnchanted();
                        if(weapon.getName().equals("Schwert")) {
                            highScoreController.addHighScore(20);
                        } else {
                            highScoreController.addHighScore(10);
                        }
                    } else if (item instanceof Armor armor) {
                        armor.setEnchanted();
                        if(armor.getName().equals("Eisenruestung")) {
                            highScoreController.addHighScore(20);
                        } else {
                            highScoreController.addHighScore(10);
                        }
                    }
                }
            }
        }



    }
}
