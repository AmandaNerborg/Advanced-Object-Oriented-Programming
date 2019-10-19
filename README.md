# Advanced-Object-Oriented-Programming

Project in advanced object oriented programming VT2019

Project specification consists of creating a framework for a simple board game of puzzle type.
Framework consists of classes: DisplaySimpleBoardGraphics.java, GameSound.java and SimpleBoardGame.java

DisplaySimpleBoardGraphics
The class is used to display a pop-up window with a restart button attached. An object of the class is created by an input of a two dimensional X*X Imageicon array(ImageIcon[][]) and an Array of Eventlisteners. The images of the imageicon array are displayed on the window according thier position in the array. The listeners, which will act as user inputs are, attached to the window. To change the images on the window, another Imageicon array must be provided to the updateGraphics method called by the Object.

GameSound
The class GameSound is used to provide sound output for the framework. An object is created by providing a viable string that translates into a filetype .wav then the sound is then played.

SimpleBoardGame
SimpleBoardGame is the bread and butter of the framework. It is an abstract class with abstract methods. In order to succesfully create an application with this framework, the application must be an extension of the class. In additon the abstract methods must be implemented correctly for the framework to work as intended.
In SimpleBoardGame there are three listeners defined: MouseListener, for inputs using the mouse, KeyListener, for inputs using the keyboard and an ActionListner for a button.
In the constructor of the class an array of EventListeners are put together.

abstract methods:
startBoard()

these are input methods:
leftKeyPressed()
rightKeyPressed()
upKeyPressed()
downKeyPressed()

When one of the arrows on the keyboard is pressed during runtime the listeners will call the according abstract method of the key that was pressed. In that method the user is free to do as they please as long as an imageicon[][] is returned. This array will be used as a representation of what has happend to the board after a move was made. The array will update the graphical window board. By the restriction of the framework there are only implemented inputs for the arrows in the keyboard.

iconClicked()
The abstract method iconClicked will be called by the MouseListener if the mouse was pressed. In addition, during this time the public method getIconClicked is guaranteed to return an integer array of size two containing the position of the icon that was clicked in the board.

In the application at least one type of input must be used in the intended way for inputs to work. If any of the input methods wished to not be used it shall return a NULL value.

There are two examples of applications created by the framework: TicTacToe.java and Sakoban.java which are run by creating an object of the class.

Testing
During the development we constantly tried to test the limitations of our framework by doing manual testing throughout the process.
During the development of the framework we created 2 different applications of the framework, Tictactoe and Sakoban, that would show 2 ends of the framework. By building these applications in parallel we made sure that the framework would work for both types of games.

Testing of the program was performed by making invariances in the beginning of every class in the framework. These invariances were insured by making assertions in the form of pre- and postcondition in fitting methods of the classes.

Results
In the end we managed to implement all funcionality that we first intended. Since the framework is based around the abstract class, abstract methods and other classes which go together with these, it is quite simple when further developing the framework to simply create new abstract methods and suitable classes for new kinds of funcionality.
