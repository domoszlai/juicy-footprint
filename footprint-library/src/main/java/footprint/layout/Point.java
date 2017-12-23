package footprint.layout;

import footprint.engine.EngineMismatchException;
import footprint.engine.Expression;
import footprint.engine.Variable;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 *
 * @author dlacko
 */
public class Point extends Shape {
 
    private final Variable x; 
    private final Variable y;
    
    Point(Layouter layout, String name)
    {
        super(layout, name);
        
        x = layout.createVariable(name+".X");
        y = layout.createVariable(name+".Y");
    }
    
    public Variable getX()
    {
        return x;
    }

    public Variable getY()
    {
        return y;
    }

    /**
     * Add constraints: 
     * - this.X = xv
     * - this.Y = yv
     * @param xv
     * @param yv
     */
    public void addConstraint(double xv, double yv)
    {
        x.addConstraint(xv);
        y.addConstraint(yv);
    }

    /**
     * Add constraints: 
     * - this.X = xv
     * - this.Y = yv
     * @param xv
     * @param yv
     * @throws footprint.engine.EngineMismatchException
     */
    public void addConstraint(Expression xv, Expression yv) throws EngineMismatchException
    {
        x.addConstraint(xv);
        y.addConstraint(yv);
    }	
	
    /**
     * Add constraints:
     * - this.X = p.X + xv
     * - this.Y = p.Y + yv
     * @param p
     * @param xv
     * @param yv
     * @throws footprint.engine.EngineMismatchException
     */
    public void addConstraint(Point p, double xv, double yv) throws EngineMismatchException
    {
        x.addConstraint(p.getX().add(xv));
        y.addConstraint(p.getY().add(yv));
    }
    	
    /**
     * Add constraints:
     * - this.X = p.X + xv
     * - this.Y = p.Y + yv
     * @param p
     * @param xv
     * @param yv
     * @throws footprint.engine.EngineMismatchException
     */
    public void addConstraint(Point p, Expression xv, Expression yv) throws EngineMismatchException
    {
        x.addConstraint(p.getX().add(xv));
        y.addConstraint(p.getY().add(yv));
    }

    /**
     * Add constraints:
     * - this.X = p.X + xv
     * - this.Y = p.Y + yv
     * @param p
     * @param xv
     * @param yv
     * @throws footprint.engine.EngineMismatchException
     */    
    public void addConstraint(Point p, double xv, Expression yv) throws EngineMismatchException
    {
        x.addConstraint(p.getX().add(xv));
        y.addConstraint(p.getY().add(yv));
    }

    /**
     * Add constraints:
     * - this.X = p.X + xv
     * - this.Y = p.Y + yv
     * @param p
     * @param xv
     * @param yv
     * @throws footprint.engine.EngineMismatchException
     */    
    public void addConstraint(Point p, Expression xv, double yv) throws EngineMismatchException
    {
        x.addConstraint(p.getX().add(xv));
        y.addConstraint(p.getY().add(yv));
    }

    
    /**
     * Add constraints:
     * - this.X = p.X
     * - this.Y = p.Y
     * @param p
     * @throws footprint.engine.EngineMismatchException
     */    
    public void addConstraint(Point p) throws EngineMismatchException
    {
        x.addConstraint(p.getX());
        y.addConstraint(p.getY());
    }    
    
    @Override
    protected void generateConstraints() {
        // Nothing to add
    }

    @Override
    public Bounds getBounds(Layout layout) {
        return new BoundingBox(layout.getValue(x), layout.getValue(y), 0, 0);
    }
}
