import processing.core.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DrawPoints extends PApplet {

	String s;
	List<Point> pointList;

	public void settings() {
		size(500, 500);
		s = Paths.get("").toAbsolutePath().getFileName().toString();
	}

	public void setup() {
		background(128);
		noLoop();
	}

	public void draw() {
		List<Point> points = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File("positions.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(", ");
				if(parts.length == 3) {
					float x = Float.parseFloat(parts[0]);
					float y = Float.parseFloat(parts[1]);
					float z = Float.parseFloat(parts[2]);
					points.add(new Point(x, y, z));
				}

				// Each line contains comma and space separated x, y, and z values
				// You will add a Point to the stream builder/list for each line
				// TODO: Process the line as a string here
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Initialize the stream
		// TODO: Create the stream here using the builder/list
		Stream<Point> pointStream = points.stream();

		// Process the stream
		pointList = pointStream
				// TODO: Add additional operations here to transform the points
				.filter(point -> point.z <= 2.0)
				.map(point -> new Point(point.x * 0.5f, point.y * 0.5f, point.z * 0.5f))
				.map(point -> new Point(point.x - 150.0f, point.y - 562.0f , point.z))
				.map(point -> new Point(point.x, -point.y, point.z))
				.collect(Collectors.toList());

		// You should create a stream of Points using either a list or stream builder.
		// TODO: Initialize either a stream builder or list here
		// Display the stream
		for (Point p : pointList){
			ellipse((int) p.x, (int) p.y, 1, 1);
			fill(126, 126, 126);
			text(s, 0, 500);
		}

	}

	public static void main(String[] args) {
		PApplet.main("DrawPoints");
	}
}