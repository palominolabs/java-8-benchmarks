package com.palominolabs.benchmark;

import java.util.concurrent.atomic.LongAdder;

final class LongAdderBenchmark extends IncrementingBenchmark {
    LongAdder counter;

    public LongAdderBenchmark(int numThreads) {
        super(numThreads);
    }

    @Override
    protected void initializeCounter() {
        counter = new LongAdder();
    }

    @Override
    protected void incrementCounter() {
        counter.increment();
    }

    @Override
    protected void clearCounter() {
        counter.reset();
    }

    @Override
    protected long getCounterValue() {
        return counter.longValue();
    }

    public static void main(String[] args) {
        LongAdderBenchmark longAdderBenchmark = new LongAdderBenchmark(getNumThreads(args));
        try {
            longAdderBenchmark.benchmark();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
