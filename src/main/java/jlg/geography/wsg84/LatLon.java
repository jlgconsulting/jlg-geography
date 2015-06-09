package jlg.geography.wsg84;

import jlg.geography.Boundable;
import jlg.geography.BoundingBox;
import jlg.geography.GeometryFeature;

import static jlg.codecontract.CodeContract.verifyBetween;

/**
 * Represents a set of geographic coordinates expressed as wsg84 latitude and longitude
 */
public class LatLon implements Boundable<LatLon>, GeometryFeature {
    private double latitude;
    private double longitude;

    /**
     * @param wsg84Latitude  the latitude coordinate in decimal (between -90 and 90)
     * @param wsg84Longitude the longitude coordinate in decimal (between -180 and 180)
     * @throws jlg.codecontract.CodeContractException if the latitude and longitude are not within limits
     */
    public LatLon(double wsg84Latitude, double wsg84Longitude) {
        verifyBetween(wsg84Latitude, -90, 90);
        verifyBetween(wsg84Longitude, -180, 180);

        this.latitude = wsg84Latitude;
        this.longitude = wsg84Longitude;
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
    public LatLon(String geoLatitude, String geoLongitude) {
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
    public boolean isInBoundingBox(BoundingBox<LatLon> boundingBox) {
        return boundingBox.contains(this);
    }
}
