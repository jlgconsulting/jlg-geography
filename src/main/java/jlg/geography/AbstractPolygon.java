package jlg.geography;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotNull;
import static jlg.codecontract.CodeContract.verifyThat;

public abstract class AbstractPolygon<TPoint> {
    protected List<TPoint> points;

    protected AbstractPolygon(){
        this.points = new ArrayList<>();
    }

    protected AbstractPolygon(TPoint[] points){
        verifyNotNull(points);
        verifyThat(points.length >= 3, "Polygon needs at least 3 points.");

        this.points = Arrays.asList(points);
    }

    public abstract BoundingBox<TPoint> getBoundingBox();

    public List<TPoint> getPoints() {
        return points;
    }

    public abstract boolean contains(TPoint point);
}
