import javafx.application.Application;

import footprint.engine.Expression;
import footprint.engine.Const;
import footprint.layout.Point;
import footprint.layout.Layout;
import footprint.layout.Layouter;
import footprint.layout.Line;
import footprint.layout.Pad;
import footprint.layout.Rect;
import footprint.layout.Hole;
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
		def /(that: Double) = e.mul(1.0/that);
		def unary_-() = e.negate();
		
		def ~=(that: Expression) = e.addConstraint(that);		
   }

   implicit class HoleExtension(h: Hole)
   {
		val top = h.getTop();
        val bottom = h.getBottom();
		val left = h.getLeft();
		val right = h.getRight();
		val center = h.getCenter();
		val radius = h.getRadius();
   }
   
   implicit class RectExtension(r: Rect)
   {
		val top = r.getTop();
        val bottom = r.getBottom();
		val left = r.getLeft();
		val right = r.getRight();
		val width = r.getWidth();
		val height = r.getHeight();
   }
   
   implicit class LineExtension(l: Line)
   {
		val p1 = l.getP1();
		val p2 = l.getP2();
		val length = l.getLength();
   }
   
   implicit class PadExtension(p: Pad)
   {
		val height = p.getHeight();
		val width = p.getWidth();
		
        val topLeft = p.getTopLeft();
        val topRight = p.getTopRight();
        val bottomRight = p.getBottomRight();
        val bottomLeft = p.getBottomLeft();
        
        val center = p.getCenter();
        val centerTop = p.getCenterTop();
        val centerBottom = p.getCenterBottom();
        val centerLeft = p.getCenterLeft();
        val centerRight = p.getCenterRight();
   }
         
   implicit class PointExtension(p: Point)
   {
		val x = p.getX();
		val y = p.getY();
		
		def +(that: (Expression, Expression)) = new TranslatedPoint(p, that._1, that._2);
		def -(that: (Expression, Expression)) = new TranslatedPoint(p, that._1.negate(), that._2.negate());
	
		def ~=(that: (Expression, Expression)) = p.addConstraint(that._1, that._2);
		def ~=(that: TranslatedPoint) = p.addConstraint(that.p, that.expr1, that.expr2);
		def ~=(that: Point) = p.addConstraint(that.x, that.y);
   }
}

class Main extends Viewer {

  def onLayout():Layout = {
   
	val l = new Layouter();
	 
	// A is the top left pad	 
	// 1.5 is a guess, could not find in the spec	  
	val a = l.createPad("A", 1.5, 1);
	// B is the top right pad
	val b = l.createPad("B", a.width, a.height);
	 
	b.centerTop ~= a.centerTop + (6.4, 0); 
	 
	val p1 = l.createPad("P1", 0.4, 1.35);
	val p2 = l.createPad("P2", 0.4, 1.35);
	val p3 = l.createPad("P3", 0.4, 1.35);
	val p4 = l.createPad("P4", 0.4, 1.35);
	val p5 = l.createPad("P5", 0.4, 1.35);
	
	p2.centerTop ~= p3.centerTop - (0.65, 0);
	p1.centerTop ~= p2.centerTop - (0.65, 0);
	p4.centerTop ~= p3.centerTop + (0.65, 0);
	p5.centerTop ~= p4.centerTop + (0.65, 0);
	
	// Center of P3 is the exactly in the middle of A and B
	p3.centerBottom ~= a.centerBottom + ((b.centerBottom.x - a.centerBottom.x) / 2, 0); 
		
	// The lower two pads with the oval holes (C and D; holes are C.Inside, D.Inside)
	val c_inner = l.createPad("C.Inner", 0.45, 1.55);	
	val c_outer = l.createPad("C.Outer");
	val d_inner = l.createPad("D.Inner", 0.45, 1.55);	
	val d_outer = l.createPad("D.Outer");
	
	c_outer.center ~= c_inner.center;        
	c_outer.height ~= 2.15;
	d_outer.center ~= d_inner.center;        
	d_outer.height ~= 2.15;
	
    // The difference between the widths and heights is equal
    c_outer.height - c_inner.height ~= c_outer.width - c_inner.width;
    d_outer.height - d_inner.height ~= d_outer.width - d_inner.width;

	// Relative position of C and D to the other pads
    d_inner.center.y ~= p5.centerTop.y + 3.35;	
	c_inner.center.y ~= p5.centerTop.y + 3.35;	
    d_inner.center ~= c_inner.center + (6.45, 0);        
	// Still need a position for X. Center it relative to A and B
    d_outer.centerLeft.x - a.centerLeft.x ~= b.centerRight.x - c_outer.centerRight.x;
    
	// PCB edge line
    val edge = l.createHorizontalLine("PCB edge");
    edge.p1 ~= a.bottomLeft +  (0, 3.45);
    edge.length ~= b.bottomRight.x - a.bottomLeft.x;
	
	// Finally: the origin
	a.topLeft ~= (0, 0);	
	
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
 