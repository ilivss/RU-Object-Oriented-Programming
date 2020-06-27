package filefinder;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			String goal = "needle.txt";
			String root = "haystack";

//			long time1 = System.currentTimeMillis();
//			FileFinder sff = new FileFinder(root);
//			sff.findFile(goal);
//			long time2 = System.currentTimeMillis();
//			System.out.println("find ran in " + (time2 - time1) + "ms");

			ThreadedFileFinder ff = new ThreadedFileFinder(root, goal);
			ff.run();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
