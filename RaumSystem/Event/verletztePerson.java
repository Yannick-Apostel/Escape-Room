package RaumSystem.Event;

import Items.Item;
import Items.Staerketrank;
import Spieler.Player;
import helper.ConsoleColors;

import java.util.ArrayList;
import java.util.Scanner;

public class verletztePerson implements Event{
    private final String name = "person";
    private final boolean forcedEvent = false;

    @Override
    public void description() {
        System.out.println("Eine Person liegt in der Ecke des Raumes.");
        System.out.println("Sie scheint verletzt zu sein.");
        System.out.println("Möchtest du die ansprechen? [y/n]");
    }

    @Override
    public void aktion(ArrayList<Item> inventory, Player player) {
        boolean hasHealingItem = false;
        String itemName = "";
        Item healingItem = null;


        System.out.println("Du: Halli Hallo!");
        System.out.println("???: Ich bin stark verletzt!");
        System.out.println("???: Wenn du mich heilst dann gebe ich dir was zurück, versprochen!");

        for(Item item : inventory){
            if(item.getIsHealingItem()){
                hasHealingItem = true;
                itemName = item.getColoredName();
                healingItem = item;
            }
        }

        if(hasHealingItem){
            System.out.println("Möchtest du der Person deinen " + itemName + " geben? [y/n]");
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String giveItem = scanner.nextLine();

            if(giveItem.equals("y")){
                final Item itemToRemove = healingItem;

                inventory.removeIf(item -> item == itemToRemove);

                System.out.println("Danke bro, hier, ich hab das gefunden, du kannst es haben!");

                inventory.add(new Staerketrank());

                System.out.println("Du hast einen " + ConsoleColors.ORANGE + "Stärketrank" + ConsoleColors.RESET + " bekommen!");
            }

        } else {
            System.out.println("Du hast kein Item um die person zu heilen...");
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isForced() {
        return this.forcedEvent;
    }
}
