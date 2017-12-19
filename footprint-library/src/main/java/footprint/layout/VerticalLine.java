package footprint.layout;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 *
 * @author dlacko
 */
public class VerticalLine extends Line {
        
    VerticalLine(Layouter layout, String name)
    {
        super(layout, name);
    }

    @Override
    protected void generateConstraints() {
        getP1().getX().addConstraint(getP2().getX());
        getLength().addConstraint(getP2().getY().subtract(getP1().getY()));
    }
    
    @Override
    public Bounds getBounds(Layout layout) {
        return new BoundingBox(
                layout.getValue(getP1().getX()), 
                layout.getValue(getP1().getY()), 
                0, 
                layout.getValue(getP2().getY()) - layout.getValue(getP1().getY()));
    }    
}
