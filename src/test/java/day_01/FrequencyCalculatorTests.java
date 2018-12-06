package day_01;

import org.junit.Assert;
import org.junit.Test;

public class FrequencyCalculatorTests {

    public FrequencyCalculator frequencyCalculator = new FrequencyCalculator();

    @Test
    public void testCaseOne() {
        frequencyCalculator.calculateNewFrequency(1L);
        frequencyCalculator.calculateNewFrequency(1L);
        frequencyCalculator.calculateNewFrequency(1L);
        Assert.assertEquals(3, frequencyCalculator.getCurrentFrequency());
    }

    @Test
    public void testCaseTwo() {
        frequencyCalculator.calculateNewFrequency(1L);
        frequencyCalculator.calculateNewFrequency(1L);
        frequencyCalculator.calculateNewFrequency(-2L);
        Assert.assertEquals(0, frequencyCalculator.getCurrentFrequency());
    }

    @Test
    public void testCaseThree() {
        frequencyCalculator.calculateNewFrequency(-1L);
        frequencyCalculator.calculateNewFrequency(-2L);
        frequencyCalculator.calculateNewFrequency(-3L);
        Assert.assertEquals(-6, frequencyCalculator.getCurrentFrequency());
    }

    @Test
    public void testCaseFour() {
        frequencyCalculator.calculateNewFrequency(1L);
        frequencyCalculator.calculateNewFrequency(-1L);
        Assert.assertTrue(frequencyCalculator.duplicateFrequencies());
        Assert.assertEquals(-0, frequencyCalculator.getCurrentFrequency());
    }
}
