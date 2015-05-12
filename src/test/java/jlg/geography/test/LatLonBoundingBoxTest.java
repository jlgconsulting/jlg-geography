package jlg.geography.test;

import jlg.geography.Cartesian;
import jlg.geography.CartesianBoundingBox;
import jlg.geography.LatLon;
import jlg.geography.LatLonBoundingBox;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LatLonBoundingBoxTest {
    @Test
    public void the_constructor_with_coordinate_args_should_build_correct_bounding_box(){
        //arrange
        LatLon minCoordinate = new LatLon(1,2);
        LatLon maxCoordinate = new LatLon(3,4);

        //act
        LatLonBoundingBox boundingBox = new LatLonBoundingBox(minCoordinate, maxCoordinate);

        //assert
        assertEquals(1, boundingBox.getMinCoordinate().getLatitude(), 0.000001);
        assertEquals(2, boundingBox.getMinCoordinate().getLongitude(), 0.000001);
        assertEquals(3, boundingBox.getMaxCoordinate().getLatitude(), 0.000001);
        assertEquals(4, boundingBox.getMaxCoordinate().getLongitude(), 0.000001);
        assertEquals(2, boundingBox.getCenterCoordinate().getLatitude(), 0.000001);
        assertEquals(3, boundingBox.getCenterCoordinate().getLongitude(), 0.000001);
    }

    @Test
    public void the_constructor_with_list_of_bounding_boxes_should_build_correct_bounding_box(){
        //arrange
        LatLonBoundingBox firstBox = new LatLonBoundingBox(new LatLon(1,1), new LatLon(5,5));
        LatLonBoundingBox secondBox = new LatLonBoundingBox(new LatLon(3,3), new LatLon(7,7));

        //act
        LatLonBoundingBox boundingBox = new LatLonBoundingBox(Arrays.asList(firstBox, secondBox));

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
        LatLon minPoint = new LatLon(-10, -10);
        LatLon maxPoint = new LatLon(10, 10);
        LatLonBoundingBox box = new LatLonBoundingBox(minPoint, maxPoint);
        LatLon aPointInsideBoundingBox = new LatLon(-5, 5);

        //act
        boolean result = box.contains(aPointInsideBoundingBox);

        //assert
        boolean expectedResult = true;

        assertEquals(expectedResult,result);
    }

    @Test
    public void the_contains_method_when_point_is_outside_bounding_box_should_return_false() {
        //arrange
        LatLon minPoint = new LatLon(-10, -10);
        LatLon maxPoint = new LatLon(10, 10);
        LatLonBoundingBox box = new LatLonBoundingBox(minPoint, maxPoint);
        LatLon aPointOutsideBoundingBox = new LatLon(11, 11);

        //act
        boolean result = box.contains(aPointOutsideBoundingBox);

        //assert
        boolean expectedResult = false;

        assertEquals(expectedResult,result);
    }
}
