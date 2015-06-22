package jlg.geography.test;

import jlg.geography.geometry.BoundingBox;
import jlg.geography.geometry.Point;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BoundingBoxTest {
    @Test
    public void the_constructor_with_coordinate_args_should_build_correct_bounding_box(){
        //arrange
        Point minCoordinate = new Point(1,2);
        Point maxCoordinate = new Point(3,4);

        //act
        BoundingBox boundingBox = new BoundingBox(minCoordinate, maxCoordinate);

        //assert
        assertEquals("Min coordinate latitude incorect", 1, boundingBox.getMinCoordinate().getLatitude(), 0.000001);
        assertEquals("Min coordinate longitude incorect", 2, boundingBox.getMinCoordinate().getLongitude(), 0.000001);
        assertEquals("Max coordinate latitude incorect", 3, boundingBox.getMaxCoordinate().getLatitude(), 0.000001);
        assertEquals("Max coordinate longitude incorect", 4, boundingBox.getMaxCoordinate().getLongitude(), 0.000001);
        assertEquals("Center coordinate latitude incorect", 2, boundingBox.getCenterCoordinate().getLatitude(), 0.000001);
        assertEquals("Center coordinate longitude incorect", 3, boundingBox.getCenterCoordinate().getLongitude(), 0.000001);
    }

    @Test
    public void the_constructor_with_list_of_bounding_boxes_should_build_correct_bounding_box(){
        //arrange
        BoundingBox firstBox = new BoundingBox(new Point(1,1), new Point(5,5));
        BoundingBox secondBox = new BoundingBox(new Point(3,3), new Point(7,7));

        //act
        BoundingBox boundingBox = new BoundingBox(Arrays.asList(firstBox, secondBox));

        //assert
        assertEquals(1, boundingBox.getMinCoordinate().getLatitude(), 0.000001);
        assertEquals(1, boundingBox.getMinCoordinate().getLongitude(), 0.000001);
        assertEquals(7, boundingBox.getMaxCoordinate().getLatitude(), 0.000001);
        assertEquals(7, boundingBox.getMaxCoordinate().getLongitude(), 0.000001);
        assertEquals(4, boundingBox.getCenterCoordinate().getLatitude(), 0.000001);
        assertEquals(4, boundingBox.getCenterCoordinate().getLongitude(), 0.000001);
    }

    @Test
    public void the_contains_method_when_point_is_inside_bounding_box_should_return_true() {
        //arrange
        Point minPoint = new Point(-10, -10);
        Point maxPoint = new Point(10, 10);
        BoundingBox box = new BoundingBox(minPoint, maxPoint);
        Point aPointInsideBoundingBox = new Point(-5, 5);

        //act
        boolean result = box.contains(aPointInsideBoundingBox);

        //assert
        boolean expectedResult = true;

        assertEquals(expectedResult,result);
    }

    @Test
    public void the_contains_method_when_point_is_outside_bounding_box_should_return_false() {
        //arrange
        Point minPoint = new Point(-10, -10);
        Point maxPoint = new Point(10, 10);
        BoundingBox box = new BoundingBox(minPoint, maxPoint);
        Point aPointOutsideBoundingBox = new Point(11, 11);

        //act
        boolean result = box.contains(aPointOutsideBoundingBox);

        //assert
        boolean expectedResult = false;

        assertEquals(expectedResult,result);
    }
}
