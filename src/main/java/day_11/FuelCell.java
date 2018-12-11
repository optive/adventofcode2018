package day_11;

import java.util.Stack;

public class FuelCell {

    private static final int RACK_ID_OFFSET = 10;
    private static final int POWER_LEVEL_OFFSET = -5;

    private int x;
    private int y;
    private int serialNumber;


    public FuelCell(final int x, final int y, final int serialNumber) {
        this.x = x;
        this.y = y;
        this.serialNumber = serialNumber;
    }

    public int calculatePowerLevel() {
        final int rackId = x + RACK_ID_OFFSET;
        int powerLevel = rackId * y;
        powerLevel += serialNumber;
        powerLevel = powerLevel * rackId;
        return getHudredsDigit(powerLevel) + POWER_LEVEL_OFFSET;
    }

    private int getHudredsDigit(int num) {
        final Stack<Integer> stack = new Stack<>();
        while (stack.size() <3 ) {
            stack.push(num % 10);
            num = num / 10;
        }
        return stack.pop();
    }
}
