package RaumSystem.Event;

public class Gegner implements Event{
    private final String name = "Zombie";

    @Override
    public void function() {
        System.out.println("Ich bin ein Zombie");
        System.out.println("Ich mach die platt, wenn du es nicht tust!");
    }

    @Override
    public void angriff() {
        //Hier ANgriff
    }


    @Override
    public String getName() {
        return this.name;
    }
}
