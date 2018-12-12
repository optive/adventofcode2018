package day_12;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class PlantNurseryTests {

    private PlantNursery plantNursery;

    @Test
    public void matchesConditionsCorrectly() throws IOException {
        plantNursery = new PlantNursery(FileUtils.readLines(new File("src/test/resources/12_input.txt"), "UTF-8"));
        Assert.assertTrue(plantNursery.matchesCondition(".#.##".toCharArray()));
    }

    @Test
    public void sumsPotNumbersCorrectly() throws IOException {
        plantNursery = new PlantNursery(FileUtils.readLines(new File("src/test/resources/12_input.txt"), "UTF-8"));
        Assert.assertEquals(145, plantNursery.sumPotNumbers());
    }

    @Test
    public void generatesPlantsCorrectly() throws IOException {
        plantNursery = new PlantNursery(FileUtils.readLines(new File("src/test/resources/12_input.txt"), "UTF-8"));
        Assert.assertEquals(325, plantNursery.generatePlants(20));
    }
}
