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
     * @param minCoordinate minimum x and y values for the boundign box
     * @param maxCoordinate maximum x and y values for the bounding box
     */
    public CartesianBoundingBox(Cartesian minCoordinate, Cartesian maxCoordinate){
        verifyThat(minCoordinate != null);
        verifyThat(maxCoordinate != null);

        int centerX = (maxCoordinate.getX() + minCoordinate.getX()) / 2;
        int centerY = (maxCoordinate.getY() + minCoordinate.getY()) / 2;

        this.minCoordinate = minCoordinate;
        this.maxCoordinate = maxCoordinate;
        this.centerCoordinate = new Cartesian(centerX, centerY);
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

        this.minCoordinate = new Cartesian(minLatitude, minLongitude);
        this.maxCoordinate = new Cartesian(maxLatitude, maxLongitude);
        int centerLat = (maxCoordinate.getX() + minCoordinate.getY()) / 2;
        int centerLon = (maxCoordinate.getX() + minCoordinate.getY()) / 2;
        this.centerCoordinate = new Cartesian(centerLat,centerLon);
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
