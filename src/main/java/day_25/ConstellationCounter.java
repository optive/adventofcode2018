package day_25;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConstellationCounter {

    final List<int[]> stars = new ArrayList<>();

    public ConstellationCounter(final List<String> input) {
        for (final String line : input) {
            final String[] coordinates = line.split(",");
            stars.add(new int[]{
                    Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1]),
                    Integer.parseInt(coordinates[2]),
                    Integer.parseInt(coordinates[3])});
        }
    }

    public int getConstellationCount() {
        final List<Constellation> constellations = new ArrayList<>();
        for (final int[] star : stars) {
            final List<Constellation> matchingConstellations = new ArrayList<>();
            for (final Constellation constellation : constellations) {
                if (constellation.inludes(star)) {
                    matchingConstellations.add(constellation);
                }
            }

            if (matchingConstellations.isEmpty()) {
                // Create a new constellation
                final Constellation constellation = new Constellation();
                constellation.join(star);
                constellations.add(constellation);
            } else if (matchingConstellations.size() == 1) {
                // Join an existing constellation
                matchingConstellations.get(0).join(star);
            } else {
                // Collapse down existing constellations joined by the current star
                final Constellation parentConstellation = matchingConstellations.get(0);
                for (int i=1; i<matchingConstellations.size(); i++) {
                    parentConstellation.getStars().addAll(matchingConstellations.get(i).getStars());
                    parentConstellation.join(star);
                    constellations.remove(matchingConstellations.get(i));
                }
            }
        }

        return constellations.size();
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/25_input.txt"), "UTF-8");
        final ConstellationCounter constellationCounter = new ConstellationCounter(lines);
        System.out.println(constellationCounter.getConstellationCount());
    }
}
