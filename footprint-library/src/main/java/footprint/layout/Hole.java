package footprint.layout;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 *
 * @author dlacko
 */
public class Hole extends Shape {

    private final Point top;
    private final Point bottom;
    private final Point left;
    private final Point right;
    
    private final Point center;
    private final Size radius;
    
    Hole(Layouter layout, String name)
    {
        super(layout, name);
        
        top = layout.createPoint(name + ".Top");
        bottom = layout.createPoint(name + ".Bottom");
        left = layout.createPoint(name + ".Left");
        right = layout.createPoint(name + ".Right");
        
        center = layout.createPoint(name + ".Center");
        radius = layout.createSize(name + ".Radius");
    }
    
    public Point getCenter()
    {
        return center;
    }
    
    public Size getRadius()
    {
        return radius;
    }   

    public Point getTop() {
        return top;
    }

    public Point getBottom() {
        return bottom;
    }

    public Point getLeft() {
        return left;
    }

    public Point getRight() {
        return right;
    }
    
    @Override
    protected void generateConstraints() {
        top.addConstraint(center, 0, radius.negate());
        bottom.addConstraint(center, 0, radius);
        left.addConstraint(center, radius.negate(), 0);
        right.addConstraint(center, radius, 0);
        top.getX().addConstraint(bottom.getX());
        left.getY().addConstraint(right.getX());
    }
    
    @Override
    public Bounds getBounds(Layout layout) {
        return new BoundingBox(
                layout.getValue(getCenter().getX())-layout.getValue(getRadius()), 
                layout.getValue(getCenter().getY())-layout.getValue(getRadius()), 
                layout.getValue(getRadius()), 
                layout.getValue(getRadius()));
    }
}
