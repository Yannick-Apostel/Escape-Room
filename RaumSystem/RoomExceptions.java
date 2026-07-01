package RaumSystem;

import Spieler.Player;
import helper.PlayerReady;

import java.util.Scanner;

public class RoomExceptions {
    public enum roomReturn {
        ENTER,
        STAY
    }
    public static roomReturn main(Player player, Room nextRoom) {

        if(nextRoom.getName().equals("Bossraum")) {
            if(BossRoom(player) == false) {
                return roomReturn.STAY;
            }
        }
        return null;
    }
    public static boolean BossRoom(Player player) {
        System.out.println(PlayerReady.checkIsReady(player));
        System.out.println();
        System.out.println("Willst du wirklich in diesen Raum gehen? [y/n]");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if(input.equals("n")) {
            return false;
        }
        return true;
    }
}
