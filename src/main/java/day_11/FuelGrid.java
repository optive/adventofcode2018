package day_11;

import java.util.*;

public class FuelGrid {

    final int[][] fuelGrid = new int[300][300];

    public FuelGrid(final int serialNumber) {
        for (int x = 0; x < 300; x++) {
            for (int y = 0; y < 300; y++) {
                final FuelCell fuelCell = new FuelCell(x, y, serialNumber);
                fuelGrid[x][y] = fuelCell.calculatePowerLevel();
            }
        }
    }

    public PowerCell identifyLargestPowerCell(final int size) {
        final List<PowerCell> powerCells = new ArrayList<>();

        for (int x = 0; x < 300 - size; x++) {
            for (int y = 0; y < 300 - size; y++) {
                final PowerCell powerCell = new PowerCell();
                powerCell.setX(x);
                powerCell.setY(y);
                powerCell.setPower(sumGrid(x,y,size));
                powerCell.setSize(size);
                powerCells.add(powerCell);
            }
        }
        return Collections.max(powerCells, Comparator.comparingInt(PowerCell::getPower));
    }

    public PowerCell identifyLargestPowerCell() {
        final List<PowerCell> largestPowerCells = new ArrayList<>();
        for (int i=0; i< 300; i++) {
            largestPowerCells.add(identifyLargestPowerCell(i));
        }
        return Collections.max(largestPowerCells, Comparator.comparingInt(PowerCell::getPower));
    }

    private int sumGrid(final int x, final int y, final int size) {
        int sum = 0;
        for (int i=x; i<x+size; i++) {
            for (int j=y; j<y+size; j++) {
                sum += fuelGrid[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        final FuelGrid fuelGrid = new FuelGrid(1308);
        final PowerCell powerCell = fuelGrid.identifyLargestPowerCell(3);
        System.out.println(powerCell.getX() + "," + powerCell.getY());
        final PowerCell partTwoPowerCell = fuelGrid.identifyLargestPowerCell();
        System.out.println(partTwoPowerCell.getX() + "," +
                partTwoPowerCell.getY() + "," +
                partTwoPowerCell.getSize());
    }
}
