package day_04;

import java.util.stream.IntStream;

public class DutyRecord {
    private int guardId;
    private int[] sleep = new int[60];

    public int getGuardId() {
        return guardId;
    }

    public void setGuardId(int guardId) {
        this.guardId = guardId;
    }

    public int[] getSleep() {
        return sleep;
    }

    public int getSleepLength() {
        return IntStream.of(sleep).sum();
    }

}
