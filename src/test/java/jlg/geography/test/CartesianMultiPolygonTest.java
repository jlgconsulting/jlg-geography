package jlg.geography.test;


import jlg.geography.cartesian.CartesianMultiPolygon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartesianMultiPolygonTest {
    @Test
    public void the_constructor_when_points_are_valid_should_build_multi_polygon(){
        //arrange
        final List<Integer> somePolygonCoordinates = new ArrayList<>(Arrays.asList(10, 11, 12, 13, 10, 11));
        final List<Integer> someOtherPolygonCoordinates = new ArrayList<>(Arrays.asList(15, 15, 12, 13, 15, 15));
        List<List<Integer>> multipolygonPoints = new ArrayList<List<Integer>>(){{
            add(somePolygonCoordinates);add(someOtherPolygonCoordinates);
        }};

        //act
        CartesianMultiPolygon multiPolygon = new CartesianMultiPolygon(multipolygonPoints);

        //assert
        int expectedPolygons = 2;
        assertEquals(expectedPolygons, multiPolygon.getPolygons().size());
    }
}
