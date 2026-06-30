package RaumSystem.Event;

import Items.Armor;
import Spieler.Player;
import Items.Weapon;
import helper.Sleeper;

public class Kampf {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\033[0;34m";
    private static final String RESET = "\u001B[0m";
//TODO  rüstung einrechnen
    public Kampf(Player player, Enemy gegner) {
         int playerDamage = player.getAttackDamage();
         int playerHealth = player.getLeben();
         int playerDefense = player.getDefense();

         int enemyDamage = gegner.getattackDamage();
         int enemyHealth = gegner.getEnemyHealth();

         if (player.playerWeapon() != null) {
             Weapon spielerWaffe = player.playerWeapon();
             playerDamage = playerDamage + spielerWaffe.getDamage();
         }

         if(player.playerArmor() != null) {
             Armor spielerArmor = player.playerArmor();
             playerDefense = playerDefense + spielerArmor.getDefense();
         }

         System.out.println("Kampf START!");

        Sleeper.sleep(500);

         while(playerHealth > 0 && enemyHealth > 0) {
             Sleeper.sleep(500);

             System.out.println("Du greifst an! Du machst " + RED + playerDamage + RESET + " schaden!");

             Sleeper.sleep(500);

             enemyHealth = enemyHealth - playerDamage;
             if (enemyHealth >= 0) {
                 System.out.println("Der " + YELLOW + gegner.getName() + RESET + " hat noch " + GREEN + enemyHealth + RESET + " leben.");
             }

             if (enemyHealth <= 0) {
                 System.out.println(YELLOW + gegner.getName() + RESET + " besiegt!");
                 break;
             }
             Sleeper.sleep(500);

             System.out.println("Der " + YELLOW + gegner.getName() + RESET + " greift an! Er macht " + RED + enemyDamage + RESET + " schaden!");

             Sleeper.sleep(500);

             System.out.println("Du blockst " + BLUE + playerDefense + RESET + " schaden!");

             Sleeper.sleep(500);

             playerHealth = playerHealth + playerDefense - enemyDamage;

             if (playerHealth >= 0) {
                 System.out.println("Du hast noch " + GREEN + playerHealth + RESET + " leben.");
             } //TODO else game over funktion
         }

         player.setLeben(playerHealth);
    }
}
