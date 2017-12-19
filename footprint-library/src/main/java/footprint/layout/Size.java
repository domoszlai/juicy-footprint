package footprint.layout;

import footprint.engine.Expression;
import footprint.engine.Variable;
import java.util.List;

/**
 *
 * @author dlacko
 */
public class Size extends Expression {
    
    private final Layouter layout;
    private final Variable var;
    
    Size(Layouter layout, Variable var)
    {
        this.layout = layout;
        this.var = var;
    }

    /**
     * Add constraint: this = val  
     * @param val
     */
    public void addConstraint(double val)
    {
        layout.addConstraint(var, val);
    }

    /**
     * Add constraint: this = expr  
     * @param expr
     */
    public void addConstraint(Expression expr)
    {
        layout.addConstraint(var, expr);
    }
    
    Variable getVar()
    {
        return var;
    }

    @Override
    public Expression mul(double factor) {
        return var.mul(factor);
    }
    
    @Override
    public List<Expression> getTerms() {
        return var.getTerms();
    }    
}
