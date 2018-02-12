# Electric Charge Simulation in Java
Electric charge simulation comprises of a physics simulation program where its main focus is simulating electric charges and electric forces.

Download the [program](https://raw.githubusercontent.com/arlindiDev/ElectricChargeSimulation/master/app/out/artifacts/app_jar/app.jar) .jar file.

Download my [thesis paper](https://raw.githubusercontent.com/arlindiDev/ElectricChargeSimulation/master/thesis.docx) for the simulation.

![](https://raw.githubusercontent.com/arlindiDev/ElectricChargeSimulation/master/simulation.png)


Description
--------
Consider a professor lecturing physics inclass. The class would be incomplete without the classrooms laboratory which holds different equipment which is more than necessary to perform laboratory examples. Our problem is that there is a lack of physics simulation software applications which would aid in the student's learning process in universities, colleges or high schools that teach physics mainly subjects for electricity, electric charges and electric fields.

Here is a brief introduction to the goals of the simulation:

•	Determine the variables that affect how the electric charges interact and move.

•	Describe how charged bodies will interact with each other.

•	Describe the strength of the electric field around an electric charge.

To simulate real world phenomenon we need to represent time as our simulation clock, each time the simulation clock ticks new values for the properties are calculated for our world objects, such as new positions, electric charge, directions, force magnitude, acceleration. 
Given the time which is infinite, the simulation can never run out of time, but the simulation has a starting point and has an ending point,  both of which we can declare and chose when to start, pause or finish the simulation clock. In the real world, objects are constantly moving and have different forces applied to them at all times which means all properties are recalculated all the time. In our simulation we have to simulate the time and simulate the recalculations which cannot happen all the time due to processing power limitations, but let’s say the calculations are done every 1 second or 500 milliseconds a value that we can chose this is called the refresh rate. We’ll use a refresh rate of 40hz which will give a frame rate of 25 that makes the simulation graphics run smoothly, and the recalculations may not be too processor intensive.

After each tick of the world clock all properties of objects will be recalculated, so we first need to know what properties do the objects have, in physics we can think of objects as matter and matter has different properties and states. 

License
--------
The Simulation is licensed under the [The MIT License (MIT)](https://opensource.org/licenses/MIT).
```
  Copyright (c) 2016 Arlind Hajredinaj

  Permission is hereby granted, free of charge,
  to any person obtaining a copy of this software and associated documentation files (the "Software"),
  to deal in the Software without restriction,
  including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
  and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
  subject to the following conditions:

  The above copyright notice and this permission notice shall be
  included in all copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
