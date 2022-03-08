 ___________________________________________________
* GMIT - Dept. Computer Science & Applied Physics   *
*                                                   *
*           Image Filtering System V0.1             *
*     H.Dip in Science (Software Development)       *
*               Hazel O'Driscoll                    *
*___________________________________________________*



Running of the Application:

The application takes in an image thru user input and then saves it under "./in.png". 
This must be done first before attempted to access any filters, and is coded within the ImageWriter class. 
The user is given multiple different options on what they would like to do to the image, such as select filter, extra options or terminate.
The class ImageFilter takes in user input as to which of the 11 filters from project spec they wish to apply, and feeds this input into the
filter method. 
The class Extras and it's associated CreateYourOwn, Rotate180 and Greyscale are all extra features added to the application.
The class Runner is only there to initialise the Menu class.
The class SaveFile takes in the output of each image-altering class and saves it under the user prompted name plus ".png".

If you wish to change the image, re-enter option [1] from the main menu and it will override the previously saved image.

Extras:

Added new switch statement to allow user to pick the filter choice instead of hardcoding each filter. 
Added modulus in filter method to prevent exceptions arising from the edges.
Added a second menu system for selecting filters and selecting Extras, also using the new switch statement. 
Added exception handling for user inputs so application doesn't crash out, such as invalid numbers input and for NullPointerException.
In Extras Menu there is the option to go back to the Main Menu if uninterested in using any of the extra features.
Added the option to save a file under a specific name, given by user input.
Fixed issue on IOException on invalid file name, loops until correct filepath is given, informing user of their mistake. 
Added class which allows the user to create their own matrix/filter, by entering the kernel size and then it's values.
The Create-your-own class also includes options to change the multipication factor of the filter and the bias for brightness.  
Added GreyScale class which takes in a coloured image and changes it to greyscale, using java.awt.color import. 
Added Rotate180 class which will flip the input image upsidedown.
Added SaveFile class to take in and save the filtered image from each class, saving it under the desired filename thru user input.
Added shortMenu method in Menu class for ease of access to shortened version of the menu after accessing long output elements of the application.
Converted the kernel class to enums and implemented it into the image filtering process.


I have used the following websites in research and code snippets:

Articles/Blogs/Lectures:

https://web.stanford.edu/class/archive/cs/cs106a/cs106a.1178/lectures/Lecture17/Lecture17.pdf
Stanford Computer Science and Marty Stepp, Lecture 17 - 2D Arrays and Images

https://www.cs.swarthmore.edu/~newhall/imagemanip/ 
Two Image Processing Projects for a CS1 Course
Richard Wicentowski and Tia Newhall
Computer Science Dept. Swarthmore College

https://www.codingame.com/playgrounds/2524/basic-image-manipulation/filtering 
CodinGame, Basic Image Manipulation

http://tech.abdulfatir.com/2014/05/kernel-image-processing.html?m=1 
Of Bits and Pieces, Kernel Image Processing : Image Filters (with Java Code)
Used code for adding extra option to choose your own kernel values.
Used code snippets for ImageFilter class.
 
https://naushadsblog.wordpress.com/2014/04/25/image-processing-and-computer-vision-in-java-image-filtering-part1/ 
NAUSHAD1290, Image Processing and Computer vision in java (Image Filtering part1), APRIL 25 2014
For aid on code for looping the kernel with pixels.

https://stackoverflow.com/questions/21027520/how-do-i-create-multiple-two-dimensional-arrays-in-an-enum 
Enums and 2D Arrays

https://stackoverflow.com/questions/33353058/how-does-one-correctly-apply-filters-to-an-image-array
Applying filters to an image array

https://stackoverflow.com/questions/19046625/bluring-a-java-buffered-image
Blurring a Java buffered image

https://en.wikipedia.org/wiki/Kernel_(image_processing)
https://en.wikipedia.org/wiki/Convolution

Videos:

https://www.youtube.com/watch?v=EmtU0eloTlE
10.4: Pixels! (The Pixels Array) - Processing Tutorial
The Coding Train
Jul 24, 2015

https://www.youtube.com/watch?v=j-ZLDEnhT3Q
10.5: Image Processing with Pixels - Processing Tutorial
The Coding Train
Jul 24, 2015

https://www.youtube.com/watch?v=luBSuon_Y5Q
Java Tutorial - GrayScale Image Filter
Zoran Davidović
Oct 19, 2017

https://www.youtube.com/watch?v=RLHG1dR3TsI
Java Tutorial - Image rotation
Zoran Davidović
Oct 17, 2017
