package footprint.engine;

import java.util.ArrayList;
import java.util.HashMap;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author dlacko
 */
public class Engine {
    
    private int nextIndex = 0;
    private final HashMap<Integer,Variable> variables = new HashMap<>();
    private final ArrayList<Constraint> constraints = new ArrayList<>(); 
    
    public Variable createVariable(String name)
    {
        Variable var = new Variable(this, name, nextIndex++);
        variables.put(var.getIndex(), var);
        return var;
    }
        
    public void addConstraint(Expression expr, double val)
    {
        constraints.add(new Constraint(expr, val));
    }

    public void addConstraint(Expression expr1, Expression expr2) throws EngineMismatchException
    {
        Engine exprEngine = Expression.getVerifyEngines(expr1, expr2);
        
        if(exprEngine != null && exprEngine != this)
                        throw new EngineMismatchException();
        
        constraints.add(new Constraint(expr1.add(expr2.negate()), 0));
    }    

    public void clearConstraints()
    {
        constraints.clear();
    }
    
    Variable getVariableByIndex(int index)
    {
        return variables.get(index);
    }
    
    public Solution solve()
    {
        SimpleMatrix A = new SimpleMatrix(constraints.size(),variables.size());
        SimpleMatrix b = new SimpleMatrix(constraints.size(),1);
                
        for(int i=0; i<constraints.size(); i++)
        {
            constraints.get(i).updateA(A, i);
            b.set(i, 0, constraints.get(i).getConstant());
        }
        
        SimpleMatrix x = A.solve(b);
                
        return new Solution(this, x);        
    }
}
