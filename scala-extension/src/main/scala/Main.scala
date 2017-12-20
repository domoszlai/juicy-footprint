import javafx.application.Application;

import footprint.layout.Layout;
import footprint.layout.Layouter;
import footprint.layout.Line;
import footprint.layout.Pad;
import footprint.viewer.Viewer;

class Main extends Viewer {

  def onLayout():Layout = {
   
     val l = new Layouter();
	 
	 val p = l.createPoint("P"); 
	 p.addConstraint(0, 0);
      
     var layout = l.generate();
     System.out.println(layout);
     return layout;     
   }

}

object HelloWorld {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[Main], args: _*)
  }
}
 