package day_23;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class EmergencyTeleporterTests {

    private EmergencyTeleporter emergencyTeleporter;

    @Test
    public void calculatesTheNanobotCountInRange() throws IOException {
        emergencyTeleporter = new EmergencyTeleporter(FileUtils.readLines(new File("src/test/resources/23_input.txt"), "UTF-8"));
        Assert.assertEquals(7, emergencyTeleporter.getNanobotCountInRange());
    }

}
