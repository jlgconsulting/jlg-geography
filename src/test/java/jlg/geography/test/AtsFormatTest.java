package jlg.geography.test;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;
import jlg.geography.representation.ats.AtsFormat;
import jlg.geography.representation.ats.AtsPoint;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AtsFormatTest {

    private AtsFormat formatter;

    @Before
    public void init() {
        formatter = new AtsFormat();
    }

    @Test
    public void transform_point_returns_correct_object_format() {
        // arrange
        double latitude = 44.4;
        double longitude = 23.6;
        Point point = new Point(latitude, longitude);

        // act
        AtsPoint atsPoint = formatter.transform(point);

        // assert
        double delta = 0.001;
        assertNotNull("ats point is null!", atsPoint);
        assertEquals("Latitude numbers don't correspond", latitude, atsPoint.getY(), delta);
        assertEquals("Longitude numbers don't correspond", longitude, atsPoint.getX(), delta);
    }

    @Test
    public void transform_line_returs_correct_object() {
        // arrange
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);
        Point p4 = new Point(7, 8);
        Point[] points =  new Point[]{ p1, p2, p3, p4};
        Line line = new Line(points);

        // act
        AtsPoint[][] atsLine = formatter.transform(line);

        // assert
        int expectedNumberOfPoints = 7;
        assertEquals("Incorrect number of points", expectedNumberOfPoints, atsLine[0].length);
        assertEquals("First point is not same with last", atsLine[0][0].getX(), atsLine[0][6].getX(), 0.001);
        assertEquals("Incorrect object", p3.getLatitude(), atsLine[0][4].getY(), 0.01);
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
        AtsPoint[][] atsPolygon = formatter.transform(polygon);

        // assert
        int expectedNumberOfPoints = 5;
        assertEquals("Incorrect number of points", expectedNumberOfPoints, atsPolygon[0].length);
        assertEquals("First point is not same with last", atsPolygon[0][0].getX(), atsPolygon[0][4].getX(), 0.001);
    }

    @Test
    public void transform_multipolygon_returns_correct_object() {
        //arrange
        final double[] somePolygonCoordinates = new double[] {10.123, 11.123, 12.123, 13.123, 10.123, 11.123};
        final double[] someOtherPolygonCoordinates = new double[] {15.123, 15.123, 12.123, 13.123, 15.123, 15.123};
        double[][] multipolygonPoints = new double[][]{
                somePolygonCoordinates,
                someOtherPolygonCoordinates
        };
        MultiPolygon multiPolygon = new MultiPolygon(multipolygonPoints);

        // act
        AtsPoint[][] atsMultiPolygon = formatter.transformMulti(multiPolygon);

        // assert
        int expectedNumberOfPolygons = 2;
        assertEquals("Incorrect number of polygons", expectedNumberOfPolygons, atsMultiPolygon.length);
        assertEquals("Incorrect lenght of internal polygon", 3, atsMultiPolygon[1].length);
        assertEquals("Incorrect corespondent", 10.123, atsMultiPolygon[0][2].getY(), 0.001);
    }
}
