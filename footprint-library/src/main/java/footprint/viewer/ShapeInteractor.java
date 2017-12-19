package footprint.viewer;

import footprint.layout.Layout;
import footprint.layout.Line;
import footprint.layout.Pad;
import footprint.layout.Point;
import footprint.layout.Rect;
import footprint.layout.Shape;

/**
 *
 * @author dlacko
 */
public abstract class ShapeInteractor {
        
    public abstract javafx.scene.Node getVisualShapes(Layout layout, double scale);
    
    public static ShapeInteractor createInteractor(Shape shape)
    {
        if(shape instanceof Point)
        {
            return new PointInteractor((Point) shape);
        }
        else if(shape instanceof Line)
        {
            return new LineInteractor((Line) shape);
        }
        else if(shape instanceof Rect)
        {
            return new RectInteractor((Rect) shape);
        }
        else if(shape instanceof Pad)
        {
            return new PadInteractor((Pad) shape);
        }
        else
        {
            throw new RuntimeException();
        }
    }
}
