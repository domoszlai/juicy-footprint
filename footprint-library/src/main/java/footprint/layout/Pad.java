package footprint.layout;

import footprint.engine.EngineMismatchException;
import footprint.engine.Variable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 *
 * @author dlacko
 */
public class Pad extends Shape {
    
    private final Point topLeft;    
    private final Point topRight;
    private final Point bottomRight;    
    private final Point bottomLeft;
    private final Variable width;
    private final Variable height;
    
    private final Point center;
    private final Point centerTop;
    private final Point centerBottom;
    private final Point centerLeft;    
    private final Point centerRight;    

    Pad(Layouter layout, String name)
    {
        super(layout, name);
        
        topLeft = layout.createPoint(name+".TopLeft");
        topRight = layout.createPoint(name+".TopRight");
        bottomRight = layout.createPoint(name+".BottomRight");
        bottomLeft = layout.createPoint(name+".BottomLeft");
        
        center = layout.createPoint(name+".Center");
        centerTop = layout.createPoint(name+".CenterTop");
        centerBottom = layout.createPoint(name+".CenterBottom");
        centerLeft = layout.createPoint(name+".CenterLeft");
        centerRight = layout.createPoint(name+".CenterRight");
        
        width = layout.createVariable(name+".Width");
        height = layout.createVariable(name+".Height");
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public Point getCenter() {
        return center;
    }
    
    public Point getCenterTop() {
        return centerTop;
    }

    public Point getCenterBottom() {
        return centerBottom;
    }

    public Point getCenterLeft() {
        return centerLeft;
    }

    public Point getCenterRight() {
        return centerRight;
    }
    
    public Variable getWidth() {
        return width;
    }

    public Variable getHeight() {
        return height;
    }
    
    @Override
    protected void generateConstraints() throws EngineMismatchException {
        
        // TODO: what is the minimal set of equations I need?
        
        bottomLeft.addConstraint(topLeft, 0, height);
        topRight.addConstraint(topLeft, width, 0);
        bottomRight.addConstraint(bottomLeft, width, 0);
        bottomRight.addConstraint(topRight, 0, height);
        
        width.addConstraint(topRight.getX().subtract(topLeft.getX()));
        width.addConstraint(bottomRight.getX().subtract(bottomLeft.getX()));
        height.addConstraint(bottomLeft.getY().subtract(topLeft.getY()));
        height.addConstraint(bottomRight.getY().subtract(topRight.getY()));
        
        center.getX().addConstraint(topLeft.getX().add(width.mul(0.5d)));
        center.getY().addConstraint(topLeft.getY().add(height.mul(0.5d)));
        
        centerTop.getX().addConstraint(center.getX());
        centerTop.getY().addConstraint(topLeft.getY());
        centerTop.getY().addConstraint(topRight.getY());
        
        centerBottom.getX().addConstraint(center.getX());
        centerBottom.getY().addConstraint(bottomLeft.getY());
        centerBottom.getY().addConstraint(bottomRight.getY());
        
        centerLeft.getX().addConstraint(topLeft.getX());
        centerLeft.getX().addConstraint(bottomLeft.getX());
        centerLeft.getY().addConstraint(center.getY());
        
        centerRight.getX().addConstraint(topRight.getX());
        centerRight.getX().addConstraint(bottomRight.getX());
        centerRight.getY().addConstraint(center.getY()); 
    }   
    
    @Override
    public Bounds getBounds(Layout layout) {
        return new BoundingBox(
                layout.getValue(topLeft.getX()), 
                layout.getValue(topLeft.getY()),
                layout.getValue(width), 
                layout.getValue(height));
    }    
}
