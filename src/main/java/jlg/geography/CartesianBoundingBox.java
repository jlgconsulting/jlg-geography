package jlg.geography;

import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyThat;

public class CartesianBoundingBox implements BoundingBox<Cartesian> {
    private Cartesian minCoordinate;
    private Cartesian maxCoordinate;
    private Cartesian centerCoordinate;

    protected CartesianBoundingBox(){}

    /**
     * @param coordinates variable number of coordinates from which to build the bounding box. (minimum is 2)
     */
    public CartesianBoundingBox(Cartesian... coordinates){
        verifyThat(coordinates.length >= 2);

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for(Cartesian cartesian : coordinates){
            minX = Integer.min(minX, cartesian.getX());
            maxX = Integer.max(maxX, cartesian.getX());
            minY = Integer.min(minY, cartesian.getY());
            maxY = Integer.max(maxY, cartesian.getY());
        }

        minCoordinate = new Cartesian(minX, minY);
        maxCoordinate = new Cartesian(maxX, maxY);
        int centerX = (maxCoordinate.getX() + minCoordinate.getX()) / 2;
        int centerY = (maxCoordinate.getY() + minCoordinate.getY()) / 2;
        centerCoordinate = new Cartesian(centerX, centerY);
    }

    /**
     * @param boundingBoxes a list of bounding boxes that will make up this larger bounding box
     */
    public CartesianBoundingBox(List<CartesianBoundingBox> boundingBoxes){
        verifyThat(boundingBoxes != null);
        verifyNotEmpty(boundingBoxes);

        int minLatitude = boundingBoxes.stream().mapToInt(x -> x.getMinCoordinate().getX()).min().getAsInt();
        int minLongitude = boundingBoxes.stream().mapToInt(x -> x.getMinCoordinate().getY()).min().getAsInt();
        int maxLatitude = boundingBoxes.stream().mapToInt(x -> x.getMaxCoordinate().getX()).max().getAsInt();
        int maxLongitude = boundingBoxes.stream().mapToInt(x -> x.getMaxCoordinate().getY()).max().getAsInt();

        minCoordinate = new Cartesian(minLatitude, minLongitude);
        maxCoordinate = new Cartesian(maxLatitude, maxLongitude);
        int centerLat = (maxCoordinate.getX() + minCoordinate.getY()) / 2;
        int centerLon = (maxCoordinate.getX() + minCoordinate.getY()) / 2;
        centerCoordinate = new Cartesian(centerLat,centerLon);
    }

    @Override
    public Cartesian getMinCoordinate() {
        return minCoordinate;
    }

    @Override
    public Cartesian getMaxCoordinate() {
        return maxCoordinate;
    }

    @Override
    public Cartesian getCenterCoordinate() {
        return centerCoordinate;
    }

    /**
     * Checks if a given coordinate can be contained inside this bounding box
     */
    @Override
    public boolean contains(Cartesian coordinate) {
        verifyThat(coordinate != null);

        if(coordinate.getX() >= this.minCoordinate.getX() && coordinate.getX() <= this.maxCoordinate.getX()){
            if(coordinate.getY() >= this.minCoordinate.getY() && coordinate.getY() <= this.maxCoordinate.getY()){
                return true;
            }
        }
        return false;
    }
}
