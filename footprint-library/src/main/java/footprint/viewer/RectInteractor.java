package footprint.viewer;

import footprint.layout.Layout;
import javafx.scene.Group;

/**
 *
 * @author dlacko
 */
class RectInteractor extends ShapeInteractor {
    
    private final footprint.layout.Rect rect;
    
    RectInteractor(footprint.layout.Rect rect) {
        this.rect = rect;
    }

    @Override
    public javafx.scene.Node getVisualShapes(Layout layout, double scale) {
        // Do nothing, children nodes generate the lines
        return new Group();
    }
}
