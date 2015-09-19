------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Connect four Game

PURPOSE OF PROJECT: To demonstrate the level of my skills in Java programming and basic algorithms
implementation on this platform.

VERSION or DATE: 2015-Sep-19
HOW TO START THIS PROJECT:

    1. Build
    Either manually compile all source files in src/main/java or just build the project with Maven 3.x: mvn clean compile

    2. Run

    2.1 UI

    Either start manually compiled class Main (java Main) or run main class with Maven exec-java-plugin. To open
    application UI use Maven: *mvn -Dui-exec exec:java*

    2.2 Test Cases

    There is two main classes, one (com.udacity.connect4game.Main) leads to application UI activation, and the other
    one (com.udacity.connect4game.TestCases) just run matches between my agent implementation and base implementations
    provided by Udacity.

    To run test cases use Maven: *mvn -Dui-testCases exec:java*

AUTHORS: Cyril A. Karpenko

USER INSTRUCTIONS: -
