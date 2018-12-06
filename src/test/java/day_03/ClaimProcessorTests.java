package day_03;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class ClaimProcessorTests {

    private ClaimProcessor claimProcessor;

    @Test
    public void processesTheClaimString() {
        claimProcessor = new ClaimProcessor();
        final String input = "#123 @ 3,2: 5x4";

        final Claim expectedClaim = new Claim();
        expectedClaim.setId(123);
        expectedClaim.setLeftOffset(3);
        expectedClaim.setTopOffset(2);
        expectedClaim.setWidth(5);
        expectedClaim.setHeight(4);

        final Claim claim = claimProcessor.processClaim(input);

        Assert.assertEquals(expectedClaim, claim);
    }

    @Test
    public void calculatesFabricUsage() {
        final List<String> claims = Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2");
        claimProcessor = new ClaimProcessor(claims);
        Assert.assertEquals(4, claimProcessor.getContestedSquares());
    }

    @Test
    public void identifiesUncontestedClaims() {
        final List<String> claims = Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2");
        claimProcessor = new ClaimProcessor(claims);
        Assert.assertEquals(3, claimProcessor.getUncontestedClaim());
    }
}
