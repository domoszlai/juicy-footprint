package footprint.viewer;

import footprint.layout.Layout;
import footprint.layout.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author dlacko
 */
class PointInteractor extends ShapeInteractor {
    
    private final Point point;
    
    PointInteractor(Point point) {
        this.point = point;
    }

    @Override
    public javafx.scene.Node getVisualShapes(Layout layout, double scale) {
        Circle c = new Circle();
        c.setCenterX(layout.getValue(point.getX()));
        c.setCenterY(layout.getValue(point.getY()));
        c.setRadius(2/scale);
        c.setFill(Color.BLUE);
        return c;
    }
}
