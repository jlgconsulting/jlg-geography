package jlg.geography.Ats;

import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;

public class AtsFormat {

    public AtsPoint transformPoint(Point point) {
        return new AtsPoint(point.getLongitude(), point.getLatitude());
    }

    public AtsPoint[] transformLine(Line line) {
        AtsPoint[] atsLine = new AtsPoint[line.getPoints().size() * 2 - 1];
        int lastIndex = line.getPoints().size() * 2 - 2;

        for (int i = 0; i < line.getPoints().size(); i++) {
            AtsPoint atsPoint = new AtsPoint();
            atsPoint.setX(line.getPoints().get(i).getLongitude());
            atsPoint.setY(line.getPoints().get(i).getLatitude());
            atsLine[i] = atsPoint;
            atsLine[lastIndex - i] = atsPoint;
        }

        return atsLine;
    }

    public AtsPoint[] transformPolygon(Polygon polygon) {
        AtsPoint[] atsPolygon = new AtsPoint[polygon.getPoints().size()];

        for (int i = 0; i < polygon.getPoints().size(); i++) {
            AtsPoint atsPoint = new AtsPoint();
            atsPoint.setX(polygon.getPoints().get(i).getLongitude());
            atsPoint.setY(polygon.getPoints().get(i).getLatitude());
            atsPolygon[i] = atsPoint;
        }

        return atsPolygon;
    }

    public AtsPoint[][] transformMultiPolygon(MultiPolygon multiPolygon) {
        int size = multiPolygon.getPolygons().size();
        AtsPoint[][] atsMultiPolygon = new AtsPoint[size][];


        for (int i = 0; i < size; i++)
            atsMultiPolygon[i] = transformPolygon(multiPolygon.getPolygons().get(i));

        return atsMultiPolygon;
    }
}