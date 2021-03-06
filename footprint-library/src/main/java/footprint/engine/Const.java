package footprint.engine;

/**
 *
 * @author dlacko
 */
public class Const extends Expression {

    private final double val;
    
    public Const(double val)
    {
        super(null); // Const does not depend on an engine
        this.val = val;
    }
    
    double getval()
    {
        return val;
    }
    
    @Override
    public Expression mul(double newFactor)
    {
        return new Const(val * newFactor);
    }    
    
    @Override
    public String toString()
    {
        return Double.toString(val);
    }    
}    

