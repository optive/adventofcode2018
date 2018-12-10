package day_08;

import org.junit.Assert;
import org.junit.Test;

public class LicenseFileTests {

    private LicenseFile licenseFile;

    @Test
    public void calculatesTheMetadataSum() {
        licenseFile = new LicenseFile("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");
        Assert.assertEquals(138, licenseFile.getMetadataSum());
    }

    @Test
    public void calculatesASimpleNodeValue() {
        licenseFile = new LicenseFile("0 4 1 2 3 4");
        Assert.assertEquals(10, licenseFile.getParentNodeValue());
    }

    @Test
    public void calculatesNodeValueForOneChildNode() {
        licenseFile = new LicenseFile("1 2 0 3 1 2 3 1 2");
        Assert.assertEquals(6, licenseFile.getParentNodeValue());
    }

    @Test
    public void calculatesTheExampleNodeValue() {
        licenseFile = new LicenseFile("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");
        Assert.assertEquals(66, licenseFile.getParentNodeValue());
    }


}
