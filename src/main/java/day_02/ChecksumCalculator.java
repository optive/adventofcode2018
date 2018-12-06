 package day_02;

 import org.apache.commons.io.FileUtils;

 import java.io.File;
 import java.io.IOException;
 import java.util.*;
 import java.util.stream.Collectors;

public class ChecksumCalculator {

    private long duplicateCount;
    private long triplicateCount;

    public long calculateChecksum() {
        return duplicateCount * triplicateCount;
    }

    public void ingestIds(final List<String> boxIds) {
        for (final String id : boxIds) {
            final Map<Character,Integer> charMap = countChars(id);
            if (getDuplicates(charMap).size() >= 1) {
                duplicateCount++;
            }
            if (getTriplicates(charMap).size() >= 1) {
                triplicateCount++;
            }
        }
    }

    public Map<Character, Integer> countChars(final String id) {
        final Map<Character, Integer> charMap = new HashMap<>();
        for (Character c : id.toCharArray()) {
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c) + 1);
            } else {
                charMap.put(c, 1);
            }
        }
        return charMap;
    }

    public Set<Character> getDuplicates(final Map<Character, Integer> charMap) {
         return charMap.entrySet().stream()
                .filter(e -> e.getValue() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Set<Character> getTriplicates(final Map<Character, Integer> charMap) {
        return charMap.entrySet().stream()
                .filter(e -> e.getValue() == 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/02_input.txt"), "UTF-8");

        final ChecksumCalculator checksumCalculator = new ChecksumCalculator();
        checksumCalculator.ingestIds(lines);
        System.out.println(checksumCalculator.calculateChecksum());
    }
}
