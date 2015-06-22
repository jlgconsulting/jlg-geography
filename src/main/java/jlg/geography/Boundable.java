package jlg.geography;

import jlg.geography.geometry.BoundingBox;

public interface Boundable {
    boolean isInBoundingBox(BoundingBox boundingBox);
}
