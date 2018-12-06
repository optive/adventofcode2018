package day_06;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AreaCalculator {

    private int[][] map;
    private List<int[]> coordinates = new ArrayList<>();
    private int[][] nearestNeighbourMap;
    private int[][] regionMap;

    public AreaCalculator(final List<String> coordinates) {
        final List<Integer> xList = new ArrayList<>();
        final List<Integer> yList = new ArrayList<>();

        for (final String coordinate : coordinates) {
            final String[] coordString = coordinate.split(",");
            xList.add(Integer.parseInt(coordString[0].trim()));
            yList.add(Integer.parseInt(coordString[1].trim()));
        }

        map = new int[Collections.max(yList) + 1][Collections.max(xList) + 1];
        nearestNeighbourMap = new int[Collections.max(yList) + 1][Collections.max(xList) + 1];
        regionMap = new int[Collections.max(yList) + 1][Collections.max(xList) + 1];

        for (int i = 0; i < xList.size(); i++) {
            map[yList.get(i)][xList.get(i)] = i + 1;
            this.coordinates.add(new int[]{xList.get(i), yList.get(i)});
        }
    }

    public int findLargestArea() {
        populateNearestNeighbourMap();
        final Map<Integer, Integer> areas = new HashMap<>();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {

                int key = nearestNeighbourMap[y][x];

                if (areas.containsKey(key)) {
                    areas.put(key, areas.get(key) + 1);
                } else {
                    areas.put(key, 1);
                }
            }
        }

        final Set<Integer> infiniteAreas = new HashSet<>();
        int width = map[0].length;
        int height = map.length;
        for (int y = 0; y < height; y++) {
            infiniteAreas.add(nearestNeighbourMap[y][0]);
            infiniteAreas.add(nearestNeighbourMap[y][width-1]);
        }
        for (int x=0; x < width; x++) {
            infiniteAreas.add(nearestNeighbourMap[0][x]);
            infiniteAreas.add(nearestNeighbourMap[height-1][x]);
        }

        for (final int infiniteArea: infiniteAreas) {
            if (areas.containsKey(infiniteArea)) areas.remove(infiniteArea);
        }

        return areas.entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getValue).get();

    }

    private void populateNearestNeighbourMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                nearestNeighbourMap[y][x] = findNearestNeighbour(new int[]{x,y});
            }
        }
    }

    public int findNearestNeighbour(int[] coordinate) {
        final Map<Integer, Integer> distanceMap = new HashMap<>();
        for (int i = 0; i < coordinates.size(); i++) {
            final int[] location = coordinates.get(i);
            final int distX = Math.abs(location[0] - coordinate[0]);
            final int distY = Math.abs(location[1] - coordinate[1]);
            distanceMap.put(i + 1, distX + distY);
        }

        boolean equidistant = false;
        Map.Entry<Integer, Integer> min = null;
        for (Map.Entry<Integer, Integer> entry : distanceMap.entrySet()) {
            if (min != null && min.getValue() == entry.getValue()) {
                equidistant = true;
            }
            if (min == null || min.getValue() > entry.getValue()) {
                equidistant = false;
                min = entry;
            }
        }

        if (distanceMap.values().stream().reduce(0, Integer::sum) < 10000) {
            regionMap[coordinate[1]][coordinate[0]] = 1;
        }

        if (equidistant) return 0;
        else return min.getKey();
    }

    public int getRegionSize() {
        int size = 0;
        for (int i=0; i< regionMap.length; i++) {
            for(int j=0; j<regionMap[i].length; j++) {
                size += regionMap[i][j];
            }
        }
        return size;
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/06_input.txt"), "UTF-8");
        final AreaCalculator areaCalculator = new AreaCalculator(lines);
        System.out.println(areaCalculator.findLargestArea());
        System.out.println(areaCalculator.getRegionSize());
    }
}
