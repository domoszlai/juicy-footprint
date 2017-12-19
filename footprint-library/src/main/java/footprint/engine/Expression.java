package footprint.engine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlacko
 */
public abstract class Expression {
    
    public Expression add(Expression expr) {
        return new CompoundExpression(this, expr);
    }

    public Expression subtract(Expression expr) {
        return new CompoundExpression(this, expr.negate());
    }
        
    public Expression add(double val) {
        return new CompoundExpression(this, new Const(val));
    }    
    
    public Expression subtract(double val) {
        return new CompoundExpression(this, new Const(-val));
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
