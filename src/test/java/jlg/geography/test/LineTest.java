package jlg.geography.test;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LineTest {
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
        double[] coordinates = {1.0,1.0,2.0,2.0};

        //act
        Line line = new Line(coordinates);

        //assert
        assertEquals(2, line.getPoints().size());
    }

    @Test
    public void contains_method_works_point_not_on_line() {
        //arrange
        double[] coordinates = {9.0, 1.0,
                                9.0, 8.0,
                                5.0, 9.0,
                                5.0, 17.0,
                                9.0, 1.0};
        Line line = new Line(coordinates);

        // act
        boolean result = line.contains(new Point(8.0, 5.0));

        // assert
        assertFalse("Point should not be found on the line", result);
    }

    @Test
    public void contains_method_works_point_on_line() {
        //arrange
        double[] coordinates = {9.0, 1.0,
                9.0, 8.0,
                5.0, 9.0,
                5.0, 17.0,
                9.0, 1.0};
        Line line = new Line(coordinates);

        // act
        boolean result = line.contains(new Point(9.0, 4.42));

        // assert
        assertTrue("Point should be found on the line", result);
    }
}
