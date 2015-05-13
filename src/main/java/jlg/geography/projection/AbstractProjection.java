package jlg.geography.projection;

import java.math.BigDecimal;

/**
 * Abstract class that needs to be implemented by all projection classes
 */
public abstract class AbstractProjection<TPoint,TProjectedPoint> {
    protected final double DEGREES_TO_RADIANS = Math.PI / 180;
    protected final double EARTH_ECCENTRICITY = 0.081819;
    protected final double EARTH_MAJOR_AXIS_IN_METERS = 6378137.0;

    public TProjectedPoint project(TPoint point){
        return project(point,0);
    }

    public abstract TProjectedPoint project(TPoint point, int altitude);

    protected double round(double value){
        BigDecimal b = new BigDecimal(value);
        b = b.setScale(6,BigDecimal.ROUND_UP);
        return (b.doubleValue());
    }

}
