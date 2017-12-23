import footprint.layout.Layout;
import footprint.layout.Layouter;
import footprint.layout.Line;
import footprint.layout.Pad;
import footprint.viewer.Viewer;

public class Main extends Viewer {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    }
    
    @Override
    public Layout onLayout() throws Exception
    {
        Layouter l = new Layouter();

        // A is the top left pad
		// 1.5 is a guess, could not find in the spec	
        Pad a = l.createPad("A", 1.5, 1);
        // B is the top right pad
        Pad b = l.createPad("B", a.getWidth(), a.getHeight());
        
        b.getCenterTop().addConstraint(a.getCenterTop(), 6.4, 0);
        
        Pad p1 = l.createPad("P1", 0.4, 1.35);
        Pad p2 = l.createPad("P2", 0.4, 1.35);
        Pad p3 = l.createPad("P3", 0.4, 1.35);
        Pad p4 = l.createPad("P4", 0.4, 1.35);
        Pad p5 = l.createPad("P5", 0.4, 1.35);
        
        p2.getCenterTop().addConstraint(p3.getCenterTop(), -0.65, 0);
        p1.getCenterTop().addConstraint(p2.getCenterTop(), -0.65, 0);
        p4.getCenterTop().addConstraint(p3.getCenterTop(), 0.65, 0);
        p5.getCenterTop().addConstraint(p4.getCenterTop(), 0.65, 0);
        
        // Center of P3 is the exactly in the middle of A and B
        p3.getCenterBottom().addConstraint(a.getCenterBottom(), 
                b.getCenterBottom().getX().subtract(a.getCenterBottom().getX()).mul(0.5), 0); 
                                
        // The lower two pads with the oval holes (holes are C.Inside, D.Inside)

        Pad c_inner = l.createPad("C.Inner", 0.45, 1.55);        
        Pad c_outer = l.createPad("C.Outer");
        Pad d_inner = l.createPad("D.Inner", 0.45, 1.55);        
        Pad d_outer = l.createPad("D.Outer");
        
        c_outer.getCenter().addConstraint(c_inner.getCenter());        
        c_outer.getHeight().addConstraint(2.15);
        d_outer.getCenter().addConstraint(d_inner.getCenter());        
        d_outer.getHeight().addConstraint(2.15);
        
        // The difference between the widths and heights is equal
        c_outer.getHeight().subtract(c_inner.getHeight()).addConstraint( 
                        c_outer.getWidth().subtract(c_inner.getWidth()));
        d_outer.getHeight().subtract(d_inner.getHeight()).addConstraint(
                            d_outer.getWidth().subtract(d_inner.getWidth()));
        
		// Relative position of C and D to the other pads		
        c_inner.getCenter().getY().addConstraint(p5.getCenterTop().getY().add(3.35));
        d_inner.getCenter().getY().addConstraint(p5.getCenterTop().getY().add(3.35));
        d_inner.getCenter().addConstraint(c_inner.getCenter(), 6.45, 0);
		// Still need a position for X. Center it relative to A and B		
        d_outer.getCenterLeft().getX().subtract(a.getCenterLeft().getX()).addConstraint( 
                            b.getCenterRight().getX().subtract(c_outer.getCenterRight().getX()));
        
        // PCB edge line        
        Line edge = l.createHorizontalLine("PCB edge");
        edge.getP1().addConstraint(a.getBottomLeft(), 0, 3.45);
        edge.getLength().addConstraint(b.getBottomRight().getX().subtract(a.getBottomLeft().getX()));
        
        // Finally: the origin
        a.getTopLeft().addConstraint(0, 0);
        
        Layout layout = l.generate();
        System.out.println(layout);
        return layout;        
    }
}
