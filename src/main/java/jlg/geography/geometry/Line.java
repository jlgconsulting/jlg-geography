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

    public Line(List<Point> points) {
        this.points = points;
        boundingBox = new BoundingBox(points.toArray(new Point[points.size()]));
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

    @Override
    public boolean contains(Point point) {
        if(!point.isInBoundingBox(getBoundingBox())){
            return false;
        }

        for (int i = 0; i < this.points.size() - 2; i++) {
            if (isOnSegment(this.points.get(i), this.points.get(i + 1), point))
                return true;
        }

        return false;
    }

    private boolean isOnSegment(Point p0, Point p1, Point point) {
        return (p1.getLongitude() - p0.getLongitude()) * (point.getLatitude() - p0.getLatitude())
                - (point.getLongitude() - p0.getLongitude()) * (p1.getLatitude() - p0.getLatitude()) == 0;
    }
}
