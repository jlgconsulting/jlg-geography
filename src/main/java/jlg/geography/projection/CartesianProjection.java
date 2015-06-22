package jlg.geography.projection;

import jlg.geography.geometry.Point;


import static jlg.codecontract.CodeContract.verifyNotNull;

/**
 * Class that given a center lat and lon, projects a wsg84 coordinate to a cartesian point
 */
public class CartesianProjection extends AbstractProjection {

    private Point center;

    /**
     * @param center the lat and lon for the (0,0) cartesian coordinate, from which all cartesian points
     *               start
     */
    public CartesianProjection(Point center) {
        verifyNotNull(center);
        this.center = center;
    }

    @Override
    public Point project(Point latLon, int altitude) {
        double[] result = new double[2];
        double rp_sqe;
        double rp_b;
        double rp_dk;
        double rp_sk;
        double rp_ck;
        double rp_sp;
        double rp_cp;
        double dki;
        double ski;
        double cki;
        double spi;
        double cpi;
        double sli;
        double cli;
        double rhinm;
        double x;
        double y;

        double rlat = latLon.getLatitude() * DEGREES_TO_RADIANS;
        double rlong = latLon.getLongitude() * DEGREES_TO_RADIANS;
        double radius = EARTH_MAJOR_AXIS_IN_METERS;
        double eccentricitySquared = EARTH_ECCENTRICITY * EARTH_ECCENTRICITY;
        double centreLatitude = center.getLatitude();
        double centreLongitude = center.getLongitude();
        centreLatitude = centreLatitude * DEGREES_TO_RADIANS;
        centreLongitude = centreLongitude * DEGREES_TO_RADIANS;

        rp_sqe = Math.sqrt(1 - eccentricitySquared);
        rp_b = radius * rp_sqe;

        rp_dk = Math.atan(rp_sqe * Math.tan(centreLatitude));
        rp_sk = Math.sin(rp_dk);
        rp_ck = Math.cos(rp_dk);
        rp_sp = Math.sin(centreLatitude);
        rp_cp = Math.cos(centreLatitude);

        dki = Math.atan(rp_sqe * Math.tan(rlat));
        ski = Math.sin(dki);
        cki = Math.cos(dki);
        spi = Math.sin(rlat);
        cpi = Math.cos(rlat);
        sli = Math.sin(rlong - centreLongitude);
        cli = Math.cos(rlong - centreLongitude);

        //Can change altitude if required
        rhinm = altitude;

        x = (radius * cki + rhinm * cpi) * sli;
        y = (rp_b * (ski - rp_sk) + rhinm * spi) * rp_cp +
                (radius * (rp_ck - cli * cki) - rhinm * cli * cpi) * rp_sp;

        // convert the result to number of units of half meters
        result[0] = (round(x * 2) / 2) * 2;
        result[1] = (round(y * 2) / 2) * 2;

        return new Point((int)result[0], (int)result[1]);
    }
}
