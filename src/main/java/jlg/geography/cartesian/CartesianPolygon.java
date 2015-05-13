package jlg.geography.cartesian;

import jlg.codecontract.CodeContractException;
import jlg.geography.AbstractPolygon;
import jlg.geography.BoundingBox;

public class CartesianPolygon extends AbstractPolygon<Cartesian>{
    public CartesianPolygon(Cartesian[] points){
        super(points);
        checkIfPolygonIsClosed();
        boundingBox = new CartesianBoundingBox(points);
    }

    public CartesianPolygon(int[] coordinates){
        super();
        for(int i=0; i<coordinates.length;i+=2){
            this.points.add(new Cartesian(coordinates[i],coordinates[i+1]));
        }
        checkIfPolygonIsClosed();
        boundingBox = new CartesianBoundingBox(points.toArray(new Cartesian[points.size()]));
    }

    @Override
    public boolean contains(Cartesian cartesian) {
        if(!cartesian.isInBoundingBox(getBoundingBox())){
            return false;
        }

        int windingNumber = 0;
        for(int i=0; i<this.points.size()-1;i++){
            if(this.points.get(i).getY() <= cartesian.getY()){
                if(this.points.get(i+1).getY() > cartesian.getY()){
                    //an upward crossing
                    if(isLeft(this.points.get(i), this.points.get(i+1), cartesian) > 0){
                        //has a valid up intersect
                        ++windingNumber;
                    }
                }
            }
            else{
                if(this.points.get(i+1).getY() <= cartesian.getY()){
                    //a downward crossing
                    if(isLeft(this.points.get(i), this.points.get(i+1), cartesian) < 0){
                        //has a valid down intersect
                        --windingNumber;
                    }
                }
            }
        }
        return windingNumber != 0;
    }

    private int isLeft(Cartesian p0, Cartesian p1, Cartesian somePoint){
        return (p1.getX() - p0.getX()) * (somePoint.getY() - p0.getY())
                - (somePoint.getX() - p0.getX()) * (p1.getY() - p0.getY());
    }

    private void checkIfPolygonIsClosed() {
        Cartesian firstPolygonPoint = this.points.get(0);
        Cartesian lastPolygonPoint = this.points.get(this.points.size()-1);

        if(firstPolygonPoint.getX() != lastPolygonPoint.getX()){
            throw new CodeContractException("The polygon is not closed. Can not construct the polygon. Latitudes are different. ");
        }

        if(firstPolygonPoint.getY() != lastPolygonPoint.getY()){
            throw new CodeContractException("The polygon is not closed. Can not construct the polygon. Longitudes are different");
        }
    }
}
