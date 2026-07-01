package Items;

import HighScore.HighScoreController;
import Spieler.Player;
import helper.ConsoleColors;

import java.util.Scanner;

public class Lederrüstung implements Armor {
    private final String name = "Lederruestung";
    private final boolean isWeapon = false;
    private final boolean isArmor = true;
    private final boolean isHealingItem = false;
    private int defense = 2;
    private boolean isEnchanted = false;

    @Override
    public void function() {
        System.out.println();

        if (!isEnchanted) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║               ITEM INFO                ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ Name: Lederruestung                    ║");
            System.out.println("║ Typ:  Rüstung                          ║");
            System.out.println("║ Rüstung: "+defense+"                             ║");
            System.out.println("║                                        ║");
            System.out.println("║ Beschreibung:                          ║");
            System.out.println("║ Eine leichte Lederrüstung bietet       ║");
            System.out.println("║ Schutz, ohne dich stark zu bremsen.    ║");
            System.out.println("║ Sie ist abgenutzt, aber verlässlich.   ║");
            System.out.println("╚════════════════════════════════════════╝");
        } else {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║               ITEM INFO                ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ Name: Lederruestung"+ ConsoleColors.PURPLE_BOLD_BRIGHT +"(Verzaubert)"+ConsoleColors.RESET+"           ║");
            System.out.println("║ Typ:  Rüstung                          ║");
            System.out.println("║ Rüstung: "+ConsoleColors.PURPLE_BOLD_BRIGHT+defense+ConsoleColors.RESET+"                             ║");
            System.out.println("║                                        ║");
            System.out.println("║ Beschreibung:                          ║");
            System.out.println("║ Eine leichte Lederrüstung bietet       ║");
            System.out.println("║ Schutz, ohne dich stark zu bremsen.    ║");
            System.out.println("║ Sie ist abgenutzt, aber verlässlich.   ║");
            System.out.println("╚════════════════════════════════════════╝");

        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getColoredName() {
        if (isEnchanted) {
            return ConsoleColors.YELLOW_BRIGHT + "★" + getName() + "★" + ConsoleColors.RESET;
        }
        return ConsoleColors.YELLOW_BRIGHT + getName() + ConsoleColors.RESET;
    }

    @Override
    public boolean getIsWeapon() {
        return this.isWeapon;
    }

    @Override
    public boolean getIsHealingItem() {
        return this.isHealingItem;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public void use(Player player, Item useItem, HighScoreController highScoreController) {
        if(player.getCurrentRoom().getName().equals("Büro")) {
            System.out.println("Willst du deine "+ConsoleColors.YELLOW_BRIGHT+"Lederrüstung"+ConsoleColors.RESET+" wirklich auf den Rüstungsständer packen?");
            System.out.println("Du wirst sie "+ConsoleColors.RED+"permanent"+ConsoleColors.RESET+" verlieren! [y/n]");

            Scanner scanner = new Scanner(System.in);

            if(scanner.nextLine().equals("y")) {
                player.deleteItemFromInventar(useItem.getName());
                Schwert schwert= new Schwert();

                System.out.println("Du legst deine Rüstung auf den Rüstungsständer und wartest...");
                System.out.println("Nach einigen Sekunden öffnet sich loch in der Wand!");
                System.out.println("In dem Geheimraum befindet sich eine Schatztruhe!");
                System.out.println("In der Kiste findest du ein " + schwert.getName() + "!");
                player.addItemErfolgreich(schwert);
                highScoreController.addHighScore(20);
            }
        }
    }

    @Override
    public boolean isArmor() {
        return this.isArmor;
    }

    @Override
    public boolean isEnchanted() {
        return this.isEnchanted;
    }

    @Override
    public void setEnchanted() {
        if (!this.isEnchanted) {
            this.defense += 1;
            this.isEnchanted = true;
        }
    }
    //TODO raum hat leeren rüstungsständer, wenn diese rüstung im raum geused wird dann kannst du die rüstung darauf setzen, das öffnet ein secret, und zwar öffnet sich ein raum mit einer lootkiste
}
