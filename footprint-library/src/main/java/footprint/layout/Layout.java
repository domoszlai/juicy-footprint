package footprint.layout;

import footprint.engine.Solution;
import java.util.List;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 *
 * @author dlacko
 */
public class Layout {
    
    private final Solution solution;
    private final List<Shape> shapes;
    
    Layout(Solution solution, List<Shape> shapes)
    {
        this.shapes = shapes;
        this.solution = solution;
    }
    
    public double getValue(Size size)
    {
        return solution.getValue(size.getVar());
    }
    
    public List<Shape> getShapes()
    {
        return shapes;
    }
    
    @Override
    public String toString()
    {
        return solution.toString();
    }

    public Bounds getBounds() {
        double minx = Double.MAX_VALUE;
        double miny = Double.MAX_VALUE;
        double maxx = Double.MIN_VALUE;
        double maxy = Double.MIN_VALUE;
        
        for(Shape shape : shapes)
        {
            Bounds b = shape.getBounds(this);
            minx = Math.min(minx, b.getMinX());
            miny = Math.min(miny, b.getMinY());
            maxx = Math.max(maxx, b.getMaxX());
            maxy = Math.max(maxy, b.getMaxY());
        }
        
        return new BoundingBox(minx, miny, maxx-minx, maxy-miny);
    }        
}
