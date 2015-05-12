package jlg.geography;

import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyThat;

public class LatLonBoundingBox implements BoundingBox<LatLon> {
    private LatLon minCoordinate;
    private LatLon maxCoordinate;
    private LatLon centerCoordinate;

    protected LatLonBoundingBox(){}

    /**
     * @param coordinates variable number of coordinates from which to build the bounding box. (minimum is 2)
     */
    public LatLonBoundingBox(LatLon... coordinates){
        verifyThat(coordinates.length >= 2);

        double minLat = Double.MAX_VALUE;
        double maxLat = Double.MIN_VALUE;
        double minLon = Double.MAX_VALUE;
        double maxLon = Double.MIN_VALUE;

        for(LatLon latLon : coordinates){
            minLat = Double.min(minLat, latLon.getLatitude());
            maxLat = Double.max(maxLat, latLon.getLatitude());
            minLon = Double.min(minLon, latLon.getLongitude());
            maxLon = Double.max(maxLon, latLon.getLongitude());
        }

        minCoordinate = new LatLon(minLat, minLon);
        maxCoordinate = new LatLon(maxLat, maxLon);
        double centerX = (maxCoordinate.getLatitude() + minCoordinate.getLatitude()) / 2;
        double centerY = (maxCoordinate.getLongitude() + minCoordinate.getLongitude()) / 2;
        centerCoordinate = new LatLon(centerX, centerY);
    }

    /**
     * @param boundingBoxes a list of bounding boxes that will make up this larger bounding box
     */
    public LatLonBoundingBox(List<LatLonBoundingBox> boundingBoxes){
        verifyThat(boundingBoxes != null);
        verifyNotEmpty(boundingBoxes);

        double minLatitude = boundingBoxes.stream().mapToDouble(x -> x.getMinCoordinate().getLatitude()).min().getAsDouble();
        double minLongitude = boundingBoxes.stream().mapToDouble(x -> x.getMinCoordinate().getLongitude()).min().getAsDouble();
        double maxLatitude = boundingBoxes.stream().mapToDouble(x -> x.getMaxCoordinate().getLatitude()).max().getAsDouble();
        double maxLongitude = boundingBoxes.stream().mapToDouble(x -> x.getMaxCoordinate().getLongitude()).max().getAsDouble();

        minCoordinate = new LatLon(minLatitude, minLongitude);
        maxCoordinate = new LatLon(maxLatitude, maxLongitude);
        double centerLat = (maxCoordinate.getLatitude() + minCoordinate.getLatitude()) / 2;
        double centerLon = (maxCoordinate.getLongitude() + minCoordinate.getLongitude()) / 2;
        centerCoordinate = new LatLon(centerLat,centerLon);
    }

    @Override
    public LatLon getMinCoordinate() {
        return minCoordinate;
    }

    @Override
    public LatLon getMaxCoordinate() {
        return maxCoordinate;
    }

    @Override
    public LatLon getCenterCoordinate() {
        return centerCoordinate;
    }

    /**
     * Checks if a given coordinate can be contained inside this bounding box
     */
    @Override
    public boolean contains(LatLon coordinate) {
        verifyThat(coordinate != null);
        if(coordinate.getLatitude() >= this.minCoordinate.getLatitude() && coordinate.getLatitude() <= this.maxCoordinate.getLatitude()){
            if(coordinate.getLongitude() >= this.minCoordinate.getLongitude() && coordinate.getLongitude() <= this.maxCoordinate.getLongitude()){
                return true;
            }
        }
        return false;
    }
}
