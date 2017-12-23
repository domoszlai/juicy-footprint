package footprint.layout;

import footprint.engine.EngineMismatchException;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 *
 * @author dlacko
 */
public class HorizontalLine extends Line {
        
    HorizontalLine(Layouter layout, String name)
    {
        super(layout, name);
    }

    @Override
    protected void generateConstraints() throws EngineMismatchException {
        getP1().getY().addConstraint(getP2().getY());
        getP2().getX().addConstraint(getP1().getX().add(getLength()));
        getLength().addConstraint(getP2().getX().subtract(getP1().getX()));            
    }
    
    @Override
    public Bounds getBounds(Layout layout) {
        return new BoundingBox(
                layout.getValue(getP1().getX()), 
                layout.getValue(getP1().getY()), 
                layout.getValue(getP2().getX()) - layout.getValue(getP1().getX()), 
                0);
    }
}
