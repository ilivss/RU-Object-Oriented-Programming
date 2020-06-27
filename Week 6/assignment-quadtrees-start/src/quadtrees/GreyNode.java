package quadtrees;

import java.io.IOException;
import java.io.Writer;

public class GreyNode implements QuadTreeNode {
    private QuadTreeNode[] children;

    public GreyNode (QuadTreeNode[] children) {
        this.children = children;
    }

    @Override
    public void fillBitmap(int x, int y, int width, Bitmap bitmap) {
        children[0].fillBitmap(x, y, width/2, bitmap);
        children[1].fillBitmap(x + width/2, y, width/2, bitmap);
        children[2].fillBitmap(x + width/2, y + width/2, width/2, bitmap);
        children[3].fillBitmap(x, y + width/2, width/2, bitmap);
    }

    @Override
    public void writeNode(Writer out) {
        try {
            out.append("1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (QuadTreeNode child : children) {
            child.writeNode(out);
        }
    }
}
