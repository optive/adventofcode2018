package day_02;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ChecksumCalculatorTests {
    public ChecksumCalculator checksumCalculator = new ChecksumCalculator();

    @Test
    public void calculatesDuplicates() {
        final Map<Character, Integer> charMap = checksumCalculator.countChars("bababc");
        final Set<Character> expected = new HashSet<>();
        expected.add('a');
        Assert.assertEquals(expected, checksumCalculator.getDuplicates(charMap));
    }

    @Test
    public void calculatesTriplicates() {
        final Map<Character, Integer> charMap = checksumCalculator.countChars("bababc");
        final Set<Character> expected = new HashSet<>();
        expected.add('b');
        Assert.assertEquals(expected, checksumCalculator.getTriplicates(charMap));
    }

    @Test
    public void calculatesChecksum() {
        checksumCalculator.ingestIds(Arrays.asList("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"));
        Assert.assertEquals(12, checksumCalculator.calculateChecksum());
    }

}
