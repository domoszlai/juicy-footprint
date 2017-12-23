package footprint.engine;

/**
 *
 * @author dlacko
 */
public class Variable extends Expression {
    
    private final String name;
    private final int index;
    
    Variable(Engine engine, String name, int index)
    {
        super(engine);
        this.name = name;
        this.index = index;
    }
        
    @Override
    public Expression mul(double factor)
    {
        return new MulExpression(getEngine(), this, factor);
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
