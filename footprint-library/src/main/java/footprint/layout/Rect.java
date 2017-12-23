package footprint.layout;

import footprint.engine.EngineMismatchException;
import footprint.engine.Variable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 *
 * @author dlacko
 */
public class Rect extends Shape {
    
    private final HorizontalLine top;
    private final HorizontalLine bottom;
    private final VerticalLine left;
    private final VerticalLine right;
    private final Variable width;
    private final Variable height;
    
    Rect(Layouter layout, String name)
    {
        super(layout, name);
        
        top = layout.createHorizontalLine(name+".Top");
        bottom = layout.createHorizontalLine(name+".Bottom");
        left = layout.createVerticalLine(name+".Left");
        right = layout.createVerticalLine(name+".Right");
        
        width = layout.createVariable(name+".Width");
        height = layout.createVariable(name+".height");
    }
    
    public HorizontalLine getTop()
    {
        return top;
    }
    
    public HorizontalLine getBottom()
    {
        return bottom;
    }    

    public VerticalLine getLeft()
    {
        return left;
    }    

    public VerticalLine getRight()
    {
        return right;
    }

    public Variable getWidth() {
        return width;
    }

    public Variable getHeight() {
        return height;
    }
    
    @Override
    protected void generateConstraints() throws EngineMismatchException {
        top.getP1().addConstraint(left.getP1());
        top.getP2().addConstraint(right.getP1());
        bottom.getP1().addConstraint(left.getP2());
        bottom.getP2().addConstraint(right.getP2());
        
        top.getLength().addConstraint(width);
        bottom.getLength().addConstraint(width);
        left.getLength().addConstraint(height);
        right.getLength().addConstraint(height);
    }    
    
    @Override
    public Bounds getBounds(Layout layout) {
        return new BoundingBox(
                layout.getValue(top.getP1().getX()), 
                layout.getValue(top.getP1().getY()),
                layout.getValue(width), 
                layout.getValue(height));
    }    
}
