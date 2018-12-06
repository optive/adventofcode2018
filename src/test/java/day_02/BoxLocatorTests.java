package day_02;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BoxLocatorTests {
    public BoxLocator boxLocator;

    @Test
    public void detectsTheCharPosition() {
        boxLocator = new BoxLocator(Arrays.asList("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz"));
        Assert.assertEquals("fgij", boxLocator.detectBoxId());
    }
}
