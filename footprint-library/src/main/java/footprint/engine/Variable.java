package footprint.engine;

/**
 *
 * @author dlacko
 */
public class Variable extends Expression {
    
    private final String name;
    private final int index;
    private final Engine engine;
    
    Variable(Engine engine, String name, int index)
    {
        this.engine = engine;
        this.name = name;
        this.index = index;
    }
    
    Engine getEngine()
    {
        return engine;
    }
    
    @Override
    public Expression mul(double factor)
    {
        return new MulExpression(this, factor);
    }
    
    int getIndex()
    {
        return index;
    }
    
    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }    
}
