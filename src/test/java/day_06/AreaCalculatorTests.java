package day_06;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AreaCalculatorTests {

    private AreaCalculator areaCalculator;

    @Before
    public void setup() throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/test/resources/06_input.txt"), "UTF-8");
        areaCalculator = new AreaCalculator(lines);
    }

    @Test
    public void calculatesNearestNeighbourCorrectly() {
        Assert.assertEquals(4, areaCalculator.findNearestNeighbour(new int[]{3,2}));
    }

    @Test
    public void calculatesNearestNeighbourCorrectlyTwo() {
        Assert.assertEquals(1, areaCalculator.findNearestNeighbour(new int[]{0,0}));
    }

    @Test
    public void calculatesNearestNeighbourCorrectlyThree() {
        Assert.assertEquals(5, areaCalculator.findNearestNeighbour(new int[]{4,6}));
    }

    @Test
    public void calculatesNearestNeighbourEquidistant() {
        Assert.assertEquals(0, areaCalculator.findNearestNeighbour(new int[]{1,4}));
    }

    @Test
    public void calculatesLargestAreaCorrectly() {
        Assert.assertEquals(17, areaCalculator.findLargestArea());
    }




}
