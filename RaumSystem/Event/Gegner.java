package RaumSystem.Event;

public class Gegner implements Event{
    private final String name = "Zombie";
    private final boolean forcedEvent = true;

    @Override
    public void description() {
        System.out.println("Ich bin ein Zombie!");
        System.out.println("Ich mach die platt, wenn du es nicht tust!");
    }

    @Override
    public void aktion() {

        //TODO Zufallsgen.
       //System.out.println("Gegner besiegt - Glückwunsch");
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
