package jlg.geography.geometry;

import jlg.codecontract.CodeContractException;
import jlg.geography.GeometryFeature;
import jlg.geography.Boundable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon implements Boundable, GeometryFeature{
    private List<Point> points;
    private BoundingBox boundingBox;

    protected Polygon(){
        this.points = new ArrayList<>();
    }

    public Polygon(Point[] points) {
        this.points = Arrays.asList(points);
        checkIfPolygonIsClosed();
        boundingBox = new BoundingBox(points);
    }

    public Polygon(double[] coordinates) {
        points = new ArrayList<>();
        for(int i=0; i<coordinates.length;i+=2){
            this.points.add(new Point(coordinates[i],coordinates[i+1]));
        }
        checkIfPolygonIsClosed();
        boundingBox = new BoundingBox(points.toArray(new Point[points.size()]));
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public boolean contains(Point point) {
        if(!point.isInBoundingBox(getBoundingBox())){
            return false;
        }

        int windingNumber = 0;
        for(int i=0; i<this.points.size()-1;i++){
            if(this.points.get(i).getLatitude() <= point.getLatitude()){
                if(this.points.get(i+1).getLatitude() > point.getLatitude()){
                    //an upward crossing
                    if(isLeft(this.points.get(i), this.points.get(i+1), point) > 0){
                        //has a valid up intersect
                        ++windingNumber;
                    }
                }
            }
            else{
                if(this.points.get(i+1).getLatitude() <= point.getLatitude()){
                    //a downward crossing
                    if(isLeft(this.points.get(i), this.points.get(i+1), point) < 0){
                        //has a valid down intersect
                        --windingNumber;
                    }
                }
            }
        }
        return windingNumber != 0;
    }

    private double isLeft(Point p0, Point p1, Point somePoint){
        return (p1.getLongitude() - p0.getLongitude()) * (somePoint.getLatitude() - p0.getLatitude())
                - (somePoint.getLongitude() - p0.getLongitude()) * (p1.getLatitude() - p0.getLatitude());
    }

    private void checkIfPolygonIsClosed() {
        Point firstPolygonPoint = this.points.get(0);
        Point lastPolygonPoint = this.points.get(this.points.size()-1);

        if(firstPolygonPoint.getLongitude() != lastPolygonPoint.getLongitude()){
            throw new CodeContractException("The polygon is not closed. Can not construct the polygon. Latitudes are different. ");
        }

        if(firstPolygonPoint.getLatitude() != lastPolygonPoint.getLatitude()){
            throw new CodeContractException("The polygon is not closed. Can not construct the polygon. Longitudes are different");
        }
    }

    public List<Point> getPoints() {
        return points;
    }
}
