package day_07;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SleighInstructions {

    private Map<Character, Step> steps = new HashMap<>();

    public SleighInstructions(final List<String> requirements) {
        for (final String requirement : requirements) {
            final String[] reqArray = requirement.split(" ");
            final Character stepName = reqArray[1].charAt(0);
            final Character succedingStepName = reqArray[7].charAt(0);
            final Step step = steps.getOrDefault(stepName, new Step(stepName));
            final Step succeding = steps.getOrDefault(succedingStepName, new Step(succedingStepName));
            step.getSucceding().add(succeding);
            succeding.getPreceding().add(step);
            steps.putIfAbsent(stepName, step);
            steps.putIfAbsent(succedingStepName, succeding);
        }
    }

    public String getOrderedSteps() {
        StringBuilder sb = new StringBuilder();

        while (stepsRemaining()) {
            final Step step = getNextAvailableStep().get();
            sb.append(step.getName());
            completeStep(step);
        }

        return sb.toString();
    }

    public int getTotalTimeTaken(final int workers, final int baseTime) {

        final Map<Step, Integer> workQueue = new HashMap<>();
        int availableWorkers = workers;
        int time = 0;

        while (true) {

            // Decrement the time remaining for all currently in progress steps
            workQueue.replaceAll((k, v) -> v - 1);

            // Remove any tasks that are done
            final List<Step> toRemove = new ArrayList<>();
            for (final Map.Entry<Step, Integer> entry : workQueue.entrySet()) {
                if (entry.getValue() == 0) {
                    entry.getKey().sendSignal();
                    toRemove.add(entry.getKey());
                    availableWorkers++;
                }
            }
            for (final Step step: toRemove) {
                workQueue.remove(step);
            }

            while (availableWorkers > 0) {

                if (getNextAvailableStep().isPresent()) {
                    final Step step = getNextAvailableStep().get();
                    workQueue.put(step, baseTime + step.getTimeRequired());
                    step.setDone(true);
                    availableWorkers--;
                } else {
                    break;
                }
            }

            if (workQueue.entrySet().size() == 0) {
                break;
            }

            time++;
        }
        return time;
    }

    private void completeStep(final Step step) {
        step.setDone(true);
        step.sendSignal();
    }

    public boolean stepsRemaining() {
        return steps.values()
                .stream()
                .filter(step -> !step.isDone())
                .collect(Collectors.toList()).size() > 0;
    }

    public Optional<Step> getNextAvailableStep() {
        return steps.values()
                .stream()
                .filter(step -> step.getPreceding().size() == step.getSignal())
                .filter(step -> !step.isDone())
                .min(Comparator.comparing(Step::getName));
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/07_input.txt"), "UTF-8");
        SleighInstructions sleighInstructions = new SleighInstructions(lines);
        System.out.println(sleighInstructions.getOrderedSteps());
        sleighInstructions = new SleighInstructions(lines); // Reset all the variables
        System.out.println(sleighInstructions.getTotalTimeTaken(5, 60));
    }
}
