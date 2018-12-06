package day_05;

import org.junit.Assert;
import org.junit.Test;

public class PolymerReactorTests {

    public PolymerReactor polymerReactor;

    @Test
    public void reactsPolymersTestOne() {
        polymerReactor = new PolymerReactor("aA");
        polymerReactor.reactPolymer();
        Assert.assertEquals("", polymerReactor.getPolymerString());
    }

    @Test
    public void reactsPolymersTestTwo() {
        polymerReactor = new PolymerReactor("abBA");
        polymerReactor.reactPolymer();
        Assert.assertEquals("", polymerReactor.getPolymerString());
    }

    @Test
    public void reactsPolymersTestThree() {
        polymerReactor = new PolymerReactor("abAB");
        polymerReactor.reactPolymer();
        Assert.assertEquals("abAB", polymerReactor.getPolymerString());
    }

    @Test
    public void reactsPolymersTestFour() {
        polymerReactor = new PolymerReactor("aabAAB");
        polymerReactor.reactPolymer();
        Assert.assertEquals("aabAAB", polymerReactor.getPolymerString());
    }

    @Test
    public void reactsPolymersTestFive() {
        polymerReactor = new PolymerReactor("dabAcCaCBAcCcaDA");
        polymerReactor.reactPolymer();
        Assert.assertEquals("dabCBAcaDA", polymerReactor.getPolymerString());
    }

    @Test
    public void findsTheBestPossiblePolymer() {
        polymerReactor = new PolymerReactor("dabAcCaCBAcCcaDA");
        final int length = polymerReactor.findBestReaction();
        Assert.assertEquals(4, length);
    }

}
