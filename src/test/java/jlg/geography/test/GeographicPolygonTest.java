package jlg.geography.test;

import jlg.codecontract.CodeContractException;
import jlg.geography.geometry.BoundingBox;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GeographicPolygonTest {
    @Test
    public void the_constructor_with_points_should_build_polygon() {
        //arrange
        Point[] points = {
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 1)
        };

        //act
        Polygon polygon = new Polygon(points);

        //assert
        assertEquals(3, polygon.getPoints().size());
    }

    @Test(expected = CodeContractException.class)
    public void the_constructor_with_points_that_do_no_close_should_throw() {
        //arrange
        Point[] points = {
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3)
        };

        //act
        Polygon polygon = new Polygon(points);
    }

    @Test
    public void the_constructor_with_coordinates_should_build_polygon() {
        //arrange
        double[] coordinates = {1, 1, 2, 2, 1, 1};

        //act
        Polygon polygon = new Polygon(coordinates);

        //assert
        assertEquals(3, polygon.getPoints().size());
    }

    @Test(expected = CodeContractException.class)
    public void the_constructor_with_coordinates_that_do_not_close_should_throw() {
        //arrange
        double[] coordinates = {1, 1, 2, 2, 3, 3};

        //act
        Polygon polygon = new Polygon(coordinates);
    }

    @Test
    public void the_get_bounding_box_should_return_correct_bounding_box() {
        //arrange
        double[] coordinates = {1, 1, 2, 2, 1, 1};

        //act
        Polygon polygon = new Polygon(coordinates);

        //assert
        BoundingBox boundingBox = polygon.getBoundingBox();
        assertEquals(1, boundingBox.getMinCoordinate().getLatitude(),0.000001);
        assertEquals(1, boundingBox.getMinCoordinate().getLongitude(),0.000001);
        assertEquals(2, boundingBox.getMaxCoordinate().getLatitude(),0.000001);
        assertEquals(2, boundingBox.getMaxCoordinate().getLongitude(),0.000001);
    }

    @Test
    public void the_contains_point_method_when_point_inside_should_return_true() {
        //arrange
        Point[] points = {
                new Point(1,1),
                new Point(4,1),
                new Point(4,3),
                new Point(1,3),
                new Point(1,1)
        };

        Polygon polygon = new Polygon(points);
        Point pointInside = new Point(2,2);

        //act
        boolean result = polygon.contains(pointInside);

        //assert
        assertTrue(result);
    }

    @Test
    public void the_contains_point_method_when_point_outside_should_return_false() {
        //arrange
        Point[] points = {
                new Point(1,1),
                new Point(4,1),
                new Point(4,3),
                new Point(1,3),
                new Point(1,1)
        };

        Polygon polygon = new Polygon(points);
        Point pointOutside = new Point(4,4);

        //act
        boolean result = polygon.contains(pointOutside);

        //assert
        assertFalse(result);
    }
}
