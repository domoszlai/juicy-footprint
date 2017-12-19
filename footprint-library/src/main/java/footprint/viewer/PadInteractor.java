package footprint.viewer;

import footprint.layout.Layout;
import javafx.scene.paint.Color;

/**
 *
 * @author dlacko
 */
class PadInteractor extends ShapeInteractor {
    
    private final footprint.layout.Pad pad;
    
    PadInteractor(footprint.layout.Pad pad) {
        this.pad = pad;
    }

    @Override
    public javafx.scene.Node getVisualShapes(Layout layout, double scale) {
        javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle();
        r.setX(scale);
        r.setX(layout.getValue(pad.getTopLeft().getX()));
        r.setY(layout.getValue(pad.getTopLeft().getY()));
        r.setWidth(layout.getValue(pad.getWidth()));
        r.setHeight(layout.getValue(pad.getHeight()));
        r.setFill(Color.RED);
        r.setOpacity(0.5);        
        return r;
    }
}
