import javafx.application.Application;

import footprint.engine.Expression;
import footprint.engine.Const;
import footprint.layout.Point;
import footprint.layout.Layout;
import footprint.layout.Layouter;
import footprint.layout.Line;
import footprint.layout.Pad;
import footprint.viewer.Viewer;

import FootprintExtensions._

class TranslatedPoint(val p: Point, val expr1: Expression, val expr2: Expression) {
}

object FootprintExtensions {

   implicit def double2Expression(x: Double) = new Const(x)

   implicit class ExpressionExtension(e: Expression)
   {
		def +(that: Expression) = e.add(that);
		def -(that: Expression) = e.subtract(that);
		def *(that: Double) = e.mul(that);
		def unary_-() = e.negate();
   }
   
   implicit class PointExtension(p: Point)
   {
		val x = p.getX();
		val y = p.getY();
		
		def +(that: (Expression, Expression)) = new TranslatedPoint(p, that._1, that._2);
	
		def ~=(that: (Expression, Expression)) = p.addConstraint(that._1, that._2);
		def ~=(that: TranslatedPoint) = p.addConstraint(that.p, that.expr1, that.expr2);
   }
}

class Main extends Viewer {

  def onLayout():Layout = {
   
     val l = new Layouter();
	 
	 val p1 = l.createPoint("P1"); 
	 val p2 = l.createPoint("P2"); 
	 //p.addConstraint(0, 0);
	 p1 ~= (0, 0);
	 p2 ~= p1 + (10, 10)
      
	 //System.out.println(p1.x); 
	  
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
 