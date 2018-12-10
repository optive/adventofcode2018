package day_09;

import org.junit.Assert;
import org.junit.Test;

public class MarbleGameTests {
    private MarbleGame marbleGame;

    @Test
    public void testGameOne() {
        marbleGame = new MarbleGame(10, 1618);
        Assert.assertEquals(8317, marbleGame.getHighestScore());
    }

    @Test
    public void testGameTwo() {
        marbleGame = new MarbleGame(13, 7999);
        Assert.assertEquals(146373, marbleGame.getHighestScore());
    }

    @Test
    public void testGameThree() {
        marbleGame = new MarbleGame(17, 1104);
        Assert.assertEquals(2764, marbleGame.getHighestScore());
    }

    @Test
    public void testGameFour() {
        marbleGame = new MarbleGame(21, 6111);
        Assert.assertEquals(54718, marbleGame.getHighestScore());
    }

    @Test
    public void testGameFive() {
        marbleGame = new MarbleGame(30, 5807);
        Assert.assertEquals(37305, marbleGame.getHighestScore());
    }

    @Test
    public void testGameSix() {
        marbleGame = new MarbleGame(9, 25);
        Assert.assertEquals(32, marbleGame.getHighestScore());
    }
}
