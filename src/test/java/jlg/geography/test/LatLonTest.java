package jlg.geography.test;

import jlg.codecontract.CodeContractException;
import jlg.geography.LatLon;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class LatLonTest {

    @Test
    @Parameters({"-90,-180","-45,-90","0,0","45,90","90,180"})
    public void the_constructor_with_wsg84_args_should_return_correct_value(double validLatitude, double validLongitude){
        //act
        LatLon coordinate = new LatLon(validLatitude,validLongitude);

        //assert
        assertEquals(validLatitude, coordinate.getLatitude(), 0.000001);
        assertEquals(validLongitude, coordinate.getLongitude(), 0.000001);
    }

    @Test(expected = CodeContractException.class)
    @Parameters({"-91","91"})
    public void the_constructor_with_latitude_out_of_bounds_should_throw(int invalidLatitude){
        //arrange
        double aValidLongitude = 11.123456;

        //act
        LatLon coordinate = new LatLon(invalidLatitude,aValidLongitude);
    }

    @Test(expected = CodeContractException.class)
    @Parameters({"-181","181"})
    public void the_constructor_with_longitude_out_of_bounds_should_throw(int invalidLongitude){
        //arrange
        double aValidLatitude = 11.123456;

        //act
        LatLon coordinate = new LatLon(aValidLatitude,invalidLongitude);
    }

    @Test
    @Parameters({
            "453467N,1233421W,45.58527,-123.57249",
            "453467N,1233421E,45.58527,123.57249",
            "453467S,1233421W,-45.58527,-123.57249",
            "453467S,1233421E,-45.58527,123.57249"
    })
    public void the_constructor_with_geographic_args_should_instantiate_correct_lat_lon(String geodeticLat, String geodeticLon, double expectedDecimalLat, double expectedDecimalLon){
        //act
        LatLon latLonResult = null;
        try {
            latLonResult = new LatLon(geodeticLat, geodeticLon);
        } catch (Exception e) {
            fail("should not have thrown exception");
        }

        assertEquals(expectedDecimalLat, latLonResult.getLatitude(),0.1);
        assertEquals(expectedDecimalLon, latLonResult.getLongitude(),0.1);
    }
}
