package footprint.engine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlacko
 */
public class CompoundExpression extends Expression {

    private final List<Expression> terms;

    CompoundExpression(List<Expression> terms) 
    {
        this.terms = terms;
    }
        
    CompoundExpression(Expression expr, Expression addExpr) 
    {
        terms = new ArrayList<>(expr.getTerms());

        // This step ensures that the list always contains primitive Expressions only
        // and it also gives the opportunity the a 3th party expression to unwrap itself
        addExpr.getTerms().forEach((term) -> {
            terms.add(term);
        });
    }
    
    @Override
    public Expression mul(double newFactor)
    {
        List<Expression> nt = new ArrayList<>(terms.size());
        
        terms.forEach((term) -> {
            nt.add(term.mul(newFactor));
        });
        
        return new CompoundExpression(nt);
    }    
    
    @Override
    public List<Expression> getTerms() {
        return terms;
    }
    
    @Override
    public String toString()
    {
        StringBuilder b = new StringBuilder();
        
        boolean first = true;
        
        for(Expression term : terms)
        {
            if(!first)
            {
                b.append((" + "));
            }
            else
            {
                first = false;
            }
            
            b.append(term.toString());
        }
        
        return b.toString();
    }    
}
