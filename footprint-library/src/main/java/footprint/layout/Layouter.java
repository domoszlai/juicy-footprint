package footprint.layout;

import footprint.engine.Engine;
import footprint.engine.EngineMismatchException;
import footprint.engine.Expression;
import footprint.engine.Solution;
import footprint.engine.Variable;
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
        
    public Variable createVariable(String name)
    {
        return engine.createVariable(name);
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

    public Pad createPad(String name, double width, double height)
    {
        Pad h = new Pad(this, name);
        shapes.add(h);
        h.getWidth().addConstraint(width);
        h.getHeight().addConstraint(height);
        return h;        
    }

    public Pad createPad(String name, double width, Expression height) throws EngineMismatchException
    {
        Pad h = new Pad(this, name);
        shapes.add(h);
        h.getWidth().addConstraint(width);
        h.getHeight().addConstraint(height);
        return h;        
    }
        
    public Pad createPad(String name, Expression width, double height) throws EngineMismatchException
    {
        Pad h = new Pad(this, name);
        shapes.add(h);
        h.getWidth().addConstraint(width);
        h.getHeight().addConstraint(height);
        return h;        
    }
	
    public Pad createPad(String name, Expression width, Expression height) throws EngineMismatchException
    {
        Pad h = new Pad(this, name);
        shapes.add(h);
        h.getWidth().addConstraint(width);
        h.getHeight().addConstraint(height);
        return h;        
    }
            
    public List<Shape> getShapes()
    {
        return shapes;
    }
    
    public Layout generate()
    {
        for(Shape shape : shapes)
        {
            try {
                shape.generateConstraints();
            } catch (EngineMismatchException ex) {
                // Should not happen in generateConstraints
                throw new RuntimeException("Unexpected EngineMismatchException");
            }
        }
        
        Solution solution = engine.solve();
        return new Layout(solution, shapes);
    }    
}
