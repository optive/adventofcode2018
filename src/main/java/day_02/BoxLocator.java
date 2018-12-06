package day_02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoxLocator {

    final List<String> ids;

    public BoxLocator(final List<String> ids) {
        this.ids = ids;
    }

    public String detectBoxId() {
        final String exampleId = ids.get(0);
        String boxId = null;
        int position = 0;
        for (int i= 0; i<exampleId.length(); i++) {
            final List<String> contractedIds = generateContractedIds(i);
            final Set<String> uniqueIds = new HashSet<>();

            for (final String id: contractedIds) {
                final boolean unique = uniqueIds.add(id);
                if (!unique) {
                    boxId = id;
                    break;
                }
            }
        }
        return boxId;
    }

    private List<String> generateContractedIds(final int position) {
        final List<String> contractedIds = new ArrayList<>();
        for (final String id : ids) {
            contractedIds.add(id.substring(0,position) + id.substring(position + 1, id.length()));
        }
        return contractedIds;
    }


    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/02_input.txt"), "UTF-8");

        final BoxLocator boxLocator = new BoxLocator(lines);
        System.out.println(boxLocator.detectBoxId());
    }
}
