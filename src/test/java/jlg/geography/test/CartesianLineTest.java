package jlg.geography.test;

import jlg.geography.AbstractLine;
import jlg.geography.cartesian.Cartesian;
import jlg.geography.cartesian.CartesianLine;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartesianLineTest {
    @Test
    public void with_array_of_cartesian_points_should_build_line(){
        //arrange
        Cartesian[] points = {new Cartesian(1,1), new Cartesian(2,2)};

        //act
        AbstractLine line = new CartesianLine(points);

        //assert
        assertEquals(2, line.getPoints().size());
    }

    @Test
    public void with_array_of_integer_coordiantes_should_build_line(){
        //arrange
        int[] coordinates = {1,1,2,2};

        //act
        AbstractLine line = new CartesianLine(coordinates);

        //assert
        assertEquals(2, line.getPoints().size());
    }
}
