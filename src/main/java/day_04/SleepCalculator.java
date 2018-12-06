package day_04;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SleepCalculator {

    private Map<Integer, List<DutyRecord>> guardDutyMap = new HashMap<>();


    public void parseSleepRecords(final List<String> eventLog) {
        Collections.sort(eventLog);
        final List<DutyRecord> dutyRecords = createDutyRecords(eventLog);
        guardDutyMap = collectDutyRecordsByGuard(dutyRecords);
    }

    public int calculateBestGuardMinuteProduct() {

        // Calculate the combined sleep length for each guard
        final Map<Integer, Integer> guardSleepMap = guardDutyMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream().collect(Collectors.summingInt(DutyRecord::getSleepLength))
                ));

        // Find the sleepiest guard
        final int guardId = guardSleepMap.entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey).get();

        final SleepRecord sleepRecord = findSleepiestMinute(guardId);
        return (sleepRecord.getSleepiestMinute() * guardId);
    }

    public int calculateMostFrequentlyAsleepProduct() {
        final int product = guardDutyMap.keySet()
                .stream()
                .map(id -> findSleepiestMinute(id))
                .max(Comparator.comparingInt(SleepRecord::getCount))
                .map(sr -> sr.getSleepiestMinute() * sr.getGuardId())
                .get();

        return product;
    }


    public Map<Integer, List<DutyRecord>> collectDutyRecordsByGuard(final List<DutyRecord> dutyRecords) {
        final Map<Integer, List<DutyRecord>> guardMap = new HashMap<>();
        for (final DutyRecord dutyRecord : dutyRecords) {
            if (guardMap.containsKey(dutyRecord.getGuardId())) {
                guardMap.get(dutyRecord.getGuardId()).add(dutyRecord);
            } else {
                final List<DutyRecord> dutyList = new ArrayList<>();
                dutyList.add(dutyRecord);
                guardMap.put(dutyRecord.getGuardId(), dutyList);
            }
        }
        return guardMap;
    }

    public List<DutyRecord> createDutyRecords(final List<String> eventLog) {
        DutyRecord currentRecord = null;
        final List<DutyRecord> dutyRecords = new ArrayList<>();

        for (final String event : eventLog) {
            if (event.endsWith("shift")) {
                final DutyRecord dutyRecord = new DutyRecord();
                currentRecord = dutyRecord;
                dutyRecord.setGuardId(extractGuardNumber(event));
                dutyRecords.add(dutyRecord);
            } else if (event.endsWith("asleep")) {
                int[] sleep = currentRecord.getSleep();
                for (int i = parseMinute(event); i < sleep.length; i++) {
                    sleep[i] = 1;
                }
            } else if (event.endsWith("up")) {
                int[] sleep = currentRecord.getSleep();
                for (int i = parseMinute(event); i < sleep.length; i++) {
                    sleep[i] = 0;
                }
            }
        }
        return dutyRecords;
    }


    public int parseMinute(final String event) {
        return Integer.valueOf(event.split(" ")[1].substring(3,5));
    }

    public int extractGuardNumber(final String event) {
        final int startIndex = event.indexOf("#") + 1;
        final int endIndex = event.indexOf("begins") -1;
        return Integer.valueOf(event.substring(startIndex,endIndex));
    }

    public SleepRecord findSleepiestMinute(final int guardId) {
        // Get the sleep arrays for each shift the guard completed
        final List<int[]> sleepArrays = guardDutyMap.get(guardId).stream()
                .map(dutyRecord -> dutyRecord.getSleep()).collect(Collectors.toList());

        // Sum (element wise) the sleep arrays and find the max
        int max = 0;
        int index = 0;
        for (int i = 0; i < 60; i++) {
            int sum = 0;
            for (final int[] sleepArray : sleepArrays) {
                sum += sleepArray[i];
            }

            if (sum > max) {
                max = sum;
                index = i;
            }
        }

        final SleepRecord sleepRecord = new SleepRecord();
        sleepRecord.setGuardId(guardId);
        sleepRecord.setCount(max);
        sleepRecord.setSleepiestMinute(index);
        return sleepRecord;
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/04_input.txt"), "UTF-8");
        final SleepCalculator sleepCalculator = new SleepCalculator();
        sleepCalculator.parseSleepRecords(lines);
        System.out.println(sleepCalculator.calculateBestGuardMinuteProduct());
        System.out.println(sleepCalculator.calculateMostFrequentlyAsleepProduct());
    }
}
