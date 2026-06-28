package RaumSystem.Event;

import Spieler.Player;
import Items.Weapon;

public class Kampf {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public Kampf(Player player, Enemy gegner) {
         int playerDamage = player.getAttackDamage();
         int playerHealth = player.getLeben();
         int enemyDamage = gegner.getattackDamage();
         int enemyHealth = gegner.getEnemyHealth();

         if (player.playerWeapon() != null) {
             Weapon spielerWaffe = player.playerWeapon();
             playerDamage = playerDamage + spielerWaffe.getDamage();
         }

         System.out.println("Kampf START!");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

         while(playerHealth > 0 && enemyHealth > 0) {
             try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }

             System.out.println("Du greifst an! Du machst " + RED + playerDamage + RESET + " schaden!");

             try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }

             enemyHealth = enemyHealth - playerDamage;
             if (enemyHealth >= 0) {
                 System.out.println("Der " + YELLOW + gegner.getName() + RESET + " hat noch " + GREEN + enemyHealth + RESET + " leben.");
             }

             if (enemyHealth <= 0) {
                 System.out.println(YELLOW + gegner.getName() + RESET + " besiegt!");
                 break;
             }
             try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }

             System.out.println("Der " + YELLOW + gegner.getName() + RESET + " greift an! Er macht " + RED + enemyDamage + RESET + " schaden!");

             try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }

             playerHealth = playerHealth - enemyDamage;

             if (playerHealth >= 0) {
                 System.out.println("Du hast noch " + GREEN + playerHealth + RESET + " leben.");
             } //TODO else game over funktion
         }

         player.setLeben(playerHealth);
    }
}
