package jlg.geography.test;

import jlg.geography.Cartesian;
import jlg.geography.CartesianBoundingBox;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CartesianBoundingBoxTest {
    @Test
    public void the_constructor_with_coordinate_args_should_build_correct_bounding_box(){
        //arrange
        Cartesian minCoordinate = new Cartesian(1,2);
        Cartesian maxCoordinate = new Cartesian(3,4);

        //act
        CartesianBoundingBox boundingBox = new CartesianBoundingBox(minCoordinate, maxCoordinate);

        //assert
        assertEquals(1, boundingBox.getMinCoordinate().getX());
        assertEquals(2, boundingBox.getMinCoordinate().getY());
        assertEquals(3, boundingBox.getMaxCoordinate().getX());
        assertEquals(4, boundingBox.getMaxCoordinate().getY());
        assertEquals(2, boundingBox.getCenterCoordinate().getX());
        assertEquals(3, boundingBox.getCenterCoordinate().getY());
    }

    @Test
    public void the_constructor_with_list_of_bounding_boxes_should_build_correct_bounding_box(){
        //arrange
        CartesianBoundingBox firstBox = new CartesianBoundingBox(new Cartesian(1,1), new Cartesian(5,5));
        CartesianBoundingBox secondBox = new CartesianBoundingBox(new Cartesian(3,3), new Cartesian(7,7));

        //act
        CartesianBoundingBox boundingBox = new CartesianBoundingBox(Arrays.asList(firstBox,secondBox));

        //assert
        assertEquals(1, boundingBox.getMinCoordinate().getX());
        assertEquals(1, boundingBox.getMinCoordinate().getY());
        assertEquals(7, boundingBox.getMaxCoordinate().getX());
        assertEquals(7, boundingBox.getMaxCoordinate().getY());
        assertEquals(4, boundingBox.getCenterCoordinate().getX());
        assertEquals(4, boundingBox.getCenterCoordinate().getY());
    }

    @Test
    public void the_contains_method_when_point_is_inside_bounding_box_should_return_true() {
        //arrange
        Cartesian minPoint = new Cartesian(-100, -100);
        Cartesian maxPoint = new Cartesian(100, 100);
        CartesianBoundingBox box = new CartesianBoundingBox(minPoint, maxPoint);
        Cartesian aPointInsideBoundingBox = new Cartesian(-50, -50);

        //act
        boolean result = box.contains(aPointInsideBoundingBox);

        //assert
        boolean expectedResult = true;

        assertEquals(expectedResult,result);
    }

    @Test
    public void the_contains_method_when_point_is_outside_bounding_box_should_return_false() {
        //arrange
        Cartesian minPoint = new Cartesian(-100, -100);
        Cartesian maxPoint = new Cartesian(100, 100);
        CartesianBoundingBox box = new CartesianBoundingBox(minPoint, maxPoint);
        Cartesian aPointInsideBoundingBox = new Cartesian(500, 500);

        //act
        boolean result = box.contains(aPointInsideBoundingBox);

        //assert
        boolean expectedResult = false;
        assertEquals(expectedResult,result);
    }
}
