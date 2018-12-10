package day_08;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private int childCount;
    private int metadataCount;
    private List<TreeNode> childNodes = new ArrayList<>();
    private List<Integer> metadata = new ArrayList<>();

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getMetadataCount() {
        return metadataCount;
    }

    public void setMetadataCount(int metadataCount) {
        this.metadataCount = metadataCount;
    }

    public List<TreeNode> getChildNodes() {
        return childNodes;
    }

    public List<Integer> getMetadata() {
        return metadata;
    }
}
