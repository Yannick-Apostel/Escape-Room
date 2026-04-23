
public class Room {

    private String name;
    private String description;

    private Room north;
    private Room south;
    private Room east;
    private Room west;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setExits(Room north, Room south, Room east, Room west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public void setExits(String direction, Room room) {
        if (direction.toLowerCase().equals("n")) {
            this.north = room;
        } else if (direction.toLowerCase().equals("e")) {
            this.east = room;
        } else if (direction.toLowerCase().equals("s")) {
            this.south = room;
        } else if (direction.toLowerCase().equals("w")) {
            this.west = room;
        }
    }

    public Room getExit(String direction) {
        if (direction.equals("n")) return north;
        if (direction.equals("s")) return south;
        if (direction.equals("o")) return east;
        if (direction.equals("w")) return west;
        return null;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
