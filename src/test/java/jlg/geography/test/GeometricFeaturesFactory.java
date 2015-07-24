package jlg.geography.test;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeometricFeaturesFactory {

    public static Line getLineWithThesePoints(Point[] points) {
        return new Line(points);
    }

    public static Line getLine() {
        return new Line(getPoints(3));
    }

    public static List<Line> getLines(int numberOfLines) {

        List<Line> lines = new ArrayList<>();

        while (lines.size() < numberOfLines) {
            lines.add(
                    new Line(getPoints(3))
            );
        }
        return lines;
    }

    public static Polygon getPolygon() {
        return new Polygon(getPolygonCoordinates(4));
    }

    public static MultiPolygon getMultiPolygon(int numberOfPolygons) {
        Polygon[] polygons = new Polygon[numberOfPolygons];
        for (int i = 0; i < numberOfPolygons; i++)
            polygons[i] = getPolygon();

        return new MultiPolygon(polygons);
    }

    private static Point[] getPoints(int numberOfPoints) {
        Random generator = new Random();

        Point[] points = new Point[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            int x = 1 + generator.nextInt(5);
            int y = 1 + generator.nextInt(5);
            points[i] = new Point(x, y);
        }

        return points;
    }

    private static double[] getPolygonCoordinates(int numberOfPoints) {
        Random generator = new Random();
        double[] coordinates = new double[numberOfPoints * 2];

        int index = 0;
        while (index < (numberOfPoints - 1) * 2){
            int coordinate = 1 + generator.nextInt(5);
            coordinates[index] = coordinate;
            index++;
        }

        coordinates[numberOfPoints * 2 - 2] = coordinates[0];
        coordinates[numberOfPoints * 2 - 1] = coordinates[1];

        return coordinates;
    }
}
