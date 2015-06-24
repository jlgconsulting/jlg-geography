package jlg.geography.geometry;

import jlg.geography.GeometryFeature;
import jlg.geography.Boundable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotNull;
import static jlg.codecontract.CodeContract.verifyThat;

public class Line implements Boundable, GeometryFeature {

    private List<Point> points;
    private BoundingBox boundingBox;

    protected Line(){
        this.points = new ArrayList<>();
    }

    public Line(Point[] points) {
        this.points = Arrays.asList(points);
        boundingBox = new BoundingBox(points);
    }

    public Line(double[] coordinates) {
        verifyNotNull(coordinates);
        verifyThat(coordinates.length >= 4, "Coordinates size must be gte than 4 in order to create a line (2 points)");

        points = new ArrayList<>();
        for (int i = 0; i < coordinates.length; i += 2) {
            this.points.add(new Point(coordinates[i], coordinates[i + 1]));
        }

        boundingBox = new BoundingBox(points.toArray(new Point[points.size()]));
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
}
