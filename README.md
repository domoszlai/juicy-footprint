Juicy-footprint: An SMD Footprint designer DSL
==================================

[![Travis](https://travis-ci.org/domoszlai/juicy-footprint.svg?branch=master)](http://travis-ci.org/domoszlai/juicy-footprint)

## Synopsis

<img align="right" width="345" height="271" src="https://github.com/domoszlai/juicy-footprint/blob/master/sample-mcusb-java/docs/pcb_layout.png">

Juicy-footprint is a domain specific language written in/for Java and Scala to design/reconstruct SMD
footprints from the Recommended PCB Layout of the datasheet of an SMD component. Recommended PCB Layouts
are usually given as engineering drawings where the distances between the parts are relative to each other.
Most EDA applications, however, e.g. Eagle, requires SMD footprints to be given in absolute coordinates. 

Figuring out absolute coordinates from engineering drawings can be exhausting and error-prone. Juicy-footprint is designed
to help with resolving this impedance mismatch. The drawings can be directly represented with the Juicy-footprint DSL,
which, when executed, provides absolute coordinates and displays the footprint.

## Installation

The easiest way to install is to download one of the pre-built packages from [releases](https://github.com/domoszlai/juicy-footprint/releases).

Alternatively, you can build from the source code as follows:

* Install [Gradle](https://gradle.org/install/) if you do not have it yet
* `$ git clone https://github.com/domoszlai/juicy-footprint.git`
* `$ gradle build`

## The DSL

With juicy-footprint one creates shapes and defines relations between their properties (called *constraints*). The available shapes and their properties are 
the following:

* **Variable:** relations can be defined between variables
* **Point**
  * x: Variable
  * y: Variable
* **HorizontalLine, VerticalLine** 
  * p1: Point
  * p2: Point
  * length: Variable 
* **Rect**
  * top: HorizontalLine
  * bottom: HorizontalLine
  * left: VerticalLine  
  * right: VerticalLine  
  * width: Variable
  * height: Variable
* **Hole**
  * top: Point
  * bottom: Point
  * left: Point
  * right: Point
  * center: Point
  * radius: Variable
* **Pad**
  * topLeft: Point
  * topRight: Point
  * bottomReft: Point
  * bottomRight: Point
  * center: Point
  * centerTop: Point
  * centerBottom: Point
  * centerLeft: Point 
  * centerRight: Point  
  * width: Variable
  * height: Variable

<img align="right" width="320" height="70" src="https://github.com/domoszlai/juicy-footprint/blob/master/sample-mcusb-java/docs/generated_layout.png">
  
The constraints must be linear, only addition and multiplication with a constant are allowed. 
As a basic example, the following pseudocode creates two pads the same size, and defines a distance of 6.4mm between their center points:   

```
var a = createPad()
a.width = 1.5
a.height = 1
var b = createPad()
b.width = a.width
b.height = a.height

b.centerTop.x = a.centerTop.x + 6.4
b.centerTop.y = a.centerTop.y
```  
  
### Java

The same code in Java would be as follows:

```java
Layouter l = new Layouter();

Pad a = l.createPad("A", 1.5, 1);
Pad b = l.createPad("B", a.getWidth(), a.getHeight());

b.getCenterTop().addConstraint(a.getCenterTop(), 6.4, 0);
```

Unfortunately the lack of some language features, e.g. operator overloading and properties, in Java, makes the code
unintuitive and cumbersome. 

Subproject [sample-mcusb-java](https://github.com/domoszlai/juicy-footprint/blob/master/sample-mcusb-java/src/main/java/Main.java)
describes the footprint of a [micro USB connector](https://github.com/domoszlai/juicy-footprint/blob/master/sample-mcusb-java/docs/(t-t)_mcusb-b-s05pfhsbth.pdf)
as a full-fledged example. 

### Scala  

The same code in Scala is more intuitive thanks to its advanced language features:

```scala
Layouter l = new Layouter();

val a = l.createPad("A", 1.5, 1);
val b = l.createPad("B", a.width, a.height);

b.centerTop ~= a.centerTop + (6.4, 0);
```

Subproject [sample-mcusb-scala](https://github.com/domoszlai/juicy-footprint/blob/master/sample-mcusb-scala/src/main/scala/Main.java)
describes the footprint of a [micro USB connector](https://github.com/domoszlai/juicy-footprint/blob/master/sample-mcusb-java/docs/(t-t)_mcusb-b-s05pfhsbth.pdf)
as a full-fledged example. 
  
## Implementation

Juicy-footprint is based on the [EJML](ejml.org) linear algebra library to solve the linear equation system defined by the constraints
between the variables. It uses javafx to display the footprints.
