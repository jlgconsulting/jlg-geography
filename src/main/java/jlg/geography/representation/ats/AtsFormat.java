package jlg.geography.representation.ats;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jlg.codecontract.CodeContract.verifyThat;

public class AtsFormat {

    public static AtsPoint transform(Point point) {
        return new AtsPoint(point.getLongitude(), point.getLatitude());
    }

    public static AtsPoint[][] transform(Line line) {

        List<Point> allLinePoints = line.getPoints();
        List<Line> unprocessedLines = new ArrayList<>();

        for (int i = 0; i < allLinePoints.size() - 1; i++) {
            unprocessedLines.add(
                    new Line(Arrays.asList(allLinePoints.get(i), allLinePoints.get(i +1), allLinePoints.get(i)))
            );
        }

        AtsPoint[][] finalAtsLine = new AtsPoint[unprocessedLines.size()][];
        for (int i = 0; i < unprocessedLines.size(); i++) {
            AtsPoint[] atsLine = formatSingleLine(unprocessedLines.get(i));
            finalAtsLine[i] = atsLine;
        }

        return finalAtsLine;
    }

    public static AtsPoint[][] transform(Polygon polygon) {

        AtsPoint[] atsPolygon = formatSinglePolygon(polygon.getPoints());

        AtsPoint[][] finalAtsPolygon = new AtsPoint[1][];
        finalAtsPolygon[0] = atsPolygon;
        return finalAtsPolygon;
    }

    public static AtsPoint[][] transform(MultiPolygon multiPolygon) {
        int size = multiPolygon.getPolygons().size();
        AtsPoint[][] atsMultiPolygon = new AtsPoint[size][];

        for (int i = 0; i < multiPolygon.getPolygons().size(); i++)
            atsMultiPolygon[i] = formatSinglePolygon(multiPolygon.getPolygons().get(i).getPoints());


        return atsMultiPolygon;
    }

    private static AtsPoint[] formatSinglePolygon(List<Point> points) {
        AtsPoint[] atsPolygon = new AtsPoint[points.size()];

        for (int i = 0; i < points.size(); i++) {
            AtsPoint atsPoint = new AtsPoint();
            atsPoint.setX(points.get(i).getLongitude());
            atsPoint.setY(points.get(i).getLatitude());
            atsPolygon[i] = atsPoint;
        }

        return atsPolygon;
    }

    private static AtsPoint[] formatSingleLine(Line line) {
        verifyThat(line.getPoints().size() == 3, "Error trying to format a line segment. Must have 3 points exactly");

        AtsPoint[] atsLine = new AtsPoint[3];
        int lastIndex = 2;

        for (int i = 0; i < line.getPoints().size(); i++) {
            AtsPoint atsPoint = new AtsPoint();
            atsPoint.setX(line.getPoints().get(i).getLongitude());
            atsPoint.setY(line.getPoints().get(i).getLatitude());
            atsLine[i] = atsPoint;
            atsLine[lastIndex - i] = atsPoint;
        }

        return atsLine;
    }
}