package jlg.geography.geometry;

import jlg.geography.GeometryFeature;
import jlg.geography.Boundable;

import java.util.ArrayList;
import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyNotNull;

public class MultiPolygon implements Boundable, GeometryFeature{
    private BoundingBox boundingBox;
    private List<Polygon> polygons;
    private List<Point> points;

    public MultiPolygon(List<List<Double>> multiPolygonPoints) {
        verifyNotNull(multiPolygonPoints);
        verifyNotEmpty(multiPolygonPoints);

        this.polygons = new ArrayList<>();
        for(List<Double> polygonPoints:multiPolygonPoints){
            double[] coordinates = new double[polygonPoints.size()];
            for (int i = 0; i < polygonPoints.size(); i++) {
                coordinates[i] = polygonPoints.get(i);
            }
            this.polygons.add(new Polygon(coordinates));
        }

        //calculate boundign box
        double minLatitude = this.polygons.stream().mapToDouble(x -> x.getBoundingBox().getMinCoordinate().getLatitude()).min().getAsDouble();
        double maxLatitude = this.polygons.stream().mapToDouble(x -> x.getBoundingBox().getMaxCoordinate().getLongitude()).max().getAsDouble();
        double minLongitude = this.polygons.stream().mapToDouble(x -> x.getBoundingBox().getMinCoordinate().getLatitude()).min().getAsDouble();
        double maxLongitude = this.polygons.stream().mapToDouble(x -> x.getBoundingBox().getMaxCoordinate().getLongitude()).max().getAsDouble();
        boundingBox = new BoundingBox(new Point(minLatitude,minLongitude), new Point(maxLatitude,maxLongitude));
    }


    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        for(Polygon polygon : this.polygons){
            points.addAll(polygon.getPoints());
        }

        return points;
    }

    public boolean contains(Point point) {
        return polygons.stream().anyMatch(x -> x.contains(point));
    }
}
