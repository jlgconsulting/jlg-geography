package jlg.geography;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyNotNull;
import static jlg.codecontract.CodeContract.verifyThat;

/**
 * Represents a geographic complex polygon, composed by multiple sub-polygons. It is used to build more complex
 * geographical representations
 */
public abstract class AbstractMultiPolygon<TPolygon extends AbstractPolygon<TPoint>,TPoint>  {
    protected List<TPolygon> polygons;

    protected AbstractMultiPolygon(){
        this.polygons = new ArrayList<>();
    }

    public AbstractMultiPolygon(TPolygon[] polygons){
        verifyNotNull(polygons);
        verifyThat(polygons.length > 0);

        this.polygons = Arrays.asList(polygons);
    }

    public List<TPolygon> getPolygons() {
        return polygons;
    }

    public abstract List<TPoint> getPoints();

    public boolean contains(TPoint point){
        return polygons.stream().anyMatch(x -> x.contains(point));
    }
}
