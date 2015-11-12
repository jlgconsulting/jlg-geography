package jlg.geography.geometry;

import jlg.geography.Boundable;
import jlg.geography.GeometryFeature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static jlg.codecontract.CodeContract.verifyNotNull;
import static jlg.codecontract.CodeContract.verifyThat;

public class MultiPolygon implements Boundable, GeometryFeature, Serializable{
    private BoundingBox boundingBox;
    private List<Polygon> polygons;

    protected MultiPolygon(){
        polygons = new ArrayList<>();
    }

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

        calculateBoundingBox();
    }

    public MultiPolygon(Polygon[] polygons) {
        verifyNotNull(polygons);
        verifyThat(polygons.length > 0, "Multipolygon in empty");
        List<Polygon> polygonList = Arrays.asList(polygons);

        Function<Polygon, double[]> getCoordinates = polygon -> {
            List<Double> singlePolygonCoordinates = new ArrayList<>();

            polygon.getPoints().stream().forEach(point -> {
                singlePolygonCoordinates.add(point.getLatitude());
                singlePolygonCoordinates.add(point.getLongitude());

            });

            double[] result = new double[singlePolygonCoordinates.size()];
            for (int i = 0; i< singlePolygonCoordinates.size(); i++) {
                result[i] = singlePolygonCoordinates.get(i);
            }
            return result;
        };

        double[][] multipolygonCoordinates = new double[polygons.length][];
        polygonList.stream().forEach(polygon -> {
            int index = polygonList.indexOf(polygon);
            multipolygonCoordinates[index] = getCoordinates.apply(polygon);
        });

        this.polygons = new ArrayList<>();
        for(double[] polygonPoints: multipolygonCoordinates){
            double[] coordinates = new double[polygonPoints.length];
            for (int i = 0; i < polygonPoints.length; i++) {
                coordinates[i] = polygonPoints[i];
            }
            this.polygons.add(new Polygon(coordinates));
        }

        calculateBoundingBox();
    }

    private void calculateBoundingBox() {
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
