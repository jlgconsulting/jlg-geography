package jlg.geography.test;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;
import jlg.geography.representation.wsg84.WktFormat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WktTransformTest {
    @Test
    public void should_transform_point_to_wkt_string(){
        //arrange
        double aLatitude = 10.123456;
        double aLongitude = 11.123456;
        Point aCoordinate = new Point(aLatitude,aLongitude);

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
        Line line = new Line(somePoints);

        //act
        String resultWkt = WktFormat.transform(line);

        //assert
        assertEquals("LINESTRING(2.0 1.0,4.0 3.0)", resultWkt);
    }

    @Test
    public void should_transform_polygon_to_wkt_string(){
        //arrange
        double[] someCoordinates = {10.123,11.123,12.123,13.123,10.123,11.123};
        Polygon polygon = new Polygon(someCoordinates);

        //act
        String result = WktFormat.transform(polygon);

        //assert
        String expectedResult = "POLYGON((11.123 10.123,13.123 12.123,11.123 10.123))";
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_transform_multi_polygon_to_wkt_string(){
        //arrange
        final double[] somePolygonCoordinates = new double[] {10.123, 11.123, 12.123, 13.123, 10.123, 11.123};
        final double[] someOtherPolygonCoordinates = new double[] {15.123, 15.123, 12.123, 13.123, 15.123, 15.123};
        double[][] multipolygonPoints = new double[][]{
                somePolygonCoordinates,
                someOtherPolygonCoordinates
        };
        MultiPolygon multiPolygon = new MultiPolygon(multipolygonPoints);

        //act
        String result = WktFormat.transform(multiPolygon);

        //assert
        String expectedWkt = "MULTIPOLYGON(((11.123 10.123,13.123 12.123,11.123 10.123)),((15.123 15.123,13.123 12.123,15.123 15.123)))";
        assertEquals(expectedWkt,result);
    }
}
