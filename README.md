Juicy-footprint: An SMD Footprint reconstruction DSL
==================================

[![Travis](https://travis-ci.org/domoszlai/juicy-footprint.svg?branch=master)](http://travis-ci.org/domoszlai/juicy-footprint)

## Synopsis

Juicy-footprint is a domain specific language written in/for Java and Scala to reconstruct SMD
footprints from the Recommended PCB Layout of the datasheet of an SMD component. Recommended PCB Layouts
are usually given as engineering drawings where the distances between the parts are relative to each other.
Most EDA applications, however, e.g. Eagle, requires SMD footprints to be given in absolute coordinates. 

<img align="right" src="https://github.com/domoszlai/juicy-footprint/blob/master/sample-mcusb-java/docs/pcb_layout.png">

Figuring out absolute coordinates from engineering drawings can be exhausting. Juicy-footprint is designed
to help with resolving this impedance mismatch. The drawings can be directly represented with the Juicy-footprint DSL,
which, when executed, provides absolute coordinates and displays the footprint.

## Installation

The easiest way to install is to download one of the pre-built packages from [releases](https://github.com/domoszlai/juicy-footprint/releases).

Alternatively, you can build from the source code as follows:

* Install [Gradle](https://gradle.org/install/) if you do not have it yet
* `$ git clone https://github.com/domoszlai/juicy-footprint.git`
* `$ gradle build`

## Usage



## Implementation

