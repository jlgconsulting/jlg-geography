package jlg.geography.cartesian;

import jlg.geography.AbstractMultiPolygon;
import jlg.geography.wsg84.LatLon;

import java.util.ArrayList;
import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyNotNull;

public class CartesianMultiPolygon extends AbstractMultiPolygon<CartesianPolygon,Cartesian> {
    private CartesianBoundingBox boundingBox;

    public CartesianMultiPolygon(List<List<Integer>> multiPolygonPoints){
        verifyNotNull(multiPolygonPoints);
        verifyNotEmpty(multiPolygonPoints);

        this.polygons = new ArrayList<>();
        for(List<Integer> polygonPoints:multiPolygonPoints){
            int[] coordinates = new int[polygonPoints.size()];
            for (int i = 0; i < polygonPoints.size(); i++) {
                coordinates[i] = polygonPoints.get(i);
            }
            this.polygons.add(new CartesianPolygon(coordinates));
        }

        //calculate boundign box
        int minLatitude = this.polygons.stream().mapToInt(x -> x.getBoundingBox().getMinCoordinate().getX()).min().getAsInt();
        int maxLatitude = this.polygons.stream().mapToInt(x -> x.getBoundingBox().getMaxCoordinate().getX()).max().getAsInt();
        int minLongitude = this.polygons.stream().mapToInt(x -> x.getBoundingBox().getMinCoordinate().getY()).min().getAsInt();
        int maxLongitude = this.polygons.stream().mapToInt(x -> x.getBoundingBox().getMaxCoordinate().getY()).max().getAsInt();
        boundingBox = new CartesianBoundingBox(new Cartesian(minLatitude,minLongitude), new Cartesian(maxLatitude,maxLongitude));
    }

    public CartesianBoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public List<Cartesian> getPoints() {
        List<Cartesian> points = new ArrayList<>();
        for(CartesianPolygon polygon : this.polygons){
            points.addAll(polygon.getPoints());
        }
        return points;
    }
}
