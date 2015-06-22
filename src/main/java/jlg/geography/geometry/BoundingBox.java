package jlg.geography.geometry;

import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyThat;

public class BoundingBox {
    private Point minCoordinate;
    private Point maxCoordinate;
    private Point centerCoordinate;


    public BoundingBox(Point... coordinates) {
        verifyThat(coordinates.length >= 2);

        double minX = Integer.MAX_VALUE;
        double maxX = Integer.MIN_VALUE;
        double minY = Integer.MAX_VALUE;
        double maxY = Integer.MIN_VALUE;

        for(Point point : coordinates){
            minX = Double.min(minX, point.getLongitude());
            maxX = Double.max(maxX, point.getLongitude());
            minY = Double.min(minY, point.getLatitude());
            maxY = Double.max(maxY, point.getLatitude());
        }

        minCoordinate = new Point(minX, minY);
        maxCoordinate = new Point(maxX, maxY);
        double centerX = (maxCoordinate.getLongitude() + minCoordinate.getLongitude()) / 2;
        double centerY = (maxCoordinate.getLatitude() + minCoordinate.getLatitude()) / 2;
        centerCoordinate = new Point(centerX, centerY);
    }

    public BoundingBox(List<BoundingBox> boundingBoxes) {
        verifyThat(boundingBoxes != null);
        verifyNotEmpty(boundingBoxes);

        double minLatitude = boundingBoxes.stream().mapToDouble(x -> x.getMinCoordinate().getLongitude()).min().getAsDouble();
        double minLongitude = boundingBoxes.stream().mapToDouble(x -> x.getMinCoordinate().getLatitude()).min().getAsDouble();
        double maxLatitude = boundingBoxes.stream().mapToDouble(x -> x.getMaxCoordinate().getLongitude()).max().getAsDouble();
        double maxLongitude = boundingBoxes.stream().mapToDouble(x -> x.getMaxCoordinate().getLatitude()).max().getAsDouble();

        minCoordinate = new Point(minLatitude, minLongitude);
        maxCoordinate = new Point(maxLatitude, maxLongitude);
        double centerLat = (maxCoordinate.getLongitude() + minCoordinate.getLatitude()) / 2;
        double centerLon = (maxCoordinate.getLongitude() + minCoordinate.getLatitude()) / 2;
        centerCoordinate = new Point(centerLat,centerLon);
    }

    public Point getMinCoordinate() {
        return minCoordinate;
    }

    public Point getMaxCoordinate() {
        return maxCoordinate;
    }

    public Point getCenterCoordinate() {
        return centerCoordinate;
    }

    public boolean contains(Point point) {
        verifyThat(point != null);

        if(point.getLongitude() >= this.minCoordinate.getLongitude() && point.getLongitude() <= this.maxCoordinate.getLongitude()){
            if(point.getLatitude() >= this.minCoordinate.getLatitude() && point.getLatitude() <= this.maxCoordinate.getLatitude()){
                return true;
            }
        }
        return false;
    }
}
