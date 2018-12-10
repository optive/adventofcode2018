package day_08;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LicenseFile {

    final List<TreeNode> nodes = new ArrayList<>();
    final TreeNode parentNode;

    public LicenseFile(final String input) {
        final List<String> inputList = Arrays.asList(input.split(" "));
        final List<Integer> intInputList = inputList.stream().map(Integer::parseInt).collect(Collectors.toList());
        final Iterator<Integer> inputIterator = intInputList.iterator();
        parentNode = parseNode(inputIterator);
    }

    private TreeNode parseNode(final Iterator<Integer> iterator) {
        if(iterator.hasNext()) {
            final TreeNode node = new TreeNode();
            node.setChildCount(iterator.next());
            node.setMetadataCount(iterator.next());

            for(int i=0; i<node.getChildCount(); i++) {
                node.getChildNodes().add(parseNode(iterator));
            }

            for (int i=0; i<node.getMetadataCount(); i++) {
                node.getMetadata().add(iterator.next());
            }
            nodes.add(node);
            return node;
        }
        return null; //Something has gone wrong if we reach this point.
    }

    public int getNodeValue(final TreeNode node) {
        int value = 0;
        if (node.getChildCount() > 0) {
            for (final int metadata : node.getMetadata()) {
                if (metadata <= node.getChildNodes().size()) {
                    value += getNodeValue(node.getChildNodes().get(metadata - 1));
                }
            }
        } else {
            value = node.getMetadata()
                    .stream()
                    .collect(Collectors.summingInt(i -> i));
        }
        return value;
    }

    public int getParentNodeValue() {
        return getNodeValue(parentNode);
    }

    public int getMetadataSum() {
        return nodes
                .stream()
                .map(TreeNode::getMetadata)
                .map(l-> l.stream().mapToInt(i -> i).sum())
                .collect(Collectors.summingInt(i -> i));
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/08_input.txt"), "UTF-8");
        final LicenseFile licenseFile = new LicenseFile(lines.get(0).trim());
        System.out.println(licenseFile.getMetadataSum());
        System.out.println(licenseFile.getParentNodeValue());
    }
}
