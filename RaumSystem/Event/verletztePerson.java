package RaumSystem.Event;

import Items.Item;
import Items.Schlussel;
import Spieler.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class verletztePerson implements Event{
    private final String name = "person";
    private final boolean forcedEvent = false;

    @Override
    public void description() {
        System.out.println("Eine Person liegt in der Ecke des Raumes.");
        System.out.println("Sie scheint verletzt zu sein.");
        System.out.println("Möchtest du die ansprechen?[y/n]");
    }

    @Override
    public void aktion(ArrayList<Item> inventory) {
        boolean hasHealingItem = false;
        String itemName = "";


        System.out.println("Du: Halli Hallo!");
        System.out.println("???: Ich bin stark verletzt!");
        System.out.println("???: Wenn du mich heilst dann gebe ich dir was zurück, versprochen!");

        for(Item item : inventory){
            if(item.getIsHealingItem()){
                hasHealingItem = true;
                itemName = item.getName();
            }
        }

        if(hasHealingItem){
            System.out.println("Möchtest du der Person deinen " + itemName + " geben?[y/n]");
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String giveItem = scanner.nextLine();

            if(giveItem.equals("y")){
                final String itemNameToRemove = itemName;

                inventory.removeIf(item -> item.getName().equals(itemNameToRemove));

                System.out.println("Danke bro, hier, ich hab das gefunden, du kannst es haben!");

                inventory.add(new Schlussel());

                System.out.println("Du hast einen schlüssel bekommen!");
            }

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
