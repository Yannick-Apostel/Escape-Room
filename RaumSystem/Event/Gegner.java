package RaumSystem.Event;

public class Gegner implements Event{
    private final String name = "Zombie";
    private final boolean forcedEvent = true;

    @Override
    public void function() {
        System.out.println("Ich bin ein Zombie");
        System.out.println("Ich mach die platt, wenn du es nicht tust!");
    }

    @Override
    public void angriff() {
        //TODO Zufallsgen.
       System.out.println("Gegner besiegt - Glückwunsch");   //TODO konsultation mit yannick(warum genau "angriff" und nicht genereller z.b. "aktion")
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
