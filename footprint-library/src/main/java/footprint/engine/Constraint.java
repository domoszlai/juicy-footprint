package footprint.engine;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author dlacko
 */
class Constraint {

    final private Expression expr;
    final private double val;
    
    Constraint(Expression expr, double val)
    {
        this.expr = expr;
        this.val = val;
    }
        
    void updateA(SimpleMatrix m, int row)
    {
        for(Expression term : expr.getTerms())
        {
            if(term instanceof Variable)
            {
                Variable var = (Variable)term;
                
                int col = var.getIndex();
                m.set(row, col, m.get(row, col) + 1);
            }
            else if(term instanceof Const)
            {
                // skip
            }
            else if(term instanceof MulExpression)
            {
                MulExpression me = (MulExpression) term;
                int col = me.getVar().getIndex();
                m.set(row, col, m.get(row, col) + me.getFactor());
            }
            else // Shouldn't be, but good to know if a mistake was made
            {
                throw new RuntimeException("Unexpected compund expression");
            }
        }
    }

    double getConstant()
    {
        double ret = val;
        
        for(Expression term : expr.getTerms())
        {
            if(term instanceof Const)
            {
                ret -= ((Const)term).getval();
            }
        }
        
        return ret;
    }
    
    @Override
    public String toString()
    {
        return expr.toString() + " = " + Double.toString(val);
    }
    
}
