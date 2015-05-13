package jlg.geography.wsg84;

import java.text.DecimalFormat;

import static jlg.codecontract.CodeContract.verifyNotEmpty;
import static jlg.codecontract.CodeContract.verifyThat;

/**
 * Utility class that is used to parse longitude data from various representations
 */
public class LongitudeParser {
    /**
     * Extracts wsg 84 latitude from a string representing the the geographic notation
     * @param geogaphicNotation the longitude represented on 8 characters as DDMMSSO
     * @return wsg 84 longitude
     * @throws IllegalArgumentException when geographic notation is not a valid coordinate
     */
    public static double fromGeographicNotation(String geogaphicNotation){
        verifyNotEmpty(geogaphicNotation);
        verifyThat(geogaphicNotation.length() == 8, "The longitude needs to have 8 charcaters in the geographic notation.");

        try {
            double longitudeDegrees = Double.parseDouble(geogaphicNotation.substring(0, 3));
            double longitudeMinutes = Double.parseDouble(geogaphicNotation.substring(3, 5));
            double longitudeSeconds = Double.parseDouble(geogaphicNotation.substring(5, 7));
            String longitudeDirection = geogaphicNotation.substring(7, 8);

            double calculatedLongitude = longitudeDegrees + (((longitudeMinutes * 60) + longitudeSeconds) / 3600);
            if (longitudeDirection.equals("W")) {
                calculatedLongitude = calculatedLongitude * (-1.0);
            }

            return Double.parseDouble(new DecimalFormat("###.######").format(calculatedLongitude));
        }
        catch (Exception e){
            throw new IllegalArgumentException(geogaphicNotation + " is not a correct geographic notation for longitude");
        }
    }
}
