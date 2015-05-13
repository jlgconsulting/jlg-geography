package jlg.geography.wsg84;

import jlg.codecontract.CodeContractException;
import jlg.geography.AbstractPolygon;
import jlg.geography.BoundingBox;

public class GeographicPolygon extends AbstractPolygon<LatLon> {
    public GeographicPolygon(LatLon[] points){
        super(points);
        checkIfPolygonIsClosed();
        boundingBox = new GeographicBoundingBox(points);
    }

    public GeographicPolygon(double[] coordinates){
        super();
        for(int i=0; i<coordinates.length;i+=2){
            this.points.add(new LatLon(coordinates[i],coordinates[i+1]));
        }
        checkIfPolygonIsClosed();
        boundingBox = new GeographicBoundingBox(points.toArray(new LatLon[this.points.size()]));
    }

    @Override
    public boolean contains(LatLon latLon) {
        if(!latLon.isInBoundingBox(getBoundingBox())){
            return false;
        }
        int windingNumber = 0;
        for(int i=0; i<this.points.size()-1;i++){
            if(this.points.get(i).getLongitude() <= latLon.getLongitude()){
                if(this.points.get(i+1).getLongitude() > latLon.getLongitude()){
                    //an upward crossing
                    if(isLeft(this.points.get(i), this.points.get(i+1), latLon) > 0){
                        //has a valid up intersect
                        ++windingNumber;
                    }
                }
            }
            else{
                if(this.points.get(i+1).getLongitude() <= latLon.getLongitude()){
                    //a downward crossing
                    if(isLeft(this.points.get(i), this.points.get(i+1), latLon) < 0){
                        //has a valid down intersect
                        --windingNumber;
                    }
                }
            }
        }
        return windingNumber != 0;
    }

    private double isLeft(LatLon p0, LatLon p1, LatLon somePoint){
        return (p1.getLatitude() - p0.getLatitude()) * (somePoint.getLongitude() - p0.getLongitude())
                - (somePoint.getLatitude() - p0.getLatitude()) * (p1.getLongitude() - p0.getLongitude());
    }

    private void checkIfPolygonIsClosed() {
        LatLon firstPolygonPoint = this.points.get(0);
        LatLon lastPolygonPoint = this.points.get(this.points.size()-1);
        double epsilon = 0.0000001;

        if(Math.abs(firstPolygonPoint.getLatitude() - lastPolygonPoint.getLatitude()) > epsilon){
            throw new CodeContractException("The polygon is not closed. Can not construct the polygon. Latitudes are different. ");
        }

        if(Math.abs(firstPolygonPoint.getLongitude() - lastPolygonPoint.getLongitude()) > epsilon){
            throw new CodeContractException("The polygon is not closed. Can not construct the polygon. Longitudes are different");
        }
    }
}
