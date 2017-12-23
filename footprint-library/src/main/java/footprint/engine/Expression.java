package footprint.engine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlacko
 */
public abstract class Expression {
    
    private final Engine engine;
    
    protected Expression(Engine engine)
    {
        this.engine = engine;
    }
    
    protected Engine getEngine()
    {
        return engine;
    }
    
    static Engine getVerifyEngines(Expression expr1, Expression expr2) throws EngineMismatchException
    {
        if(expr1.engine != null && expr2.engine != null && 
           expr1.engine != expr2.engine) throw new EngineMismatchException();
        
        return expr1.engine != null ? expr1.engine : expr2.engine;
    }
    
    /**
     * Add constraint: this = val  
     * @param val
     */
    public void addConstraint(double val)
    {
        engine.addConstraint(this, val);
    }

    /**
     * Add constraint: this = expr  
     * @param expr
     * @throws footprint.engine.EngineMismatchException
     */
    public void addConstraint(Expression expr) throws EngineMismatchException
    {
        engine.addConstraint(this, expr);
    }
    
    public Expression add(Expression expr) throws EngineMismatchException {

        return new CompoundExpression(
                Expression.getVerifyEngines(this, expr), this, expr);
    }

    public Expression subtract(Expression expr) throws EngineMismatchException {
        return add(expr.negate());
    }
        
    public Expression add(double val) throws EngineMismatchException {
        return add(new Const(val));
    }    
    
    public Expression subtract(double val) throws EngineMismatchException {
        return add(new Const(-val));
    }    
    
    public Expression negate()
    {
        return mul(-1);
    }
    
    public abstract Expression mul(double factor);
    
    public List<Expression> getTerms() {
         ArrayList<Expression> list = new ArrayList<>(1);
         list.add(this);
         return list;
    }
}
