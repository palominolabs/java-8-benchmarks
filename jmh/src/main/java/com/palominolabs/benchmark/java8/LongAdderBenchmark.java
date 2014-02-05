package com.palominolabs.benchmark.java8;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderBenchmark {
    @State(Scope.Benchmark)
    static class LongAdderState {
        public static final LongAdder longAdder = new LongAdder();
    }

    @State(Scope.Thread)
    public static class LongAdderAction {
        @GenerateMicroBenchmark
        public void longAdderBenchmark() {
            LongAdderState.longAdder.increment();
        }
    }
}
