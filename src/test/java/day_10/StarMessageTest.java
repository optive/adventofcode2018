package day_10;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StarMessageTest {

    private StarMessage starMessage;

    @Test
    public void solvesTheTestInput() throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/test/resources/10_input.txt"), "UTF-8");
        starMessage = new StarMessage(lines);
        starMessage.watchStars();
    }
}
