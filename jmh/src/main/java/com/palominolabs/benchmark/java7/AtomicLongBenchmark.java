package com.palominolabs.benchmark.java7;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongBenchmark {
    @State(Scope.Benchmark)
    static class AtomicLongState {
        public static final AtomicLong atomicLong = new AtomicLong();
    }

    @State(Scope.Thread)
    public static class AtomicLongAction {
        @GenerateMicroBenchmark
        public void atomicLongBenchmark() {
            AtomicLongState.atomicLong.getAndIncrement();
        }
    }
}
