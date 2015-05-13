package jlg.geography.test;

import jlg.codecontract.CodeContractException;
import jlg.geography.BoundingBox;
import jlg.geography.cartesian.Cartesian;
import jlg.geography.cartesian.CartesianBoundingBox;
import jlg.geography.cartesian.CartesianPolygon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CartesianPolygonTest {

    @Test
    public void the_constructor_with_points_should_build_polygon() {
        //arrange
        Cartesian[] points = {
                new Cartesian(1, 1),
                new Cartesian(2, 2),
                new Cartesian(1, 1)
        };

        //act
        CartesianPolygon polygon = new CartesianPolygon(points);

        //assert
        assertEquals(3, polygon.getPoints().size());
    }

    @Test(expected = CodeContractException.class)
    public void the_constructor_with_points_that_do_no_close_should_throw() {
        //arrange
        Cartesian[] points = {
                new Cartesian(1, 1),
                new Cartesian(2, 2),
                new Cartesian(3, 3)
        };

        //act
        CartesianPolygon polygon = new CartesianPolygon(points);
    }

    @Test
    public void the_constructor_with_coordinates_should_build_polygon() {
        //arrange
        int[] coordinates = {1, 1, 2, 2, 1, 1};

        //act
        CartesianPolygon polygon = new CartesianPolygon(coordinates);

        //assert
        assertEquals(3, polygon.getPoints().size());
    }

    @Test(expected = CodeContractException.class)
    public void the_constructor_with_coordinates_that_do_not_close_should_throw() {
        //arrange
        int[] coordinates = {1, 1, 2, 2, 3, 3};

        //act
        CartesianPolygon polygon = new CartesianPolygon(coordinates);
    }

    @Test
    public void the_get_bounding_box_should_return_correct_bounding_box() {
        //arrange
        int[] coordinates = {1, 1, 2, 2, 1, 1};

        //act
        CartesianPolygon polygon = new CartesianPolygon(coordinates);

        //assert
        BoundingBox<Cartesian> boundingBox = polygon.getBoundingBox();
        assertEquals(1, boundingBox.getMinCoordinate().getX());
        assertEquals(1, boundingBox.getMinCoordinate().getY());
        assertEquals(2, boundingBox.getMaxCoordinate().getX());
        assertEquals(2, boundingBox.getMaxCoordinate().getY());
    }

    @Test
    public void the_contains_point_method_when_point_inside_should_return_true() {
        //arrange
        Cartesian[] points = {
                new Cartesian(1,1),
                new Cartesian(4,1),
                new Cartesian(4,3),
                new Cartesian(1,3),
                new Cartesian(1,1)
        };

        CartesianPolygon polygon = new CartesianPolygon(points);
        Cartesian pointInside = new Cartesian(2,2);

        //act
        boolean result = polygon.contains(pointInside);

        //assert
        assertTrue(result);
    }

    @Test
    public void the_contains_point_method_when_point_outside_should_return_false() {
        //arrange
        Cartesian[] points = {
                new Cartesian(1,1),
                new Cartesian(4,1),
                new Cartesian(4,3),
                new Cartesian(1,3),
                new Cartesian(1,1)
        };

        CartesianPolygon polygon = new CartesianPolygon(points);
        Cartesian pointOutside = new Cartesian(4,4);

        //act
        boolean result = polygon.contains(pointOutside);

        //assert
        assertFalse(result);
    }
}
