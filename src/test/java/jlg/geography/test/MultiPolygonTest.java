package jlg.geography.test;

import jlg.geography.geometry.MultiPolygon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MultiPolygonTest {
    @Test
    public void the_constructor_when_points_are_valid_should_build_multi_polygon(){
        //arrange
        final double[] somePolygonCoordinates = new double[] {10.123, 11.123, 12.123, 13.123, 10.123, 11.123};
        final double[] someOtherPolygonCoordinates = new double[] {15.123, 15.123, 12.123, 13.123, 15.123, 15.123};
        double[][] multipolygonPoints = new double[][]{
            somePolygonCoordinates,
            someOtherPolygonCoordinates
        };

        //act
        MultiPolygon multiPolygon = new MultiPolygon(multipolygonPoints);

        //assert
        int expectedPolygons = 2;
        assertEquals(expectedPolygons, multiPolygon.getPolygons().size());
    }
}
