package day_23;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmergencyTeleporter {

    private List<Nanobot> nanobots = new ArrayList<>();

    public EmergencyTeleporter(final List<String> input) {
        for (final String line : input) {
            final String[] coordinates = line.substring(5, line.indexOf('>')).split(",");
            final long x = Long.parseLong(coordinates[0]);
            final long y = Long.parseLong(coordinates[1]);
            final long z = Long.parseLong(coordinates[2]);
            final long radius = Long.parseLong(line.substring(line.lastIndexOf('=') + 1));
            nanobots.add(new Nanobot(x, y, z, radius));
        }
    }

    public long getNanobotCountInRange() {
        long count = 0;
        final Nanobot strongest = nanobots.stream().max(Comparator.comparingLong(Nanobot::getRadius)).get();
        for (final Nanobot nanobot: nanobots) {
            final long distance = Math.abs(nanobot.getX() - strongest.getX()) + Math.abs(nanobot.getY() - strongest.getY()) + Math.abs(nanobot.getZ() - strongest.getZ());
            if (distance <= strongest.getRadius()) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/23_input.txt"), "UTF-8");
        final EmergencyTeleporter emergencyTeleporter = new EmergencyTeleporter(lines);
        System.out.println(emergencyTeleporter.getNanobotCountInRange());
    }
}
