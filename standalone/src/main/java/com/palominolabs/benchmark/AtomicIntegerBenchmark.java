package com.palominolabs.benchmark;

import java.util.concurrent.atomic.AtomicInteger;

final class AtomicIntegerBenchmark extends IncrementingBenchmark {
    AtomicInteger counter;

    public AtomicIntegerBenchmark(int numThreads) {
        super(numThreads);
    }

    @Override
    protected void initializeCounter() {
        counter = new AtomicInteger();
    }

    @Override
    protected void incrementCounter() {
        counter.incrementAndGet();
    }

    @Override
    protected void clearCounter() {
        counter.set(0);
    }

    @Override
    protected Long getCounterValue() {
        return counter.longValue();
    }

    public static void main(String[] args) {
        AtomicIntegerBenchmark longAdderBenchmark = new AtomicIntegerBenchmark(getNumThreads(args));
        try {
            longAdderBenchmark.benchmark();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
