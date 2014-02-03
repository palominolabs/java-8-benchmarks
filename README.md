Benchmarks of new Java 8 things

Base JMH benchmark code from https://bitbucket.org/marshallpierce/gradle-jmh-demo

# Quick start

```
./gradlew shadowJar
java -jar build/distributions/microbenchmarks.jar
```
# Using JMH from Gradle

[JMH](http://openjdk.java.net/projects/code-tools/jmh/) provides a maven archetype out of the box for setting up benchmarking projects. For those of us that have moved on to Gradle, this project shows a toy benchmark with JMH integrated into the Gradle build process to end up with

It uses the following Gradle plugins:

- [gradle-apt-plugin](https://github.com/Jimdo/gradle-apt-plugin) to enable benchmark code gen
- [shadow](https://github.com/johnrengelman/shadow) to generate an all-in-one jar
