package day_04;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SleepCalculatorTests {

    final SleepCalculator sleepCalculator = new SleepCalculator();

    @Test
    public void parsesMinutesCorrectly() {
        Assert.assertEquals(24, sleepCalculator.parseMinute("[1518-11-03 00:24] falls asleep"));
    }

    @Test
    public void extractsGuardNumberCorrectly() {
        final String event = "[1518-11-01 23:58] Guard #99 begins shift";
        Assert.assertEquals(99, sleepCalculator.extractGuardNumber(event));
    }

    @Test
    public void completesPartOneCorrectly() throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/test/resources/04_input.txt"), "UTF-8");
        sleepCalculator.parseSleepRecords(lines);
        Assert.assertEquals(240, sleepCalculator.calculateBestGuardMinuteProduct());
    }

    @Test
    public void completesPartTwoCorrectly() throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/test/resources/04_input.txt"), "UTF-8");
        sleepCalculator.parseSleepRecords(lines);
        Assert.assertEquals(4455, sleepCalculator.calculateMostFrequentlyAsleepProduct());
    }
}
