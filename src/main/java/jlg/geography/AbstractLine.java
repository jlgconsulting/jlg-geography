package jlg.geography;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotNull;
import static jlg.codecontract.CodeContract.verifyThat;

public abstract class AbstractLine<TPoint> {
    protected List<TPoint> points;

    protected AbstractLine(){
        this.points = new ArrayList<>();
    }

    /**
     * @param points a list of points (min 2) that will make up the line
     */
    public AbstractLine(TPoint[] points){
        verifyNotNull(points);
        verifyThat(points.length >= 2);

        this.points = Arrays.asList(points);
    }

    public List<TPoint> getPoints() {
        return points;
    }
}
