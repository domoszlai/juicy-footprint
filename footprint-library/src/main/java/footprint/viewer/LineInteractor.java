package footprint.viewer;

import footprint.layout.Layout;
import javafx.scene.paint.Color;

/**
 *
 * @author dlacko
 */
class LineInteractor extends ShapeInteractor {
    
    private final footprint.layout.Line line;
    
    LineInteractor(footprint.layout.Line line) {
        this.line = line;
    }

    @Override
    public javafx.scene.Node getVisualShapes(Layout layout, double scale) {
        javafx.scene.shape.Line l = new javafx.scene.shape.Line();
        l.setStartX(layout.getValue(line.getP1().getX()));
        l.setStartY(layout.getValue(line.getP1().getY()));
        l.setEndX(layout.getValue(line.getP2().getX()));
        l.setEndY(layout.getValue(line.getP2().getY()));
        l.setStroke(Color.BLACK);
        l.setStrokeWidth(5/scale);
        l.setOpacity(0.5);
        return l;
    }
}
