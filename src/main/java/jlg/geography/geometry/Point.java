package jlg.geography.geometry;

import jlg.geography.Boundable;
import jlg.geography.GeometryFeature;
import jlg.geography.wsg84.LatitudeParser;
import jlg.geography.wsg84.LongitudeParser;

public class Point implements Boundable, GeometryFeature{


    private double latitude;
    private double longitude;

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @param geoLatitude  the latitude represented on 7 charachters as DDMMSSH
     * @param geoLongitude the longitude represented on 8 characters as DDMMSSH
     * DD - degrees
     * MM - minutes
     * SS - seconds
     * H/O - direction like N,S,E,W
     * @throws IllegalArgumentException if one of coordinates is not in correct geodetic format
     * @throws jlg.codecontract.CodeContractException if the coordinates are null
     * @throws jlg.codecontract.CodeContractException if the coordinates lenght is not valid (7 for lat, 8 for lon)
     */
    public Point(String geoLatitude, String geoLongitude) {
        this.latitude = LatitudeParser.fromGeographicNotation(geoLatitude);
        this.longitude = LongitudeParser.fromGeographicNotation(geoLongitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean isInBoundingBox(BoundingBox boundingBox) {
        return false;
    }
}
