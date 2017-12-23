package footprint.engine;

/**
 *
 * @author dlacko
 */
public class MulExpression extends Expression {

    private final double factor;
    private final Variable var;
    
    MulExpression(Engine engine, Variable var, double factor)
    {
        super(engine);
        this.factor = factor;
        this.var = var;
    }
 
    public Variable getVar()
    {
        return var;
    }
    
    public double getFactor()
    {
        return factor;
    }
    
    @Override
    public Expression mul(double newFactor)
    {
        return new MulExpression(getEngine(), var, factor*newFactor);
    }
    
    @Override
    public String toString()
    {
        return Double.toString(factor)+var.toString();
    }    
}
