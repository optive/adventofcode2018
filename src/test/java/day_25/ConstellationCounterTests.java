package day_25;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ConstellationCounterTests {

    private ConstellationCounter constellationCounter;

    @Test
    public void countsConstellationsOne() {
        constellationCounter = new ConstellationCounter(Arrays.asList(
                "0,0,0,0",
                "3,0,0,0",
                "0,3,0,0",
                "0,0,3,0",
                "0,0,0,3",
                "0,0,0,6",
                "9,0,0,0",
                "12,0,0,0"));
        Assert.assertEquals(2, constellationCounter.getConstellationCount());
    }

    @Test
    public void countsConstellationsTwo() {
        constellationCounter = new ConstellationCounter(Arrays.asList(
                "-1,2,2,0",
                "0,0,2,-2",
                "0,0,0,-2",
                "-1,2,0,0",
                "-2,-2,-2,2",
                "3,0,2,-1",
                "-1,3,2,2",
                "-1,0,-1,0",
                "0,2,1,-2",
                "3,0,0,0"));
        Assert.assertEquals(4, constellationCounter.getConstellationCount());
    }

    @Test
    public void countsConstellationsThree() {
        constellationCounter = new ConstellationCounter(Arrays.asList(
                "1,-1,0,1",
                "2,0,-1,0",
                "3,2,-1,0",
                "0,0,3,1",
                "0,0,-1,-1",
                "2,3,-2,0",
                "-2,2,0,0",
                "2,-2,0,-1",
                "1,-1,0,-1",
                "3,2,0,2"));
        Assert.assertEquals(3, constellationCounter.getConstellationCount());
    }

    @Test
    public void countsConstellationsFour() {
        constellationCounter = new ConstellationCounter(Arrays.asList(
                "1,-1,-1,-2",
                "-2,-2,0,1",
                "0,2,1,3",
                "-2,3,-2,1",
                "0,2,3,-2",
                "-1,-1,1,-2",
                "0,-2,-1,0",
                "-2,2,3,-1",
                "1,2,2,0",
                "-1,-2,0,-2"));
        Assert.assertEquals(8, constellationCounter.getConstellationCount());
    }

}
