package day_03;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClaimProcessor {

    private int[][] fabric = new int[1000][1000];
    private List<Claim> claims = new ArrayList<>();

    public ClaimProcessor() {
        // Default contstructor
    }

    public ClaimProcessor(List<String> claims) {
        for (final String c: claims) {
            final Claim claim = processClaim(c);
            addClaimToFabric(claim);
            this.claims.add(claim);
        }
    }

    public Claim processClaim(final String claimString) {
        final String[] claimArray = claimString.split(" ");

        final Claim claim = new Claim();
        claim.setId(Integer.valueOf(claimArray[0].substring(1)));

        final String[] offsets = claimArray[2].split(",");
        claim.setLeftOffset(Integer.valueOf(offsets[0]));

        final String topOffset = offsets[1];
        claim.setTopOffset(Integer.valueOf(topOffset.substring(0, topOffset.length() -1)));

        final String[] dimensions = claimArray[3].split("x");
        claim.setWidth(Integer.valueOf(dimensions[0]));
        claim.setHeight(Integer.valueOf(dimensions[1]));

        return claim;
    }

    public void addClaimToFabric(final Claim claim) {
        final int rightLimit = claim.getLeftOffset() + claim.getWidth();
        final int baseLimit = claim.getTopOffset() + claim.getHeight();

        for (int i=claim.getLeftOffset(); i<rightLimit; i++) {
            for (int j=claim.getTopOffset(); j<baseLimit; j++) {
                fabric[i][j]++;
            }
        }
    }

    public boolean isContested(final Claim claim) {
        final int rightLimit = claim.getLeftOffset() + claim.getWidth();
        final int baseLimit = claim.getTopOffset() + claim.getHeight();
        boolean isContested = false;

        for (int i=claim.getLeftOffset(); i<rightLimit; i++) {
            for (int j=claim.getTopOffset(); j<baseLimit; j++) {
                if (fabric[i][j] > 1) {
                    isContested = true;
                }
            }
        }
        return isContested;
    }

    public int getUncontestedClaim() {
        for (final Claim claim: claims) {
            if (!isContested(claim)) {
                return claim.getId();
            }
        }
        return 0;
    }

    public int getContestedSquares() {
        int contestedSquares = 0;

        for (int i=0; i<1000; i++) {
            for (int j=0; j<1000; j++) {
                if(fabric[i][j] > 1) {
                    contestedSquares++;
                }
            }
        }

        return contestedSquares;
    }


    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/03_input.txt"), "UTF-8");

        final ClaimProcessor claimProcessor = new ClaimProcessor(lines);
        System.out.println(claimProcessor.getContestedSquares());
        System.out.println(claimProcessor.getUncontestedClaim());
    }
}
