package com.palominolabs.benchmark.java7;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.atomic.AtomicLong;

@State(Scope.Benchmark)
public class AtomicLongBenchmark {
    private AtomicLong atomicLong = new AtomicLong();
    
    @GenerateMicroBenchmark
    public long atomicLongBenchmark() {
        return atomicLong.getAndIncrement();
    }
}
