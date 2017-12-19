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
        Pad a = l.createPad("A");
        a.getHeight().addConstraint(1);
        a.getWidth().addConstraint(1.5); // This is a guess, could not find in the spec
        
        // B is the top right pad
        Pad b = l.createPad("B");
        b.getHeight().addConstraint(1);
        b.getWidth().addConstraint(a.getWidth());
        
        b.getCenterTop().addConstraint(a.getCenterTop(), 6.4, 0);
        
        Pad p1 = l.createPad("P1");
        p1.getHeight().addConstraint(1.35);
        p1.getWidth().addConstraint(0.4);
        Pad p2 = l.createPad("P2");
        p2.getHeight().addConstraint(1.35);
        p2.getWidth().addConstraint(0.4);
        Pad p3 = l.createPad("P3");
        p3.getHeight().addConstraint(1.35);
        p3.getWidth().addConstraint(0.4);        
        Pad p4 = l.createPad("P4");
        p4.getHeight().addConstraint(1.35);
        p4.getWidth().addConstraint(0.4);        
        Pad p5 = l.createPad("P5");
        p5.getHeight().addConstraint(1.35);
        p5.getWidth().addConstraint(0.4);        
        
        p2.getCenterTop().addConstraint(p3.getCenterTop(), -0.65, 0);
        p1.getCenterTop().addConstraint(p2.getCenterTop(), -0.65, 0);
        p4.getCenterTop().addConstraint(p3.getCenterTop(), 0.65, 0);
        p5.getCenterTop().addConstraint(p4.getCenterTop(), 0.65, 0);
        
        // Center of P3 is the exaclty in the middle of A and B
        p3.getCenterBottom().addConstraint(a.getCenterBottom(), 
                b.getCenterBottom().getX().subtract(a.getCenterBottom().getX()).mul(0.5), 0); 
        
        a.getTopLeft().addConstraint(0, 0);
                        
        // The lower two pads with the oval hole (C.Inside, D.Inside)
        
        Pad c_outer = l.createPad("C.Outer");
        Pad c_inner = l.createPad("C.Inner");
        
        c_outer.getCenter().addConstraint(c_inner.getCenter());        
        c_inner.getWidth().addConstraint(0.45);
        c_inner.getHeight().addConstraint(1.55);
        c_outer.getHeight().addConstraint(2.15);
        
        // The diffrence between the widths is equal to the difference between heights
        l.addConstraint(c_outer.getHeight().subtract(c_inner.getHeight()), 
                        c_outer.getWidth().subtract(c_inner.getWidth()));
        
        c_inner.getCenter().getY().addConstraint(p5.getCenterTop().getY().add(3.35));
        
        // The same for D
        
        Pad d_outer = l.createPad("D.Outer");
        Pad d_inner = l.createPad("D.Inner");
        
        d_outer.getCenter().addConstraint(d_inner.getCenter());        
        d_inner.getWidth().addConstraint(0.45);
        d_inner.getHeight().addConstraint(1.55);
        d_outer.getHeight().addConstraint(2.15);
        
        // The diffrence between the widths is equal to the difference between heights
        l.addConstraint(d_outer.getHeight().subtract(d_inner.getHeight()), 
                        d_outer.getWidth().subtract(d_inner.getWidth()));
        
        d_inner.getCenter().getY().addConstraint(p5.getCenterTop().getY().add(3.35));

        // Distance between C and D
        
        d_inner.getCenter().addConstraint(c_inner.getCenter(), 6.45, 0);
        
        // Still need a position for X. Center it relative to A and B
        
        l.addConstraint(d_outer.getCenterLeft().getX().subtract(a.getCenterLeft().getX()), 
                        b.getCenterRight().getX().subtract(c_outer.getCenterRight().getX()));
        
        // PCB edge line
        
        Line edge = l.createHorizontalLine("PCB edge");
        edge.getP1().addConstraint(a.getBottomLeft(), 0, 3.45);
        edge.getLength().addConstraint(b.getBottomRight().getX().subtract(a.getBottomLeft().getX()));
        
        // Finally: set the origin
        
        a.getTopLeft().addConstraint(0, 0);
        
        Layout layout = l.generate();
        System.out.println(layout);
        return layout;        
    }
}
