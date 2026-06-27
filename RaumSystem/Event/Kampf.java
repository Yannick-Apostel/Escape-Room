package RaumSystem.Event;

import Spieler.Player;
import Items.Weapon;

public class Kampf {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public Kampf(Player player, Enemy gegner) {
         Weapon playerWeapon = player.playerWeapon();
         int playerDamage = playerWeapon.getDamage();
         int playerHealth = player.getLeben();
         int enemyDamage = gegner.getattackDamage();
         int enemyHealth = gegner.getEnemyHealth();

         System.out.println("Kampf START!");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

         while(playerHealth > 0 && enemyHealth > 0) {
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }

             System.out.println("Du greifst an! Du machst " + RED + playerDamage + RESET + " schaden!");

             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }

             enemyHealth = enemyHealth - playerDamage;
             System.out.println("Der " + YELLOW + gegner.getName() + RESET + " hat noch " + GREEN + enemyHealth + RESET + " leben.");

             if (enemyHealth <= 0) {
                 System.out.println(YELLOW + gegner.getName() + RESET + " besiegt!");
                 break;
             }
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }

             System.out.println("Dein " + YELLOW + gegner.getName() + RESET + " greift an! Er macht " + RED + enemyDamage + RESET + " schaden!");

             try {
                 Thread.sleep(1000);
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
