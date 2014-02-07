package com.palominolabs.benchmark.java8;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.atomic.LongAdder;

@State(Scope.Benchmark)
public class LongAdderBenchmark {
    private LongAdder longAdder = new LongAdder();

    @GenerateMicroBenchmark
    public void longAdderBenchmark() {
        longAdder.increment();
    }
}
