package jlg.geography.test;

import jlg.geography.Ats.AtsFormat;
import jlg.geography.Ats.AtsPoint;
import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        AtsPoint atsPoint = formatter.transformPoint(point);

        // assert
        double delta = 0.001;
        assertNotNull("Ats point is null!", atsPoint);
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
        AtsPoint[][] atsLine = formatter.transformLine(line);

        // assert
        int expenctedNumberOfPoints = 7;
        assertEquals("Incorrect number of points", expenctedNumberOfPoints, atsLine[0].length);
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
        AtsPoint[][] atsPolygon = formatter.transformPolygon(polygon);

        // assert
        int expenctedNumberOfPoints = 5;
        assertEquals("Incorrect number of points", expenctedNumberOfPoints, atsPolygon[0].length);
        assertEquals("First point is not same with last", atsPolygon[0][0].getX(), atsPolygon[0][4].getX(), 0.001);
    }

    @Test
    public void transform_multipolygon_returns_correct_object() {
        //arrange
        final List<Double> somePolygonCoordinates = new ArrayList<Double>(Arrays.asList(10.123, 11.123, 12.123, 13.123, 10.123, 11.123));
        final List<Double> someOtherPolygonCoordinates = new ArrayList<>(Arrays.asList(15.123, 15.123, 12.123, 13.123, 15.123, 15.123));
        List<List<Double>> multipolygonPoints = new ArrayList<List<Double>>(){{
            add(somePolygonCoordinates);add(someOtherPolygonCoordinates);
        }};
        MultiPolygon multiPolygon = new MultiPolygon(multipolygonPoints);

        // act
        AtsPoint[][] atsMultiPolygon = formatter.transformMultiPolygon(multiPolygon);

        // assert
        int expedtedNumberOfPolygons = 2;
        assertEquals("Incorrect number of polygons", expedtedNumberOfPolygons, atsMultiPolygon.length);
        assertEquals("Incorrect lenght of internal polygon", 3, atsMultiPolygon[1].length);
        assertEquals("Incorrect corespondent", 10.123, atsMultiPolygon[0][2].getY(), 0.001);
    }
}
