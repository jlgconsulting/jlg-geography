package jlg.geography.projection;

import jlg.geography.wsg84.LatLon;

import java.math.BigDecimal;

import static jlg.codecontract.CodeContract.verifyNotNull;

/**
 * Abstract class that needs to be implemented by all projection classes
 */
public abstract class AbstractProjection<TPoint,TProjectedPoint> {
    protected final double DEGREES_TO_RADIANS = Math.PI / 180;
    protected final double EARTH_ECCENTRICITY = 0.081819;
    protected final double EARTH_MAJOR_AXIS_IN_METERS = 6378137.0;

    public abstract TProjectedPoint project(TPoint point);

    protected double round(double value){
        BigDecimal b = new BigDecimal(value);
        b = b.setScale(2,BigDecimal.ROUND_UP);
        return (b.doubleValue());
    }

}
