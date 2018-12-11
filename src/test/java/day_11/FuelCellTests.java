package day_11;

import org.junit.Assert;
import org.junit.Test;

public class FuelCellTests {

    private FuelCell fuelCell;

    @Test
    public void calculatesPowerLevelExampleOne() {
        fuelCell = new FuelCell(3,5,8);
        Assert.assertEquals(4, fuelCell.calculatePowerLevel());
    }

    @Test
    public void calculatesPowerLevelExampleTwo() {
        fuelCell = new FuelCell(122,79,57);
        Assert.assertEquals(-5, fuelCell.calculatePowerLevel());
    }

    @Test
    public void calculatesPowerLevelExampleThree() {
        fuelCell = new FuelCell(217,196,39);
        Assert.assertEquals(0, fuelCell.calculatePowerLevel());
    }

    @Test
    public void calculatesPowerLevelExampleFour() {
        fuelCell = new FuelCell(101,153,71);
        Assert.assertEquals(4, fuelCell.calculatePowerLevel());
    }
}
