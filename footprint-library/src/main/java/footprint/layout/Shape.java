package footprint.layout;

import footprint.engine.EngineMismatchException;
import javafx.geometry.Bounds;

/**
 *
 * @author dlacko
 */
public abstract class Shape {
    
    private final String name;
    private final Layouter layout;
    
    Shape(Layouter layout, String name)
    {
        this.name = name;
        this.layout = layout;
    }
    
    public String getName()
    {
        return name;
    }
    
    Layouter getLayout()
    {
        return layout;
    }

    public abstract Bounds getBounds(Layout layout);
    protected abstract void generateConstraints() throws EngineMismatchException; 
}
