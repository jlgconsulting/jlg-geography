package jlg.geography.representation.ats;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;

import java.util.List;

public class AtsFormat {

    public static AtsPoint transform(Point point) {
        return new AtsPoint(point.getLongitude(), point.getLatitude());
    }

    public static AtsPoint[][] transform(Line line) {
        AtsPoint[] atsLine = new AtsPoint[line.getPoints().size() * 2 - 1];
        int lastIndex = line.getPoints().size() * 2 - 2;

        for (int i = 0; i < line.getPoints().size(); i++) {
            AtsPoint atsPoint = new AtsPoint();
            atsPoint.setX(line.getPoints().get(i).getLongitude());
            atsPoint.setY(line.getPoints().get(i).getLatitude());
            atsLine[i] = atsPoint;
            atsLine[lastIndex - i] = atsPoint;
        }

        AtsPoint[][] finalAtsLine = new AtsPoint[1][];
        finalAtsLine[0] = atsLine;
        return finalAtsLine;
    }

    public static AtsPoint[][] transform(Polygon polygon) {

        AtsPoint[] atsPolygon = formatSinglePolygon(polygon.getPoints());

        AtsPoint[][] finalAtsPolygon = new AtsPoint[1][];
        finalAtsPolygon[0] = atsPolygon;
        return finalAtsPolygon;
    }

    public static AtsPoint[][] transformMulti(MultiPolygon multiPolygon) {
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
}