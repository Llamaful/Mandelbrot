# **DSA Fractal Project**
_Made by Nolan & Anant_

### 1. Overview

The [Mandelbrot set](https://en.wikipedia.org/wiki/Mandelbrot_set) is created by **recursively** iterating complex numbers through a special function.

<pre>f<sup>k</sup>(z) = [f<sup>k-1</sup>(z)]<sup>2</sup> + z</pre>

If the resulting number _doesn't_ grow towards infinity, that complex number is in the set. But, because we can't check towards infinity, this algorithm stops after a certain point (max iterations).

To give the image color, the number of iterations before exiting viewing range is mapped to a gradient function.

### 2. How to Use

In the main fractal window, you can click and drag your mouse to move the image. You can also scroll to zoom in or out. When zooming in, the Fast or Detailed preset (see below) is recommended to properly view all the details in the fractal.

### 3. The Settings

Make sure your window is large enough to display all the settings. Each setting is listed below with a description.
| Setting | Description |
|---|---|
| Reset Zoom | This resets the zoom to fit the entire fractal on screen. |
| Resolution | This controls how big the pixels will be rendered as. A resolution of 1 is the highest-resolution and will color every pixel.<br><br>Note that this setting greatly impacts how fast the fractal loads. A resolution of 2 is recommended when zooming.|
| Max-Iteration | This is the maximum number of iterations that will be applied to the fractal. Because color is based on the percentage of iterations, a higher max-iteration will add more contrast to the colors in the image. When zooming in far, increase the max-iteration to see more details. |
| Show Recursion | When enabled, hover your mouse over any point on the image to view how the complex number is iterated. You will note that when hovering over a solid yellow section, the points never go to infinity. |
| Presets | A dropdown menu of useful presets. When zooming in the image, the Fast or Detailed presets are recommended. The UltraHigh setting is good for taking screenshots, but loads very slow. |