package day_25;

import java.util.ArrayList;
import java.util.List;

public class Constellation {

    private final List<int[]> stars = new ArrayList();

    public List<int[]> getStars() {
        return stars;
    }

    public boolean inludes(final int[] newStar) {

        boolean isWithinConstellation = false;
        for (final int[] star: stars) {
            if (getManhattanDistance(star, newStar) <= 3) {
                isWithinConstellation = true;
            }
        }
        return isWithinConstellation;
    }

    public void join(final int[] star) {
        stars.add(star);
    }

    private int getManhattanDistance(final int[] starOne, final int[] starTwo) {
        final int distance = Math.abs(starOne[0] - starTwo[0]) +
                Math.abs(starOne[1] - starTwo[1]) +
                Math.abs(starOne[2] - starTwo[2]) +
                Math.abs(starOne[3] - starTwo[3]);
        return distance;
    }
}
