package geometric;

public class Assignment03Tester {

	private Model model = new Model(10);

	public Assignment03Tester() {
	}

	public void createCircle(double x, double y, double r) {
		model.addCircle(x,y,r);
	}

	public void createRectangle(double x, double y, double width, double height) {
		model.addRectangle(x,y,width,height);
	}

	public double topBorder(int index) {
		return model.getGeometric_objects()[index].topBorder();
	}

	public double rightBorder(int index) {
		return model.getGeometric_objects()[index].rightBorder();
	}

	public double bottomBorder(int index) {
		return model.getGeometric_objects()[index].bottomBorder();
	}

	public double leftBorder(int index) {
		return model.getGeometric_objects()[index].leftBorder();
	}

	public double area(int index) {
		return model.getGeometric_objects()[index].getArea();
	}

	public void move(int index, double dx, double dy) {
		model.move(index, dx, dy);
	}

	public void sortByArea() {
		model.sort();
	}

	public void sortByX() {
		model.sort('x');
	}

	public void sortByY() {
		model.sort('y');
	}
}
