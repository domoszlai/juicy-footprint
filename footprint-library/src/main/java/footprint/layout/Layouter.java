package footprint.layout;

import footprint.engine.Engine;
import footprint.engine.Expression;
import footprint.engine.Solution;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlacko
 */
public class Layouter {
    
    private final Engine engine;
    private final List<Shape> shapes = new ArrayList<>();
    
    public Layouter()
    {
        this.engine = new Engine();
    }
        
    public Size createSize(String name)
    {
        return new Size(this, engine.createVariable(name));
    }
    
    public Point createPoint(String name)
    {
        Point p = new Point(this, name);
        shapes.add(p);
        return p;
    }
    
    public HorizontalLine createHorizontalLine(String name)
    {
        HorizontalLine l = new HorizontalLine(this, name);
        shapes.add(l);
        return l;
    }

    public VerticalLine createVerticalLine(String name)
    {
        VerticalLine l = new VerticalLine(this, name);
        shapes.add(l);
        return l;
    }
    
    public Rect createRect(String name)
    {
        Rect r = new Rect(this, name);
        shapes.add(r);
        return r;        
    }

    public Hole createHole(String name)
    {
        Hole h = new Hole(this, name);
        shapes.add(h);
        return h;        
    }

    public Pad createPad(String name)
    {
        Pad h = new Pad(this, name);
        shapes.add(h);
        return h;        
    }
    
    /**
     * Add constraint: expr = val
     * @param expr
     * @param val
     */
    public void addConstraint(Expression expr, double val)
    {
        engine.addConstraint(expr, val);
    }

    /**
     * Add constraint: expr1 = expr2
     * @param expr1
     * @param expr2
     */    
    public void addConstraint(Expression expr1, Expression expr2)
    {
        engine.addConstraint(expr1, expr2);
    }    

    /**
     * Add constraints: 
     * - p1.X = p2.x
     * - p1.Y = p2.Y
     * @param p1
     * @param p2
     */    
    public void addConstraint(Point p1, Point p2)
    {
        engine.addConstraint(p1.getX(), p2.getX());
        engine.addConstraint(p1.getY(), p2.getY());
    }

    /**
     * Add constraint:
     * - p.X  = x
     * - p.Y  = y
     * @param p
     * @param x
     * @param y
     */
    public void addConstraint(Point p, double x, double y)
    {
        engine.addConstraint(p.getX(), x);
        engine.addConstraint(p.getY(), y);
    }
        
    public List<Shape> getShapes()
    {
        return shapes;
    }
    
    public Layout generate() throws Exception
    {
        for(Shape shape : shapes)
        {
            shape.generateConstraints();
        }
        
        Solution solution = engine.solve();
        return new Layout(solution, shapes);
    }    
}
