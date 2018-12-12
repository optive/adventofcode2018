package day_12;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlantNursery {

    final char[] plantPots;
    final List<String> conditions = new ArrayList<>();

    public PlantNursery(final List<String> input) {
        final char[] initialState = input.get(0).substring(15).toCharArray();
        plantPots = new char[initialState.length + 500];
        Arrays.fill(plantPots, '.');
        for (int i=0; i< initialState.length; i++) {
            plantPots[i + 50] = initialState[i];
        }

        for (final String line : input) {
            if (line.endsWith("=> #")) {
                conditions.add(line.substring(0,5));
            }
        }
    }

    public long generatePlants(final long generations) {
        char[] oldGeneration = Arrays.copyOf(plantPots, plantPots.length);
        char[] newGeneration = new char[plantPots.length];
        final List<Integer> potCounts = new ArrayList<>();

        for (int i=0; i< generations; i++) {
            Arrays.fill(newGeneration, '.');
            for (int j=2; j<newGeneration.length -2; j++) {
                if (matchesCondition(new char[]{
                        oldGeneration[j-2],
                        oldGeneration[j-1],
                        oldGeneration[j],
                        oldGeneration[j+1],
                        oldGeneration[j+2]})) {
                    newGeneration[j] = '#';
                }
            }

            potCounts.add(sumPotNumbers(newGeneration));

            // If the pattern converges, break the loop and calculate the final pot count.
            if (convergenceDetected(potCounts)) {
                final int countDiff = potCounts.get(i) - potCounts.get(i-1);
                return sumPotNumbers(oldGeneration) + countDiff * (generations - i);
            }

            oldGeneration = Arrays.copyOf(newGeneration, newGeneration.length);

        }
        return sumPotNumbers(newGeneration);
    }

    public boolean convergenceDetected(final List<Integer> potCounts) {
        if (potCounts.size()<=20) {
            return false;
        }

        final Set<Integer> countDiffs = IntStream.range(potCounts.size()-20, potCounts.size())
                .mapToObj(i -> potCounts.get(i) - potCounts.get(i-1))
                .collect(Collectors.toSet());
        return countDiffs.size() == 1;
    }

    public boolean matchesCondition(char[] pots) {
        boolean matches = false;
        for (final String condition: conditions) {
            if (Arrays.equals(pots, condition.toCharArray())) {
                matches = true;
                break;
            }
        }
        return matches;
    }

    public int sumPotNumbers(char[] pots) {
        int sum = 0;
        for (int i=0; i<pots.length; i++) {
            if (pots[i] == '#') {
                sum+= i-50;
            }
        }
        return sum;
    }

    public int sumPotNumbers() {
        return sumPotNumbers(plantPots);
    }


    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/12_input.txt"), "UTF-8");
        final PlantNursery plantNursery = new PlantNursery(lines);
        System.out.println(plantNursery.generatePlants(20));
        final PlantNursery plantNurseryPartTwo = new PlantNursery(lines);
        System.out.println(plantNurseryPartTwo.generatePlants(50000000000L));
    }
}
