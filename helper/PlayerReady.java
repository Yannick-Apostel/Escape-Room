package helper;

import RaumSystem.Event.BossGegner;
import Spieler.Player;

public class PlayerReady {
    public enum CheckResult {
        YES,
        NO,
        MAYBE,
        HELLNO,
        ERROR
    }

    public static CheckResult isPlayerReady(Player player) {
        if(player.playerWeapon() == null || player.playerArmor() == null) {
            return CheckResult.HELLNO;
        } else if(player.playerWeapon().getName().equals("Baseballschlaeger") || player.playerArmor().getName().equals("Lederruestung")) {
            return CheckResult.NO;
        } else if(!player.playerWeapon().isEnchanted() || !player.playerArmor().isEnchanted()) {
            return CheckResult.MAYBE;
        } else if(player.playerWeapon().isEnchanted() && player.playerArmor().isEnchanted()) {
            return CheckResult.YES;
        }
        return CheckResult.ERROR;
    }

    public static String checkIsReady(Player player) {
        if(isPlayerReady(player) == CheckResult.HELLNO) {
            return "╔════════════════════════════════════╗\n" +
                    "║             BOSS STATUS            ║\n" +
                    "╠════════════════════════════════════╣\n" +
                    "║ Name: Eisenwächter                 ║\n" +
                    "║ Typ:  Gegner                       ║\n" +
                    "║ Risiko: "+ConsoleColors.RED_BOLD_BRIGHT+"KRITISCH"+ConsoleColors.RESET+"                   ║\n" +
                    "║                                    ║\n" +
                    "║ Beschreibung:                      ║\n" +
                    "║ Ein sehr Starker Gegner, der nur   ║\n" +
                    "║ mit perfekter Ausrüstung besiegt   ║\n" +
                    "║ werden kann. Jede falsche Item-    ║\n" +
                    "║ Entscheidung kann dich hier kosten.║\n" +
                    "╚════════════════════════════════════╝";
        } else if(isPlayerReady(player) == CheckResult.NO) {
            return "╔════════════════════════════════════╗\n" +
                    "║             BOSS STATUS            ║\n" +
                    "╠════════════════════════════════════╣\n" +
                    "║ Name: Eisenwächter                 ║\n" +
                    "║ Typ:  Gegner                       ║\n" +
                    "║ Risiko: "+ConsoleColors.RED+"HOCH"+ConsoleColors.RESET+"                       ║\n" +
                    "║                                    ║\n" +
                    "║ Beschreibung:                      ║\n" +
                    "║ Du hast zwar Ausrüstung, aber noch ║\n" +
                    "║ nicht die besten Items. Gegen den  ║\n" +
                    "║ Boss reicht schwache Ausrüstung    ║\n" +
                    "║ nicht aus.                         ║\n" +
                    "╚════════════════════════════════════╝";
        } else if(isPlayerReady(player) == CheckResult.MAYBE) {
            return "╔════════════════════════════════════╗\n" +
                    "║             BOSS STATUS            ║\n" +
                    "╠════════════════════════════════════╣\n" +
                    "║ Name: Eisenwächter                 ║\n" +
                    "║ Typ:  Gegner                       ║\n" +
                    "║ Risiko: "+ConsoleColors.YELLOW_BOLD_BRIGHT+"GEFÄHRLICH"+ConsoleColors.RESET+"                 ║\n" +
                    "║                                    ║\n" +
                    "║ Beschreibung:                      ║\n" +
                    "║ Deine Ausrüstung ist stark, aber   ║\n" +
                    "║ noch nicht perfekt vorbereitet.    ║\n" +
                    "║ Ohne Verzauberungen wird der Boss  ║\n" +
                    "║ dich wahrscheinlich besiegen.      ║\n" +
                    "╚════════════════════════════════════╝";
        } else if(isPlayerReady(player) == CheckResult.YES) {
            return "╔════════════════════════════════════╗\n" +
                    "║             BOSS STATUS            ║\n" +
                    "╠════════════════════════════════════╣\n" +
                    "║ Name: Eisenwächter                 ║\n" +
                    "║ Typ:  Gegner                       ║\n" +
                    "║ Risiko: "+ConsoleColors.GREEN_BOLD_BRIGHT+"BEREIT"+ConsoleColors.RESET+"                    ║\n" +
                    "║                                    ║\n" +
                    "║ Beschreibung:                      ║\n" +
                    "║ Du hast die beste Ausrüstung und   ║\n" +
                    "║ sie ist vollständig verzaubert.    ║\n" +
                    "║ Jetzt hast du eine echte Chance,   ║\n" +
                    "║ den Boss zu besiegen.              ║\n" +
                    "╚════════════════════════════════════╝";
        }
        return null;
    }
}
