package footprint.layout;

import footprint.engine.Variable;

/**
 *
 * @author dlacko
 */
public abstract class Line extends Shape {
    
    private final Point p1;
    private final Point p2;
    private final Variable length;
    
    Line(Layouter layout, String name)
    {
        super(layout, name);
        
        p1 = layout.createPoint(name + ".P1");
        p2 = layout.createPoint(name + ".P2");
        length = layout.createVariable(name + ".Length");
    }

    public Point getP1()
    {
        return p1;
    }

    public Point getP2()
    {
        return p2;
    }
    
    public Variable getLength() {
        return length;
    }    
}
