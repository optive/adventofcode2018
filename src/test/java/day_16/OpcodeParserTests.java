package day_16;

import org.junit.Assert;
import org.junit.Test;

public class OpcodeParserTests {

    private OpCodeParser opCodeParser;

    @Test
    public void identifiesTheNumberOfPossibleOpCodes() throws Exception {
        opCodeParser = new OpCodeParser();
        final OpCodeSample example = new OpCodeSample();
        example.setBefore(new int[]{3,2,1,1});
        example.setAfter(new int[]{3,2,2,1});
        example.setA(2);
        example.setB(1);
        example.setC(2);
        final int numPossibleOpCodes = opCodeParser.getPossibleOpCodes(example);
        Assert.assertEquals(3, numPossibleOpCodes);
    }
}
