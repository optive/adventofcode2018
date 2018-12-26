package day_13;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CartMadnessTests {
    private CartMadness cartMadness;

    @Test
    public void locatesTheCartsCorrectly() throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/test/resources/13_input.txt"), "UTF-8");
        cartMadness = new CartMadness(lines);
        final List<MineCart> carts = cartMadness.getCarts();
        Assert.assertEquals(2, carts.size());

        final MineCart cart1 = carts.get(0);
        Assert.assertEquals(2, cart1.getX());
        Assert.assertEquals(0, cart1.getY());
        Assert.assertEquals('>', cart1.getDirection());

        final MineCart cart2 = carts.get(1);
        Assert.assertEquals(9, cart2.getX());
        Assert.assertEquals(3, cart2.getY());
        Assert.assertEquals('v', cart2.getDirection());
    }

    @Test
    public void identifiesTheFirstCollisionCorrectly() throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/test/resources/13_input.txt"), "UTF-8");
        cartMadness = new CartMadness(lines);
        final MineCart cart = cartMadness.identifyFirstCollision();
        Assert.assertEquals(7,cart.getX());
        Assert.assertEquals(3, cart.getY());
    }

    @Test
    public void identifiesTheLastRemainingCartCorrectly() throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/test/resources/13_2_input.txt"), "UTF-8");
        cartMadness = new CartMadness(lines);
        final MineCart cart = cartMadness.identifyLastCart();
        Assert.assertEquals(6,cart.getX());
        Assert.assertEquals(4, cart.getY());
    }
}
