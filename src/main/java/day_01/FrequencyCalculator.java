package day_01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyCalculator {

    private long currentFrequency;
    private Map<Long, Integer> frequencyMap = new HashMap<>();
    private boolean duplicateFrequencies;

    public FrequencyCalculator() {
        frequencyMap.put(currentFrequency, 1);
    }

    public long calculateNewFrequency(final long frequencyChange) {
        final long newFrequency = currentFrequency += frequencyChange;

        if (frequencyMap.containsKey(newFrequency)) {
            duplicateFrequencies = true;
            frequencyMap.put(newFrequency, frequencyMap.get(newFrequency) + 1);
        } else {
            frequencyMap.put(newFrequency, 1);
        }

        return newFrequency;
    }

    public long getCurrentFrequency() {
        return currentFrequency;
    }

    public boolean duplicateFrequencies() {
        return duplicateFrequencies;
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/01_input.txt"), "UTF-8");

        final FrequencyCalculator fq = new FrequencyCalculator();

        while(!fq.duplicateFrequencies()) {
            for (final String line : lines) {
                fq.calculateNewFrequency(Long.valueOf(line));

                if (fq.duplicateFrequencies()) {
                    break;
                }
            }
        }

        System.out.println(fq.getCurrentFrequency());

    }
}
