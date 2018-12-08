package day_07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Step {

    private Character name;
    private List<Step> preceding = new ArrayList<>();
    private List<Step> succeding = new ArrayList<>();
    private int signal;
    private boolean done;

    public Step(final Character name) {
        this.name = name;
    }

    public Character getName() {
        return name;
    }

    public List<Step> getPreceding() {
        return preceding;
    }

    public List<Step> getSucceding() {
        succeding.sort(Comparator.comparing(Step::getName));
        return succeding;
    }

    public int getSignal() {
        return signal;
    }

    public void receiveSignal() {
        signal++;
    }

    public void sendSignal() {
        for (final Step step: succeding) {
            step.receiveSignal();
        }
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getTimeRequired() {
        return name - 64;
    }
}
