package footprint.engine;

import java.text.DecimalFormat;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author dlacko
 */
public class Solution {
    
    private final Engine engine;
    private final SimpleMatrix x;
    
    Solution(Engine engine, SimpleMatrix x)
    {
        this.engine = engine;
        this.x = x;
    }
    
    public double getValue(Variable var)
    {
        return x.get(var.getIndex());
    }
    
    @Override
    public String toString()
    {
        StringBuilder b = new StringBuilder();
        DecimalFormat f = new DecimalFormat("######0.##");
        
        for(int i=0; i<x.numRows(); i++)
        {
            b.append(engine.getVariableByIndex(i).getName());
            b.append(" = ");
            b.append(f.format(x.get(i)));
            b.append("\n");
        }  
        
        return b.toString();
    }
    
}
