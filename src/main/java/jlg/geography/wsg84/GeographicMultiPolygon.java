package jlg.geography.wsg84;

import jlg.geography.AbstractMultiPolygon;
import jlg.geography.GeometryFeature;

import java.util.ArrayList;
import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyNotNull;

public class GeographicMultiPolygon extends AbstractMultiPolygon<GeographicPolygon, LatLon> implements GeometryFeature {
    private GeographicBoundingBox boundingBox;

    public GeographicMultiPolygon(List<List<Double>> multiPolygonPoints){
        verifyNotNull(multiPolygonPoints);
        verifyNotEmpty(multiPolygonPoints);

        this.polygons = new ArrayList<>();
        for(List<Double> polygonPoints:multiPolygonPoints){
            double[] coordinates = new double[polygonPoints.size()];
            for (int i = 0; i < polygonPoints.size(); i++) {
                coordinates[i] = polygonPoints.get(i);
            }
            this.polygons.add(new GeographicPolygon(coordinates));
        }

        //calculate boundign box
        double minLatitude = this.polygons.stream().mapToDouble(x -> x.getBoundingBox().getMinCoordinate().getLatitude()).min().getAsDouble();
        double maxLatitude = this.polygons.stream().mapToDouble(x -> x.getBoundingBox().getMaxCoordinate().getLongitude()).max().getAsDouble();
        double minLongitude = this.polygons.stream().mapToDouble(x -> x.getBoundingBox().getMinCoordinate().getLatitude()).min().getAsDouble();
        double maxLongitude = this.polygons.stream().mapToDouble(x -> x.getBoundingBox().getMaxCoordinate().getLongitude()).max().getAsDouble();
        boundingBox = new GeographicBoundingBox(new LatLon(minLatitude,minLongitude), new LatLon(maxLatitude,maxLongitude));
    }

    public GeographicBoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public List getPoints() {
        List<LatLon> points = new ArrayList<>();
        for(GeographicPolygon polygon : this.polygons){
            points.addAll(polygon.getPoints());
        }
        return points;
    }
}
