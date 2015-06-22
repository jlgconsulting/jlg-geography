package jlg.geography.test;

import jlg.geography.geometry.Point;
import jlg.geography.projection.CartesianProjection;
import jlg.geography.projection.Wsg84Projection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProjectionTest {
    @Test
    public void should_convert_wsg84_coordinates_to_cartesian_coordinates(){
        //arrange
        Point centerOfProjection = new Point(48.4, 17.9);
        Point geodeticPoint = new Point(48.404496, 17.906753);

        //act
        CartesianProjection projection = new CartesianProjection(centerOfProjection);
        Point result = projection.project(geodeticPoint);

        //assert
        assertEquals(999, (int)result.getLongitude());
        assertEquals(999, (int)result.getLatitude());
    }

    @Test
    public void should_convert_cartesian_to_wsg84_coordinates(){
        //arrange
        Point centerOfProjection = new Point(48.4, 17.9);
        Wsg84Projection projection = new Wsg84Projection(centerOfProjection);

        int xInMeters = 500;
        int yInMeters = 500;
        int altitudeInMeters = 100;

        //act
        Point result = projection.project(new Point(yInMeters, xInMeters), altitudeInMeters);

        //assert
        assertEquals(48.404496, result.getLatitude(),0.000001);
        assertEquals(17.906753, result.getLongitude(),0.000001);
    }
}
