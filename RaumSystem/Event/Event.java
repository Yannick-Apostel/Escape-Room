package RaumSystem.Event;

public interface Event {
    String name = "";
    boolean forcedEvent = false;

    //TODO: Funktion mit Usecase hinzufügen
    public void description();

    public void aktion();

    public String getName();

    public boolean isForced();
}
