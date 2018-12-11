package day_11;

import org.junit.Assert;
import org.junit.Test;

public class FuelGridTests {

    private FuelGrid fuelGrid;

    @Test
    public void identifiesLargestPowerSquareOne() {
        fuelGrid = new FuelGrid(18);
        final PowerCell powerCell = fuelGrid.identifyLargestPowerCell(3);
        Assert.assertEquals(33, powerCell.getX());
        Assert.assertEquals(45, powerCell.getY());
    }

    @Test
    public void identifiesLargestPowerSquareTwo() {
        fuelGrid = new FuelGrid(42);
        final PowerCell powerCell = fuelGrid.identifyLargestPowerCell(3);
        Assert.assertEquals(21, powerCell.getX());
        Assert.assertEquals(61, powerCell.getY());
    }

    @Test
    public void identifiesTheLargestPossiblePowerSquareOne() {
        fuelGrid = new FuelGrid(18);
        final PowerCell powerCell = fuelGrid.identifyLargestPowerCell();
        Assert.assertEquals(113, powerCell.getPower());
        Assert.assertEquals(90, powerCell.getX());
        Assert.assertEquals(269, powerCell.getY());
        Assert.assertEquals(16, powerCell.getSize());
    }

    @Test
    public void identifiesTheLargestPossiblePowerSquareTwo() {
        fuelGrid = new FuelGrid(42);
        final PowerCell powerCell = fuelGrid.identifyLargestPowerCell();
        Assert.assertEquals(119, powerCell.getPower());
        Assert.assertEquals(232, powerCell.getX());
        Assert.assertEquals(251, powerCell.getY());
        Assert.assertEquals(12, powerCell.getSize());
    }
}
