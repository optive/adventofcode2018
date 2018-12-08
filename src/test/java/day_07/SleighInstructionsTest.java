package day_07;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SleighInstructionsTest {

    final private File file = new File("src/test/resources/07_input.txt");
    private SleighInstructions sleighInstructions;

    @Test
    public void calculatesOrder() throws IOException {
        final List<String> lines = FileUtils.readLines(file, "UTF-8");
        sleighInstructions = new SleighInstructions(lines);
        Assert.assertEquals("CABDFE", sleighInstructions.getOrderedSteps());
    }

    @Test
    public void calculatesTimeTaken() throws IOException {
        final List<String> lines = FileUtils.readLines(file, "UTF-8");
        sleighInstructions = new SleighInstructions(lines);
        Assert.assertEquals(15, sleighInstructions.getTotalTimeTaken(2, 0));
    }
}
