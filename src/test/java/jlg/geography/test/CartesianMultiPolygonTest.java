package jlg.geography.test;


import jlg.geography.geometry.MultiPolygon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartesianMultiPolygonTest {
    @Test
    public void the_constructor_when_points_are_valid_should_build_multi_polygon(){
        //arrange
        final List<Double> somePolygonCoordinates = new ArrayList<>(Arrays.asList(10.0, 11.0, 12.0, 13.0, 10.0, 11.0));
        final List<Double> someOtherPolygonCoordinates = new ArrayList<>(Arrays.asList(15.0, 15.0, 12.0, 13.0, 15.0, 15.0));
        List<List<Double>> multipolygonPoints = new ArrayList<List<Double>>(){{
            add(somePolygonCoordinates);
            add(someOtherPolygonCoordinates);
        }};

        //act
        MultiPolygon multiPolygon = new MultiPolygon(multipolygonPoints);

        //assert
        int expectedPolygons = 2;
        assertEquals(expectedPolygons, multiPolygon.getPolygons().size());
    }
}
