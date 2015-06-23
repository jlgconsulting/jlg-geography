package jlg.geography.geometry;

import jlg.geography.GeometryFeature;
import jlg.geography.Boundable;

import java.util.ArrayList;
import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyNotNull;
import static jlg.codecontract.CodeContract.verifyThat;

public class MultiPolygon implements Boundable, GeometryFeature{
    private BoundingBox boundingBox;
    private List<Polygon> polygons;

    public MultiPolygon(double[][] multiPolygonPoints) {
        verifyNotNull(multiPolygonPoints);
        verifyThat(multiPolygonPoints.length > 0, "Multipolygon in empty");

        this.polygons = new ArrayList<>();
        for(double[] polygonPoints: multiPolygonPoints){
            double[] coordinates = new double[polygonPoints.length];
            for (int i = 0; i < polygonPoints.length; i++) {
                coordinates[i] = polygonPoints[i];
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
