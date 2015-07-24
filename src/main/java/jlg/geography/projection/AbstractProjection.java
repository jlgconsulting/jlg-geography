package jlg.geography.projection;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract class that needs to be implemented by all projection classes
 */
public abstract class AbstractProjection {

    protected final double DEGREES_TO_RADIANS = Math.PI / 180;
    protected final double EARTH_ECCENTRICITY = 0.081819;
    protected final double EARTH_MAJOR_AXIS_IN_METERS = 6378137.0;

    public Point project(Point point){
        return project(point,0);
    }

    public Point project(Point point, Function<Double, Double> transform) {
        Point projectedPoint = project(point);
        Double transformedLatitude = transform.apply(projectedPoint.getLatitude());
        Double transformedLongitude = transform.apply(projectedPoint.getLongitude());
        return new Point(transformedLatitude, transformedLongitude);
    }

    public Line project(Line line) {
        List<Point> projectedPoints = line.getPoints().stream().map(point -> project(point)).collect(Collectors.toList());
        return new Line(projectedPoints);
    }

    public Line project(Line line, Function<Double, Double> transform) {
        List<Point> projectedPoints = line.getPoints().stream().map(point -> project(point, transform)).collect(Collectors.toList());
        return new Line(projectedPoints);
    }

    public Polygon project(Polygon polygon) {
        List<Point> projectedPoints = polygon.getPoints().stream().map(point -> project(point)).collect(Collectors.toList());
        return new Polygon(projectedPoints.toArray(new Point[projectedPoints.size()]));
    }

    public Polygon project(Polygon polygon, Function<Double, Double> transform) {
        List<Point> projectedPoints = polygon.getPoints().stream().map(point -> project(point, transform)).collect(Collectors.toList());
        return new Polygon(projectedPoints.toArray(new Point[projectedPoints.size()]));
    }

    public MultiPolygon project(MultiPolygon multiPolygon, Function<Double, Double> transformFunction) {
        List<Polygon> projectedPolygons = multiPolygon.getPolygons().stream().map(polygon -> project(polygon, transformFunction)).collect(Collectors.toList());
        return new MultiPolygon(projectedPolygons.toArray(new Polygon[projectedPolygons.size()]));
    }

    public abstract Point project(Point point, int altitude);

    protected double round(double value){
        BigDecimal b = new BigDecimal(value);
        b = b.setScale(6,BigDecimal.ROUND_UP);
        return (b.doubleValue());
    }

}
