package RaumSystem.Event;

import helper.ConsoleColors;

public class Gegner implements Event{
    private final String name = "Zombie";

    @Override
    public void function() {
        System.out.println("Ich bin ein "+ ConsoleColors.YELLOW + "Zombie" + ConsoleColors.RESET);
        System.out.println("Ich mach dich platt, wenn du es nicht tust!");
    }

    @Override
    public void angriff() {
        //TODO Zufallsgen.
       System.out.println("Gegner besiegt - Glückwunsch");
    }


    @Override
    public String getName() {
        return this.name;
    }
}
