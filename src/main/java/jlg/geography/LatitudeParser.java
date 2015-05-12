package jlg.geography;

import java.text.DecimalFormat;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyThat;

/**
 * Utility class that is used to parse latitude data from various representations
 */
public class LatitudeParser {

    /**
     * Extracts wsg 84 latitude from a string representing the the geographic notation
     * @param geogaphicNotation the latitude represented on 7 characters as DDMMSSH
     * @return wsg 84 latitude
     * @throws IllegalArgumentException when geographic notation is not a valid coordinate
     */
    public static double fromGeographicNotation(String geogaphicNotation){
        verifyNotEmpty(geogaphicNotation);
        verifyThat(geogaphicNotation.length() == 7, "The latitude needs to have 7 charcaters in the geographic notation.");

        try {
            double latitudeDegrees = Double.parseDouble(geogaphicNotation.substring(0, 2));
            double latitudeMinutes = Double.parseDouble(geogaphicNotation.substring(2, 4));
            double latitudeSeconds = Double.parseDouble(geogaphicNotation.substring(4, 6));
            String latitudeDirection = geogaphicNotation.substring(6, 7);

            double calculatedLatitude = latitudeDegrees + (((latitudeMinutes * 60) + latitudeSeconds) / 3600);
            if (latitudeDirection.equals("S")) {
                calculatedLatitude = calculatedLatitude * (-1.0);
            }

            return Double.parseDouble(new DecimalFormat("##.######").format(calculatedLatitude));
        }
        catch(Exception e){
            throw new IllegalArgumentException(geogaphicNotation + " is not a correct geographic notation for longitude");
        }
    }
}
