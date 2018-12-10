package day_10;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarMessage {

    private List<Star> stars = new ArrayList<>();

    public StarMessage(final List<String> input) {
        final Pattern p = Pattern.compile("position=<(.*), (.*)> velocity=<(.*), (.*)>.*");
        for (final String line : input) {
            final Matcher m = p.matcher(line);
            m.find();
            final Star star = new Star();
            star.setX(Integer.parseInt(m.group(1).trim()));
            star.setY(Integer.parseInt(m.group(2).trim()));
            star.setxVelocity(Integer.parseInt(m.group(3).trim()));
            star.setyVeloctiy(Integer.parseInt(m.group(4).trim()));
            stars.add(star);
        }
    }

    public void watchStars() {
        long time = 0;
        long minBoundingBoxArea = printStars(0);
        long boundingBoxArea = 0;
        do {
            for (final Star star : stars) {
                star.setX(star.getX() + star.getxVelocity());
                star.setY(star.getY() + star.getyVeloctiy());
            }

            boundingBoxArea = printStars(++time);

            if (boundingBoxArea < minBoundingBoxArea) {
                minBoundingBoxArea = boundingBoxArea;
            }

        } while (boundingBoxArea == minBoundingBoxArea);

    }

    public long printStars(final long time) { // This is a horrible function, needs refactoring.
        final int xMax = stars.stream().map(Star::getX).max(Comparator.comparingInt(x -> x)).get();
        final int xMin = stars.stream().map(Star::getX).min(Comparator.comparingInt(x -> x)).get();
        final int yMax = stars.stream().map(Star::getY).max(Comparator.comparingInt(y -> y)).get();
        final int yMin = stars.stream().map(Star::getY).min(Comparator.comparingInt(y -> y)).get();

        final int width = xMax - xMin;
        final int height = yMax - yMin;

        final long boundingBoxArea = (long) width * (long) height;

        if (boundingBoxArea < 10000) {

            final boolean[][] positions = new boolean[height + 1][width + 1];

            for (final Star star : stars) {
                int x = star.getX() - xMin;
                int y = star.getY() - yMin;
                positions[y][x] = true;
            }

            for (int j = 0; j <= height; j++) {
                for (int i = 0; i <= width; i++) {
                    if (positions[j][i]) {
                        System.out.print("#");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }

            System.out.println(time);
        }
        return boundingBoxArea;
    }

    public static void main(String args[]) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/10_input.txt"), "UTF-8");
        final StarMessage starMessage = new StarMessage(lines);
        starMessage.watchStars();
    }
}
