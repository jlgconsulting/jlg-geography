package jlg.geography.test;

import jlg.geography.*;
import jlg.geography.wsg84.LatLon;
import jlg.geography.wsg84.GeographicLine;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeographicLineTest {
    @Test
    public void with_array_of_cartesian_points_should_build_line(){
        //arrange
        LatLon[] points = {new LatLon(1,1), new LatLon(2,2)};

        //act
        AbstractLine line = new GeographicLine(points);

        //assert
        assertEquals(2, line.getPoints().size());
    }

    @Test
    public void with_array_of_integer_coordiantes_should_build_line(){
        //arrange
        double[] coordinates = {1.0,1.0,2.0,2.0};

        //act
        AbstractLine line = new GeographicLine(coordinates);

        //assert
        assertEquals(2, line.getPoints().size());
    }
}
