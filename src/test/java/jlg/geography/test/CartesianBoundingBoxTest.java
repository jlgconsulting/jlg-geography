package jlg.geography.test;

import jlg.geography.geometry.BoundingBox;
import jlg.geography.geometry.Point;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CartesianBoundingBoxTest {
    @Test
    public void the_constructor_with_coordinate_args_should_build_correct_bounding_box(){
        //arrange
        Point minCoordinate = new Point(1,2);
        Point maxCoordinate = new Point(3,4);

        //act
        BoundingBox boundingBox = new BoundingBox(minCoordinate, maxCoordinate);

        //assert
        double delta = 0.5;
        assertEquals(1, boundingBox.getMinCoordinate().getLongitude(), delta);
        assertEquals(2, boundingBox.getMinCoordinate().getLatitude(), delta);
        assertEquals(3, boundingBox.getMaxCoordinate().getLongitude(), delta);
        assertEquals(4, boundingBox.getMaxCoordinate().getLatitude(), delta);
        assertEquals(2, boundingBox.getCenterCoordinate().getLongitude(), delta);
        assertEquals(3, boundingBox.getCenterCoordinate().getLatitude(), delta);
    }

    @Test
    public void the_constructor_with_list_of_bounding_boxes_should_build_correct_bounding_box(){
        //arrange
        BoundingBox firstBox = new BoundingBox(new Point(1,1), new Point(5,5));
        BoundingBox secondBox = new BoundingBox(new Point(3,3), new Point(7,7));

        //act
        BoundingBox boundingBox = new BoundingBox(Arrays.asList(firstBox,secondBox));

        //assert
        double delta = 0.5;
        assertEquals(1, boundingBox.getMinCoordinate().getLongitude(), delta);
        assertEquals(1, boundingBox.getMinCoordinate().getLatitude(), delta);
        assertEquals(7, boundingBox.getMaxCoordinate().getLongitude(), delta);
        assertEquals(7, boundingBox.getMaxCoordinate().getLatitude(), delta);
        assertEquals(4, boundingBox.getCenterCoordinate().getLongitude(), delta);
        assertEquals(4, boundingBox.getCenterCoordinate().getLatitude(), delta);
    }

    @Test
    public void the_contains_method_when_point_is_inside_bounding_box_should_return_true() {
        //arrange
        Point minPoint = new Point(-100, -100);
        Point maxPoint = new Point(100, 100);
        BoundingBox box = new BoundingBox(minPoint, maxPoint);
        Point aPointInsideBoundingBox = new Point(-50, -50);

        //act
        boolean result = box.contains(aPointInsideBoundingBox);

        //assert
        boolean expectedResult = true;

        assertEquals(expectedResult,result);
    }

    @Test
    public void the_contains_method_when_point_is_outside_bounding_box_should_return_false() {
        //arrange
        Point minPoint = new Point(-100, -100);
        Point maxPoint = new Point(100, 100);
        BoundingBox box = new BoundingBox(minPoint, maxPoint);
        Point aPointInsideBoundingBox = new Point(500, 500);

        //act
        boolean result = box.contains(aPointInsideBoundingBox);

        //assert
        boolean expectedResult = false;
        assertEquals(expectedResult,result);
    }
}
