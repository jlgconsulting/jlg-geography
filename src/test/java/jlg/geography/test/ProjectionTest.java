package jlg.geography.test;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;
import jlg.geography.projection.CartesianProjection;
import jlg.geography.projection.Wsg84Projection;
import org.junit.Test;

import java.util.function.Function;

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

    @Test
    public void should_convert_wsg84_coordinates_to_cartesian_coordinates_and_apply_correct_transformation(){
        //arrange
        Point centerOfProjection = new Point(48.4, 17.9);
        Point geodeticPoint = new Point(48.404496, 17.906753);
        Function<Double, Double> transformToMm = x -> x * 1000;

        //act
        CartesianProjection projection = new CartesianProjection(centerOfProjection);
        Point result = projection.project(geodeticPoint, transformToMm);

        //assert
        assertEquals(999000, (int)result.getLongitude());
        assertEquals(999000, (int)result.getLatitude());
    }

    @Test
    public void should_convert_a_line_object_and_apply_transformation_function() {
        // arrange
        Point centerOfProjection = new Point(1, 1);
        Line line = GeometricFeaturesFactory.getLine();
        Function<Double, Double> transformToKilometers = x -> x /  1000;
        CartesianProjection projection = new CartesianProjection(centerOfProjection);

        // act
        Line projectedLineNormal = projection.project(line);
        Line projectedLine = projection.project(line, transformToKilometers);

        // assert
        assertEquals("Transformation function was not correctly applied", projectedLineNormal.getPoints().get(0).getLatitude(), projectedLine.getPoints().get(0).getLatitude() * 1000, 0.1);
    }

    @Test
    public void should_convert_a_Polygon_object_and_apply_transformation_function() {
        // arrange
        Point centerOfProjection = new Point(1, 1);
        Polygon polygon = GeometricFeaturesFactory.getPolygon();
        Function<Double, Double> transformToKilometers = x -> x /  1000;
        CartesianProjection projection = new CartesianProjection(centerOfProjection);

        // act
        Polygon projectedPolygonNormal = projection.project(polygon);
        Polygon projectedPolygon = projection.project(polygon, transformToKilometers);

        // assert
        assertEquals("Transformation function was not correctly applied", projectedPolygonNormal.getPoints().get(0).getLatitude(), projectedPolygon.getPoints().get(0).getLatitude() * 1000, 0.1);
    }

    @Test
    public void should_convert_a_MultiPolygon_object_and_apply_transformation_function() {
        // arrange
        Point centerOfProjection = new Point(1, 1);
        MultiPolygon multipolygon = GeometricFeaturesFactory.getMultiPolygon(4);
        Function<Double, Double> transformToKilometers = x -> x /  1000;
        CartesianProjection projection = new CartesianProjection(centerOfProjection);

        // act
        MultiPolygon projectedPolygonNormal = projection.project(multipolygon);
        MultiPolygon projectedPolygon = projection.project(multipolygon, transformToKilometers);

        // assert
        assertEquals("Transformation function was not correctly applied", projectedPolygonNormal.getPoints().get(0).getLatitude(), projectedPolygon.getPoints().get(0).getLatitude() * 1000, 0.1);
    }
}
