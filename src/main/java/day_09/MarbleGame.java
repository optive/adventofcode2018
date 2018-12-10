package day_09;

import java.io.IOException;
import java.util.*;

public class MarbleGame {

    private final Map<Integer, Long> playerScores = new HashMap<>();
    private final int numPlayers;
    private final int numMarbles;
    private final List<Integer> circle = new ArrayList<>();

    public MarbleGame(final int players, final int numMarbles) {
        for (int i = 0; i < players; i++) {
            playerScores.put(i, 0L);
        }

        this.numPlayers = players;
        this.numMarbles = numMarbles;
    }

    public long getHighestScore() {
        int currentIndex = 0;
        circle.add(0);

        for (int i=1; i<=numMarbles; i++) {
            int currentPlayer = i % numPlayers;

            if (i % 23 == 0) {
                long currentScore = playerScores.get(currentPlayer);
                currentIndex = getMarbleIndex(currentIndex, - 7);
                playerScores.put(currentPlayer, currentScore + i + circle.remove(currentIndex));
            } else {
                currentIndex = getMarbleIndex(currentIndex, 2);
                circle.add(currentIndex, i);
            }

//            System.out.print(currentPlayer + ":  ");
//            circle.forEach(m -> System.out.print(m + " "));
            System.out.println(i);
        }

        return playerScores.values()
                .stream()
                .max(Comparator.comparingLong(i -> i))
                .get();
    }

    private int getMarbleIndex(final int currentIndex, final int offset) {
        if (currentIndex + offset > circle.size()) {
            return currentIndex + offset - circle.size();
        } else if (currentIndex + offset < 0) {
            return currentIndex + offset + circle.size();
        } else {
            return currentIndex + offset;
        }
    }

    public static void main(String[] args) throws IOException {
        final MarbleGame marbleGamePartOne = new MarbleGame(471, 72026);
        System.out.println(marbleGamePartOne.getHighestScore());
        final MarbleGame marbleGamePartTwo = new MarbleGame(471, 7202600);
        System.out.println(marbleGamePartTwo.getHighestScore());
    }
}
