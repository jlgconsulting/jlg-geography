package jlg.geography.projection;

import jlg.geography.cartesian.Cartesian;
import jlg.geography.wsg84.LatLon;

import static jlg.codecontract.CodeContract.verifyNotNull;

/**
 * Class that given a center lat and lon, projects a cartesian point to a wsg84 point
 */
public class Wsg84Projection extends AbstractProjection<Cartesian,LatLon> {
    private LatLon center;

    /**
     * @param center the lat and lon for the (0,0) cartesian coordinate, from which all cartesian points
     *                         start
     */
    public Wsg84Projection(LatLon center){
        verifyNotNull(center);
        this.center = center;
    }

    /**
     * @param cartesian cartesian coordinates expressed in meters
     * @param altitude expressed in meters
     * @return
     */
    @Override
    public LatLon project(Cartesian cartesian, int altitude) {
        verifyNotNull(cartesian);

        double centerLatitudeDouble = center.getLatitude();
        double centerLongitudeDouble = center.getLongitude();
        double[] result = new double[2];

        double tLat, tLong;
        double u = 0.0;
        double x0, x1, x2;
        double y0, y1, y2;
        double z0, z1, z2;

        final double prefi = 0.000001;
        final double prealt = 0.01;

        double clat = 0.0; /* Dummy first estimate of latitude */
        double clong = 0.0; /* Dummy first estimate of Longitude */

        double[] vect = new double[3];
        double[] oa = new double[3];
        double du = -1000;
        double f;
        double f0 = 0.0;
        int itemp = 0;

        // Maximum Iterations to obtain required accuracys.
        // It is not possible for this to be exceeded but it should be trapped
        int ilimit = 1000;

        boolean iterate = true;
        char[] direction = new char[8];
        double calt = 0.0;
        int degrees, minutes;
        float seconds;
        boolean flag = true; /* Conversion Confidence Flag */

        double v = cartesian.getX();
        double w = cartesian.getY();
        double tLatIn = centerLatitudeDouble;
        double tLongIn = centerLongitudeDouble;

        double sgAxis = EARTH_MAJOR_AXIS_IN_METERS;
        if (Math.abs(sgAxis - 0.0) < 0.0000001)
            sgAxis = 6378137.0;

        double eccen = EARTH_ECCENTRICITY;
        if (Math.abs(eccen - 0.0) < 0.0000001)
            eccen = 0.081819;

            /* To radians */
        tLat = (tLatIn / 360.0) * 2 * Math.PI;

        tLong = (tLongIn / 360.0) * 2 * Math.PI;

        /* Convert to GEOCENTRIC STANDARD PROJECTION */
        double anorm = sgAxis / Math.sqrt(1.0 - (eccen * eccen) * (Math.sin(tLat) * Math.sin(tLat)));
        vect[0] = anorm * Math.cos(tLat) * Math.cos(tLong);
        vect[1] = anorm * Math.cos(tLat) * Math.sin(tLong);
        vect[2] = (anorm * (1.0 - (eccen * eccen))) * Math.sin(tLat);

        /* While accuracy is less than required ITERATE */
        do
        {
            itemp++;
            x2 = u;
            y2 = v;
            z2 = w;
            x1 = Math.cos(tLat) * x2 - Math.sin(tLat) * z2;
            y1 = y2;
            z1 = Math.sin(tLat) * x2 + Math.cos(tLat) * z2;
            x0 = Math.cos(tLong) * x1 - Math.sin(tLong) * y1;
            y0 = Math.sin(tLong) * x1 + Math.cos(tLong) * y1;
            z0 = z1;
            oa[0] = vect[0] + x0;
            oa[1] = vect[1] + y0;
            oa[2] = vect[2] + z0;
            ilimit--;

                /**/
            double p, diff, atest;
            double fi0, fi, h;

            p = Math.sqrt(oa[0] * oa[0] + oa[1] * oa[1]);

            if (Math.abs(oa[0] - 0.0) < 0.000001)
            {
                clong = Math.PI / 2;
                if (oa[1] < 0.0)
                    clong = Math.PI + clong;
            }
            else
            {
                clong = Math.atan(oa[1] / oa[0]);
                if (oa[0] < 0.0)
                    clong = clong + Math.PI;
            }

            // Estimate of longitude now fixed
            fi0 = Math.atan(oa[2] / p / (1.0 - sgAxis * (eccen * eccen) / Math.sqrt(p * p + oa[2] * oa[2])));

            clat = 0.0;

            atest = sgAxis * (1 - (eccen * eccen));

            do
            {
                anorm = sgAxis / Math.sqrt(1.0 - (eccen * eccen) * (Math.sin(fi0) * Math.sin(fi0)));
                h = p / Math.cos(fi0) - anorm;
                anorm = h * Math.sqrt(1.0 - (eccen * eccen) * (Math.sin(fi0) * Math.sin(fi0)));
                fi = Math.atan((oa[2] / p) * ((sgAxis + anorm) / (atest + anorm)));
                diff = Math.abs(fi - fi0);

                if (diff < prefi)
                {
                    clat = fi;
                    calt = h;
                }
                else
                {
                        /* next iteration differences still significant */
                    fi0 = fi;
                }
            } while (Math.abs(clat - 0.0) < 0.0000001);

            f = calt - altitude;
            if (itemp > 1 && Math.abs(f) >= Math.abs(f0))
            {
                du = -du / 2.0;
            }
            if (Math.abs(f) > prealt && ilimit > 0)
            {
                    /* height outside accuracy required reiterate */
                f0 = f;
                u += du;
            }
            else
            {
                    /* height within accuracy required STOP iterations (OR iteration limits reached) */
                iterate = false;
            }
        } while (iterate);

         /* Radians to Degrees */
        clat = (clat / (2 * Math.PI)) * 360.0;
        clong = (clong / (2 * Math.PI)) * 360.0;

        // round it to the 6 decimal point
        result[0] = round(clat);
        result[1] = round(clong);

        return new LatLon(result[0], result[1]);
    }
}
