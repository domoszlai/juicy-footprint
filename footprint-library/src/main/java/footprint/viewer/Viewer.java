package footprint.viewer;

import footprint.layout.Layout;
import footprint.layout.Shape;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author dlacko
 */
public abstract class Viewer extends Application {
    
    public abstract Layout onLayout() throws Exception;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
     
        Layout layout = onLayout();
        
        // Ask for screen size. Use two third to look nice
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        double width = screen.getWidth() * 2 / 3d;
        double height = screen.getHeight() * 2 / 3d;
        
        // Calculate layout size
        Bounds layoutBounds = layout.getBounds();
        // Calculate scale to respect a small margin (40px)
        double scaleX = (width - 40) / layoutBounds.getWidth();
        double scaleY = (height - 40) / layoutBounds.getHeight();
        double scale = Math.min(scaleX, scaleY);
        double translateX = -layoutBounds.getMinX();
        double translateY = -layoutBounds.getMinY();
                
        primaryStage.setTitle("Generated layout");

        Group root = new Group();
        // Position drawings in the middle of the screen
        root.getTransforms().add(
                new Translate((width-layout.getBounds().getWidth()*scale)/2, 
                              (height-layout.getBounds().getHeight()*scale)/2));        
        // Translate the drawings to the top left corner then scale up
        root.getTransforms().add(new Scale(scale, scale, 0, 0));
        root.getTransforms().add(new Translate(translateX, translateY));        
        
        for(Shape shape : layout.getShapes())
        {
            ShapeInteractor interactor = ShapeInteractor.createInteractor(shape);
            javafx.scene.Node g = interactor.getVisualShapes(layout, scale);
            root.getChildren().add(g);
        }        
        
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
