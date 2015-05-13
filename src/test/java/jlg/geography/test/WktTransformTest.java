package jlg.geography.test;

import jlg.geography.wsg84.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dan-geabunea on 5/13/2015.
 */
public class WktTransformTest {
    @Test
    public void should_transform_point_to_wkt_string(){
        //arrange
        double aLatitude = 10.123456;
        double aLongitude = 11.123456;
        LatLon aCoordinate = new LatLon(aLatitude,aLongitude);

        //act
        String result = WktFormat.transform(aCoordinate);

        //assert
        String expectedResult = "POINT(11.123456 10.123456)";
        assertEquals(expectedResult,result);
    }

    @Test
    public void should_transform_line_to_wkt_string(){
        //arrange
        double[] somePoints = {1.0, 2.0, 3.0, 4.0};
        GeographicLine line = new GeographicLine(somePoints);

        //act
        String resultWkt = WktFormat.transform(line);

        //assert
        assertEquals("LINESTRING(2.0 1.0,4.0 3.0)", resultWkt);
    }

    @Test
    public void should_transform_polygon_to_wkt_string(){
        //arrange
        double[] someCoordinates = {10.123,11.123,12.123,13.123,10.123,11.123};
        GeographicPolygon polygon = new GeographicPolygon(someCoordinates);

        //act
        String result = WktFormat.transform(polygon);

        //assert
        String expectedResult = "POLYGON((11.123 10.123,13.123 12.123,11.123 10.123))";
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_transform_multi_polygon_to_wkt_string(){
        //arrange
        final List<Double> somePolygonCoordinates = new ArrayList<Double>(Arrays.asList(10.123, 11.123, 12.123, 13.123, 10.123,11.123));
        final List<Double> someOtherPolygonCoordinates = new ArrayList<Double>(Arrays.asList(15.123, 15.123, 12.123, 13.123, 15.123,15.123));
        List<List<Double>> multipolygonPoints = new ArrayList<List<Double>>(){{
            add(somePolygonCoordinates);add(someOtherPolygonCoordinates);
        }};
        GeographicMultiPolygon multiPolygon = new GeographicMultiPolygon(multipolygonPoints);

        //act
        String result = WktFormat.transform(multiPolygon);

        //assert
        String expectedWkt = "MULTIPOLYGON(((11.123 10.123,13.123 12.123,11.123 10.123)),((15.123 15.123,13.123 12.123,15.123 15.123)))";
        assertEquals(expectedWkt,result);
    }
}
