# Electric Charge and Electric Field Simulation in Java
Electric charge simulation comprises of a physics simulation program where its main focus is simulating electric charges and electric forces.

You can citate from this thesis from the [**official University website  citation**](https://knowledgecenter.ubt-uni.net/etd/1156/)

Download
--------
[Program (Simulation)](https://raw.githubusercontent.com/arlindiDev/ElectricChargeSimulation/master/app/out/artifacts/app_jar/app.jar) .jar file.

My [Bachelor Thesis Paper](https://github.com/arlindiDev/Electric-Charge-Simulation-Bachelor-Thesis/blob/master/Thesis.pdf) for the simulation.

Simulation Screenshot
--------
![](https://raw.githubusercontent.com/arlindiDev/ElectricChargeSimulation/master/simulation.png)

Abstract
-------
Simulating physics phenomena is somewhat complicated, depending on what is to be simulated, the laws, the amount of space, and the time. It all breaks down to the scale of the universe that we are going to simulate. We might want to draw objects, which means we need a screen with pixels. A pixel size can be a meter, half a meter, a millimeter, or even down to the smallest distance, the Planck length, which is 1.616199(97)×10−35. We can simulate Newtonian laws, laws of thermodynamics, theory of relativity, particles of light, and more. The more physics we want to add, the more computer power we need. When we add more space and time to the simulation, we will need a larger computer. In another way, time is actually not simulated by computers, but it's imitated. Richard Feynman sets the following rule for simulation: the number of computer elements required to simulate a large physical system is only proportional to the space-time volume of the physical system. To simulate such physics, we will need a quantum computer. To simulate only electric charges and fields, we can use simple computers, where the visible properties of electric charges and fields are described by variables whose states are changed in time by an algorithm.

Description
--------
Consider a professor lecturing on physics in class. The class would be incomplete without a physics laboratory, equipped with various tools and equipment necessary for performing laboratory experiments. However, there is a lack of physics simulation software applications that could aid in the learning process of students in universities, colleges, or high schools that mainly teach subjects related to electricity, electric charges, and electric fields.

Here is a brief introduction to the goals of the simulation:

• Determine the variables that affect the movement and interaction between electric charges.

• Describe how charged bodies will interact with each other.

• Describe the strength of the electric field around an electric charge.

To simulate real-world phenomena, we need to represent time as a simulated clock. Each time the simulated clock ticks, new values for the properties of the objects in the simulation are calculated, such as new positions, electric charge, directions, force magnitude, acceleration, or other physical properties. Given that time is infinite, the simulation can never run out of time. However, the simulation has a starting point and an ending point, both of which can be defined and chosen when to start, pause, or finish the simulation clock. In the real world, objects are constantly moving and have different forces applied to them at all times, which means that all properties are recalculated (changed) continuously. In our simulation, we have to simulate time and the recalculations, which cannot happen all the time due to processing power limitations. Let's say the calculations are done every 1 second or 500 milliseconds, a value that we can choose, called the refresh rate. We will use a refresh rate of 40 Hz, which will give a frame rate of 25, making the simulation graphics run smoothly, and the recalculations may not be too processor-intensive.

After each tick of the world clock, all properties of objects will be recalculated. So, first, we need to know what properties the objects have. In physics, we can think of objects as matter, and matter has different properties and states.

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
