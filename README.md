Benchmarks of new Java 8 things

# Running JMH benchmarks

This distribution includes JMH based benchmarks, which will provide the most accurate numbers:

```
./gradlew shadowJar
java -jar jmh/build/distributions/jmh-benchmarks.jar
```

Base JMH benchmark code from https://bitbucket.org/marshallpierce/gradle-jmh-demo

# Standalone benchmarks

There are also standalone benchmarks, suitable for running under perf-stat or other things:

```
./gradlew shadowJar
java -classpath standalone/build/distributions/standalone-benchmarks.jar com.palominolabs.benchmark.AtomicLongBenchmark
java -classpath standalone/build/distributions/standalone-benchmarks.jar com.palominolabs.benchmark.LongAdderBenchmark
```