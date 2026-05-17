package RaumSystem.Event;

public interface Event {
    String name = "";
    boolean forcedEvent = false;

    //TODO: Funktion mit Usecase hinzufügen
    public void function();

    public void angriff();

    public String getName();

    public boolean isForced();
}
