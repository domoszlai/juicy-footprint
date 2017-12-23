package footprint.extension.scala;

import footprint.engine.Expression;
import footprint.engine.Const;
import footprint.layout.Point;
import footprint.layout.Layout;
import footprint.layout.Layouter;
import footprint.layout.Line;
import footprint.layout.Pad;
import footprint.layout.Rect;
import footprint.layout.Hole;

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
