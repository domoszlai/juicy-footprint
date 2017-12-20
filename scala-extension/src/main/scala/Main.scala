import javafx.application.Application;

import footprint.layout.Point;
import footprint.layout.Layout;
import footprint.layout.Layouter;
import footprint.layout.Line;
import footprint.layout.Pad;
import footprint.viewer.Viewer;

import Extension._

object Extension {
   implicit class PointExtension(p: Point)
   {
		val x = p.getX();
		val y = p.getY();
   }
}

class Main extends Viewer {

  def onLayout():Layout = {
   
     val l = new Layouter();
	 
	 val p = l.createPoint("P"); 
	 p.addConstraint(0, 0);
      
	 System.out.println(p.x); 
	  
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
 