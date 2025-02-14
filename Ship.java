package application;

public class Ship {

    private int x, y;  // Coordinates of the ship

    public Ship(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Get current ship location
    public int[] getShipLocation() {
        return new int[] { x, y };  // Return ship location as an array of integers
    }

    // Move the ship East if possible
    public void goEast(OceanMap oceanMap) {
        if (x + 1 < oceanMap.getMap().length && !oceanMap.isIsland(x + 1, y)) {
            x++;
        }
    }

    // Move the ship West if possible
    public void goWest(OceanMap oceanMap) {
        if (x - 1 >= 0 && !oceanMap.isIsland(x - 1, y)) {
            x--;
        }
    }

    // Move the ship North if possible
    public void goNorth(OceanMap oceanMap) {
        if (y - 1 >= 0 && !oceanMap.isIsland(x, y - 1)) {
            y--;
        }
    }

    // Move the ship South if possible
    public void goSouth(OceanMap oceanMap) {
        if (y + 1 < oceanMap.getMap()[0].length && !oceanMap.isIsland(x, y + 1)) {
            y++;
        }
    }
}
