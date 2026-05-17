package RaumSystem.Event;

public class verletztePerson implements Event{
    private final String name = "person";
    private final boolean forcedEvent = false;

    @Override
    public void function() {
        System.out.println("Du siehst eine Person in der Ecke des Raumes.");
        System.out.println("Sie scheint verletzt zu sein.");
    }

    @Override
    public void angriff() {

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
