package jlg.geography.test;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartesianLineTest {
    @Test
    public void with_array_of_cartesian_points_should_build_line(){
        //arrange
        Point[] points = {new Point(1,1), new Point(2,2)};

        //act
        Line line = new Line(points);

        //assert
        assertEquals(2, line.getPoints().size());
    }

    @Test
    public void with_array_of_integer_coordiantes_should_build_line(){
        //arrange
        double[] coordinates = {1,1,2,2};

        //act
        Line line = new Line(coordinates);

        //assert
        assertEquals(2, line.getPoints().size());
    }
}
