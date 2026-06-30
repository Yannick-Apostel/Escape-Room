package Items;

import Spieler.Player;
import helper.ConsoleColors;

import java.sql.SQLOutput;
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
    public void use(Player player, Item useItem) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Du bist dabei einen " + ConsoleColors.PURPLE_BOLD_BRIGHT + this.name + ConsoleColors.RESET + " zu benutzen! Willst du das wirklich? [y/n]");
        String answer = scanner.nextLine();

        if(answer.equals("y")) {
            System.out.println("Welches Item willst du verzaubern?");
        }


        //TODO logik für max 1 rüstung
    }
}
