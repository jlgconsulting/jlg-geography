package jlg.geography.test;

import jlg.codecontract.CodeContractException;
import jlg.geography.wsg84.LongitudeParser;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class LongitudeParserTest {
    @Test
    @Parameters({
            "1233421W,-123.5725",
            "1233421E,123.5725"
    })
    public void with_geographic_notation_when_valid_args_should_return_correct_wsg84_value(String geographicNotation, double expectedWsg84){
        double result = LongitudeParser.fromGeographicNotation(geographicNotation);

        assertEquals(expectedWsg84, result,0.000001);
    }

    @Test(expected = CodeContractException.class)
    public void with_geographic_notation_when_arg_is_null_should_throw(){
        LongitudeParser.fromGeographicNotation(null);
    }

    @Test(expected = CodeContractException.class)
    public void with_geographic_notation_when_arg_does_not_have_8_characters_should_throw(){
        LongitudeParser.fromGeographicNotation("1233421");
    }
}
