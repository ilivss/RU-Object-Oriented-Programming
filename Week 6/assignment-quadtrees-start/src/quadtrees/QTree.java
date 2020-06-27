package quadtrees;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class QTree {
	private QuadTreeNode root;

	public QTree(Reader input) {
		root = readQTree(input);
	}

	public QTree(Bitmap bitmap) {
		root = bitmap2QTree(0, 0, bitmap.getWidth(), bitmap);
	}

	public void fillBitmap(Bitmap bitmap) {
		root.fillBitmap(0, 0, bitmap.getWidth(), bitmap);
	}

	public void writeQTree(Writer sb) {
		root.writeNode(sb);
	}

	private static QuadTreeNode readQTree(Reader input) {
		try {
			int value = input.read();

			if (value == '1'){
				QuadTreeNode child0 = readQTree(input);
				QuadTreeNode child1 = readQTree(input);
				QuadTreeNode child2 = readQTree(input);
				QuadTreeNode child3 = readQTree(input);

				return new GreyNode(new QuadTreeNode[]{child0, child1, child2, child3});
			}
			else if (value == '0') {
				int value2 = input.read();

				if (value2 == '1') {
					return new WhiteLeaf();
				}
				else {
					return new BlackLeaf();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static QuadTreeNode bitmap2QTree(int x, int y, int width, Bitmap bitmap) {
		if (bitmap.getBit(x, y)) {
			for (int i = x; i < width; i++) {
				for (int j = y; j < width; j++) {
					if (!bitmap.getBit(i,j)) {
						return getQuadTreeNode(x, y, width, bitmap);
					}
				}
			}
			return new WhiteLeaf();
		}
		else {
			for (int i = x; i < width; i++) {
				for (int j = y; j < width; j++) {
					if (bitmap.getBit(i,j)) {
						if (bitmap.getBit(i,j)) {
							return getQuadTreeNode(x, y, width, bitmap);
						}
					}
				}
			}
			return new BlackLeaf();
		}
	}

	private static QuadTreeNode getQuadTreeNode(int x, int y, int width, Bitmap bitmap) {
		QuadTreeNode child0 = bitmap2QTree(x, y, width/2, bitmap);
		QuadTreeNode child1 = bitmap2QTree(x + width/2, y, width/2, bitmap);
		QuadTreeNode child2 = bitmap2QTree(x + width/2, y + width/2, width/2, bitmap);
		QuadTreeNode child3 = bitmap2QTree(x, y + width/2, width/2, bitmap);

		return new GreyNode(new QuadTreeNode[]{child0, child1, child2, child3});
	}

}
