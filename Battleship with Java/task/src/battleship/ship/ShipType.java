package battleship.ship;

public enum ShipType {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    // INSTANCE VARIABLES
    private final String name;
    private final int length;

    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    // CONSTRUCTOR
    ShipType(String name, int length) {
        this.name = name;
        this.length = length;
    }
}
