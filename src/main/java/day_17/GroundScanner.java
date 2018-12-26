package day_17;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GroundScanner {

    final private char[][] groundMap;
    final private int yMin;

    public GroundScanner(final List<String> input) {
        final List<int[]> clayVeins = new ArrayList<>();
        for (final String line : input) {
            final String[] coordinates = line.split(", ");
            final int i = Integer.parseInt(coordinates[0].split("=")[1]);
            final String[] jRange = coordinates[1].split("=")[1].split("\\.\\.");
            final int jMin = Integer.parseInt(jRange[0]);
            final int jMax = Integer.parseInt(jRange[1]);
            for (int k = jMin; k <= jMax; k++) {
                if (coordinates[0].startsWith("x")) {
                    clayVeins.add(new int[]{i, k});
                } else {
                    clayVeins.add(new int[]{k, i});
                }
            }
        }
        final int xMax = clayVeins.stream().max(Comparator.comparingInt(c -> c[0])).map(c -> c[0]).get();
        final int yMax = clayVeins.stream().max(Comparator.comparingInt(c -> c[1])).map(c -> c[1]).get();
        yMin = clayVeins.stream().min(Comparator.comparingInt(c -> c[1])).map(c -> c[1]).get();
        groundMap = new char[yMax + 1][xMax + 1];
        for (char[] slice : groundMap) {
            Arrays.fill(slice, '.');
        }
        groundMap[0][500] = '+';

        for (final int[] clay : clayVeins) {
            groundMap[clay[1]][clay[0]] = '#';
        }

        fillDown(500, 1);
    }

    public int getWaterExtent() {
        printGround();
        int waterExtent = 0;
        for (int j = yMin; j < groundMap.length; j++) {
            for (int i = 0; i < groundMap[j].length; i++) {
                if (groundMap[j][i] == '~' || groundMap[j][i] == '|') {
                    waterExtent++;
                }
            }
        }
        return waterExtent;
    }

    public int getRemainingWater() {
        int remainingWater = 0;
        for (int j = yMin; j < groundMap.length; j++) {
            for (int i = 0; i < groundMap[j].length; i++) {
                if (groundMap[j][i] == '~') {
                    remainingWater++;
                }
            }
        }
        return remainingWater;
    }

    private void fillDown(final int x, final int y) {
        int i = x;
        int j = y;

        while (groundMap[j + 1][i] != '#') {
            groundMap[j][i] = '|';
            j++;

            if (j + 1 == groundMap.length) {
                groundMap[j][i] = '|';
                return;
            }
        }
        groundMap[j][i] = '~';

        List<Integer> overflow = new ArrayList<>();
        while (overflow.size() == 0) {
            overflow = fillHorizontally(i, j--);
        }

        for (final int k : overflow) {
            fillDown(k, j + 1);
        }
    }


    private List<Integer> fillHorizontally(final int x, final int y) {
        final List<Integer> overflow = new ArrayList<>();
        // Fill right
        int i = 1;
        while (groundMap[y].length > (x + i) && groundMap[y][x + i] != '#') {
            groundMap[y][x + i] = '~';
            if (groundMap[y + 1][x + i] != '#' && groundMap[y + 1][x + i] != '~') {
                overflow.add(x + i);
                break;
            }
            i++;
        }

        // Fill left
        int k = 0;
        while ((x - k) >= 0 && groundMap[y][x - k] != '#') {
            groundMap[y][x - k] = '~';
            if (groundMap[y + 1][x - k] != '#' && groundMap[y + 1][x - k] != '~') {
                overflow.add(x - k);
                break;
            }
            k++;
        }

        if (overflow.size() > 0) {
            for (int w = -k + 1; w < i ; w++) {
                groundMap[y][x + w] = '|';
            }
        }
        return overflow;
    }

    public void printGround() {
        for (int j = 0; j < groundMap.length; j++) {
            for (int i = 0; i < groundMap[j].length; i++) {
                System.out.print(groundMap[j][i]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws Exception {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/17_input.txt"), "UTF-8");
        final GroundScanner groundScanner = new GroundScanner(lines);
        System.out.println(groundScanner.getWaterExtent());
        System.out.println(groundScanner.getRemainingWater());
    }
}

