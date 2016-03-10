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

    public static AtsPoint[][] transform(Point point) {

        AtsPoint[] atsPoint = {new AtsPoint(point.getLongitude(), point.getLatitude())};
        AtsPoint[][] finalAtsPoint = {atsPoint};
        return finalAtsPoint;
    }

    public static AtsPoint[][] transform(Line line) {

//        List<Line> unprocessedLines = new ArrayList<>();
//
//        for (int i = 0; i < allLinePoints.size() - 1; i++) {
//            unprocessedLines.add(
//                    new Line(Arrays.asList(allLinePoints.get(i), allLinePoints.get(i + 1)))
//            );
//        }
//
//        AtsPoint[][] finalAtsLine = new AtsPoint[unprocessedLines.size()][];
//        for (int i = 0; i < unprocessedLines.size(); i++) {
//            AtsPoint[] atsLine = formatSingleLine(unprocessedLines.get(i));
//            finalAtsLine[i] = atsLine;
//        }

        AtsPoint[][] finalAtsLine = new AtsPoint[1][];
        finalAtsLine[0] = formatLinePoints(line);

        return finalAtsLine;
    }

    public static AtsPoint[][] transform(Polygon polygon) {
        List<Point> polygonPoints = polygon.getPoints();
        List<Point> pointsToUse = polygonPoints.subList(0, polygonPoints.size() - 1);

        AtsPoint[] atsPolygon = formatSinglePolygon(pointsToUse);

        AtsPoint[][] finalAtsPolygon = new AtsPoint[1][];
        finalAtsPolygon[0] = atsPolygon;
        return finalAtsPolygon;
    }

    public static AtsPoint[][] transform(MultiPolygon multiPolygon) {
        int size = multiPolygon.getPolygons().size();
        AtsPoint[][] atsMultiPolygon = new AtsPoint[size][];

        for (int i = 0; i < multiPolygon.getPolygons().size(); i++) {
            List<Point> polygonPoints = multiPolygon.getPolygons().get(i).getPoints();
            List<Point> pointsToUse = polygonPoints.subList(0, polygonPoints.size() - 1);
            atsMultiPolygon[i] = formatSinglePolygon(pointsToUse);
        }


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
        verifyThat(line.getPoints().size() == 2, "Error trying to format a line segment. Must have 2 points exactly");

        AtsPoint[] atsLine = new AtsPoint[2];

        for (int i = 0; i < line.getPoints().size(); i++) {
            AtsPoint atsPoint = new AtsPoint();
            atsPoint.setX(line.getPoints().get(i).getLongitude());
            atsPoint.setY(line.getPoints().get(i).getLatitude());
            atsLine[i] = atsPoint;
        }

        return atsLine;
    }


    private static AtsPoint[] formatLinePoints(Line line) {
        List<Point> linePoints = line.getPoints();
        List<AtsPoint> atsLinePoints = new ArrayList<>();

        linePoints.stream().forEach(point -> {
            AtsPoint atsPoint = new AtsPoint();
            atsPoint.setX(point.getLongitude());
            atsPoint.setY(point.getLatitude());

            atsLinePoints.add(atsPoint);
        });

        AtsPoint[] atsLine = new AtsPoint[linePoints.size()];
        return atsLinePoints.toArray(atsLine);
    }
}