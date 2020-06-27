package filefinder;

import java.io.File;
import java.io.IOException;

public class ThreadedFileFinder implements Runnable {
	private final File rootDir;
	private String fileName;
	private long startTime;

	public ThreadedFileFinder(String root, String name) throws IOException {
		rootDir = new File(root);
		fileName = name;
		startTime = System.currentTimeMillis();
		if (!rootDir.exists()) {
			throw new IOException(root + " does not exists");
		}
		if (!rootDir.isDirectory()){
			throw new IOException(root + " is not a directory");
		}
	}

	@Override
	public void run() {
		File[] files = rootDir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.getName().equals(fileName)) {
					long stopTime = System.currentTimeMillis();
					System.out.println("Found at: " + file.getAbsolutePath() + " after " + (stopTime - startTime) + "ms");
				} else if (file.isDirectory()) {
					try {
						ThreadedFileFinder d = new ThreadedFileFinder(file.getAbsolutePath(), fileName);
						new Thread(d).start();

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
