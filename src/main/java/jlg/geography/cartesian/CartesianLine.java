package jlg.geography.cartesian;

import jlg.geography.AbstractLine;
import jlg.geography.GeometryFeature;

import static jlg.codecontract.CodeContract.verifyNotNull;
import static jlg.codecontract.CodeContract.verifyThat;

/**
 * Represents a list of geographic coordinates that are united to form a line.
 */
public class CartesianLine extends AbstractLine<Cartesian> implements GeometryFeature {
    public CartesianLine(Cartesian[] points){
        super(points);
    }

    /**
     * @param coordinates a vector of coordiantes (minimum 4). A cartesian is then created for each pair
     *                    of coordinates (i,i+1)
     */
    public CartesianLine(int[] coordinates) {
        super();

        verifyNotNull(coordinates);
        verifyThat(coordinates.length >= 4, "Coordinates size must be gte than 4 in order to create a line (2 points)");

        for (int i = 0; i < coordinates.length; i += 2) {
            this.points.add(new Cartesian(coordinates[i], coordinates[i + 1]));
        }
    }
}
