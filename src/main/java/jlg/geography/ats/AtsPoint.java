package jlg.geography.ats;

public class AtsPoint {
    double x;
    double y;

    public AtsPoint() {
    }

    public AtsPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "AtsPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
