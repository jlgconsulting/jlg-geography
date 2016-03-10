package jlg.geography.test;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;
import jlg.geography.representation.ats.AtsFormat;
import jlg.geography.representation.ats.AtsPoint;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class AtsFormatTest {


    @Test
    public void transform_point_returns_correct_object_format() {
        // arrange
        double latitude = 44.4;
        double longitude = 23.6;
        Point point = new Point(latitude, longitude);

        // act
        AtsPoint[][] atsPoint = AtsFormat.transform(point);

        // assert
        double delta = 0.001;
        assertNotNull("ats point is null!", atsPoint);
        assertEquals("Latitude numbers don't correspond", latitude, atsPoint[0][0].getY(), delta);
        assertEquals("Longitude numbers don't correspond", longitude, atsPoint[0][0].getX(), delta);
    }

    @Test
    public void transform_line_returns_correct_object() {
        // arrange
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);
        Point p4 = new Point(7, 8);
        Point[] points =  new Point[]{ p1, p2, p3, p4};
        Line line = new Line(points);

        // act
        AtsPoint[][] atsLine = AtsFormat.transform(line);

        // assert
        int expectedNumberOfFragments = 3;
        assertEquals("Incorrect number of fragments", expectedNumberOfFragments, atsLine.length);
        assertEquals("Incorect size of each segment", atsLine[0].length, 2);
        assertEquals("Incorrect object", p4.getLatitude(), atsLine[2][1].getY(), 0.01);
    }

    @Test
    public void transform_polygon_returns_correct_object() {
        //arrange
        Point[] points = {
                new Point(1,1),
                new Point(4,1),
                new Point(4,3),
                new Point(1,3),
                new Point(1,1)
        };
        Polygon polygon = new Polygon(points);

        // act
        AtsPoint[][] atsPolygon = AtsFormat.transform(polygon);

        // assert
        int expectedNumberOfPoints = 4;
        assertEquals("Incorrect number of points", expectedNumberOfPoints, atsPolygon[0].length);
        assertEquals("Incorect last coordinate", atsPolygon[0][3].getX(), 3, 0.001);
        assertEquals("Incorect last coordinate", atsPolygon[0][3].getY(), 1, 0.001);
    }

    @Test
    public void transform_multipolygon_returns_correct_object() {
        //arrange
        final double[] firstPolygonCoordinates = new double[] {10.123, 11.123, 12.123, 13.123, 14.0, 5.0, 24.0, 6.0, 10.123, 11.123};
        final double[] secondOtherPolygonCoordinates = new double[] {15.123, 15.123,10.0, 11.0, 12.123, 13.123, 15.123, 15.123};
        final double[] thirdOtherPolygonCoordinates = new double[] {15.123, 15.123, 5.0, 5.0, 18.0, 10.0, 12.123, 13.123, 15.123, 15.123};
        double[][] multipolygonPoints = new double[][]{
                firstPolygonCoordinates,
                secondOtherPolygonCoordinates,
                thirdOtherPolygonCoordinates
        };
        MultiPolygon multiPolygon = new MultiPolygon(multipolygonPoints);

        // act
        AtsPoint[][] atsMultiPolygon = AtsFormat.transform(multiPolygon);

        // assert
        int expectedNumberOfPolygons = 3;
        assertEquals("Incorrect number of polygons", expectedNumberOfPolygons, atsMultiPolygon.length);
        assertEquals("Incorrect lenght of internal polygon", 4, atsMultiPolygon[0].length);
        assertNotEquals("Last point should not repeat", 10.123, atsMultiPolygon[0][3].getY(), 0.001);
    }

    @Test
    public void transform_multipolygon_returns_correct_object_secont_test() {
        //arrange
        final double[] firstPolygonCoordinates = new double[] {10.123, 11.123, 12.123, 13.123, 14.0, 5.0, 24.0, 6.0, 10.123, 11.123};
        final double[] secondOtherPolygonCoordinates = new double[] {15.123, 15.123,10.0, 11.0, 12.123, 13.123, 15.123, 15.123};
        final double[] thirdOtherPolygonCoordinates = new double[] {15.123, 15.123, 5.0, 5.0, 18.0, 10.0, 12.123, 13.123, 15.123, 15.123};
        double[][] multipolygonPoints = new double[][]{
                firstPolygonCoordinates,
                secondOtherPolygonCoordinates,
                thirdOtherPolygonCoordinates
        };
        MultiPolygon multiPolygon = new MultiPolygon(multipolygonPoints);

        // act
        AtsPoint[][] atsMultiPolygon = AtsFormat.transform(multiPolygon);

        // assert
        int expectedNumberOfPolygons = 3;
        assertEquals("Incorrect number of polygons", expectedNumberOfPolygons, atsMultiPolygon.length);
        assertEquals("Incorrect lenght of internal polygon", 3, atsMultiPolygon[1].length);
        assertNotEquals("Last point should not repeat", 15.123, atsMultiPolygon[1][2].getY(), 0.001);
    }
}
