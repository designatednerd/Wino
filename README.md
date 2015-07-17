#Wino

Android Wine-Tasting Note Taker / Example App for Testing Talk 

##JUnit 4 Example

[WineTastingTests](app/src/androidTest/java/com/designatednerd/wino/model/WineTastingTests.java) is a really basic example of setting up JUnit4-style tests. 

##Parameterized Example

[ParameterizedWineNameTests](app/src/androidTest/java/com/designatednerd/wino/model/ParameterizedWineNameTests.java) is an example of how to use parameterized tests to run the same test repeatedly using different parameters to make sure the same object can handle many types of input quickly. 

##Espresso Examples

[WineTastingDetailUITests](app/src/androidTest/java/com/designatednerd/wino/ui/WineTastingDetailUITests.java) is an example of how to set up individual UI tests with Espresso. 

[EspressoHelpers](app/src/androidTest/java/com/designatednerd/wino/ui/EspressoHelpers.java) is an example of how to compose pieces of Espresso functionality into custom actions which allow you to write more expressive code. 