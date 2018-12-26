package day_17;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GroundScannerTests {

    private GroundScanner groundScanner;

    @Test
    public void calculatesWaterSaturation() throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/test/resources/17_input.txt"), "UTF-8");
        groundScanner = new GroundScanner(lines);
        Assert.assertEquals(57, groundScanner.getWaterExtent());
    }

    @Test
    public void calculatesRemainingWater() throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/test/resources/17_input.txt"), "UTF-8");
        groundScanner = new GroundScanner(lines);
        Assert.assertEquals(29, groundScanner.getRemainingWater());
    }
}
