package application;

import java.util.Random;

public class OceanMap {

    private final int dimension = 10;
    private boolean[][] grid;

    public OceanMap() {
        grid = new boolean[dimension][dimension];
        placeIslands(10); // Placing 10 islands randomly
    }

    // Generate and return the map
    public boolean[][] getMap() {
        return grid;
    }

    // Place islands randomly on the map
    public void placeIslands(int numIslands) {
        Random rand = new Random();
        int count = 0;

        while (count < numIslands) {
            int x = rand.nextInt(dimension);
            int y = rand.nextInt(dimension);

            if (!grid[x][y]) { // Ensure island is not already placed
                grid[x][y] = true; // Mark the grid as having an island
                count++;
            }
        }
    }

    // Check if the cell contains an island
    public boolean isIsland(int x, int y) {
        return grid[x][y];
    }
}

