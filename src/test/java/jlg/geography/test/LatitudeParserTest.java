package jlg.geography.test;

import jlg.codecontract.CodeContractException;
import jlg.geography.LatitudeParser;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class LatitudeParserTest {

    @Test
    @Parameters({
            "453467N,45.585278",
            "453467S,-45.585278"
    })
    public void with_geographic_notation_when_valid_args_should_return_correct_wsg84_value(String geographicNotation, double expectedWsg84){
        double result = LatitudeParser.fromGeographicNotation(geographicNotation);

        assertEquals(result, expectedWsg84,0.000001);
    }

    @Test(expected = CodeContractException.class)
    public void with_geographic_notation_when_arg_is_null_should_throw(){
        LatitudeParser.fromGeographicNotation(null);
    }

    @Test(expected = CodeContractException.class)
    public void with_geographic_notation_when_arg_does_not_have_7_characters_should_throw(){
        LatitudeParser.fromGeographicNotation("453467");
    }
}
