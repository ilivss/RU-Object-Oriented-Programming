package supermarket;

public class Item {

	private int barcode;

	public Item(int barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "Item{" + barcode + '}';
	}
}
