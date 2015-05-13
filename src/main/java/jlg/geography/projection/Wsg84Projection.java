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

    @Override
    public LatLon project(Cartesian cartesian) {
        return null;
    }
}
