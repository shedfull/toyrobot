# Toy Robot

To build you need at least Java 8 and Gradle.

`./gradlew clean build`

To run:

`java -jar ./build/libs/toyrobot-1.0.jar`

You enter commands via standard in, any output is written to standard out. Unrecognised commands do nothing (no error messages for the user)

Implementation is based on the Command Pattern, with a simple CommandParser to parse input strings in to Command objects that act on a domain model consisting of a Table, Robot, Heading and Position

Commands are hardwired into the CommandParser, in a more sophisticated implementation
 they could be injected via a DI framework
