package jlg.geography.geometry;

import jlg.geography.GeometryFeature;
import jlg.geography.representation.wsg84.LatitudeParser;
import jlg.geography.representation.wsg84.LongitudeParser;

import static jlg.codecontract.CodeContract.verifyBetween;

public class Point implements GeometryFeature{


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

    public boolean isInBoundingBox(BoundingBox boundingBox) {
        return boundingBox.contains(this);
    }

    public static Point buildFromWsg84Coordinates(double latitude, double longitude){
        verifyBetween(latitude, -90, 90);
        verifyBetween(longitude, -180, 180);

        return new Point(latitude, longitude);
    }
}
