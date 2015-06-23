package jlg.geography.representation.wsg84;

import jlg.geography.GeometryFeature;
import jlg.geography.geometry.Line;
import jlg.geography.geometry.MultiPolygon;
import jlg.geography.geometry.Point;
import jlg.geography.geometry.Polygon;

import static jlg.codecontract.CodeContract.verifyNotNull;

/**
 * Utility class that transforms various wsg84 shapes into a WKT string
 */
public class WktFormat {
    public static String transform(GeometryFeature geographicFeature){
        if(geographicFeature instanceof Point){
            return transformPoint((Point) geographicFeature);
        }
        else if(geographicFeature instanceof Line){
            return transformLine((Line) geographicFeature);
        }
        else if(geographicFeature instanceof Polygon){
            return transformPolygon((Polygon) geographicFeature);
        }
        else if(geographicFeature instanceof MultiPolygon){
            return transformMultiPolygon((MultiPolygon) geographicFeature);
        }
        else{
            throw new RuntimeException("The geographic feature type is not recognized. Must be LatLon,GeographicLine,GeographicPolygon,GeographicMultiPolygon");
        }
    }

    private static String transformPoint(Point point){
        verifyNotNull(point);

        StringBuilder sb = new StringBuilder();
        sb.append("POINT(").append(point.getLongitude()).append(" ").append(point.getLatitude()).append(")");
        return sb.toString();
    }

    private static String transformLine(Line line){
        verifyNotNull(line);

        StringBuilder sb = new StringBuilder();
        sb.append("LINESTRING(");
        for(Point point:line.getPoints()){
            sb.append(point.getLongitude()).append(" ").append(point.getLatitude()).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));   //remove last ',' character
        sb.append(")");

        return sb.toString();
    }

    private static String transformPolygon(Polygon polygon){
        verifyNotNull(polygon);

        StringBuilder sb = new StringBuilder();
        sb.append("POLYGON((");
        for(Point point:polygon.getPoints()){
            //append each coordinate
            sb.append(point.getLongitude()).append(" ").append(point.getLatitude()).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));   //remove last ',' character
        sb.append("))");

        return sb.toString();
    }

    private static String transformMultiPolygon(MultiPolygon multiPolygon){
        verifyNotNull(multiPolygon);

        StringBuilder sb = new StringBuilder();
        sb.append("MULTIPOLYGON(");
        for(Polygon polygon:multiPolygon.getPolygons()){
            String polygonWkt = WktFormat.transform(polygon).replace("POLYGON","");
            sb.append(polygonWkt);
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")");

        return sb.toString();
    }
}
