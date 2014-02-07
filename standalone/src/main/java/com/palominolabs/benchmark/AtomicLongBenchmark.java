package com.palominolabs.benchmark;

import java.util.concurrent.atomic.AtomicLong;

final class AtomicLongBenchmark extends IncrementingBenchmark {
    AtomicLong counter;

    public AtomicLongBenchmark(int numThreads) {
        super(numThreads);
    }

    @Override
    protected void initializeCounter() {
        counter = new AtomicLong();
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
    protected long getCounterValue() {
        return counter.longValue();
    }

    public static void main(String[] args) {
        AtomicLongBenchmark atomicLongBenchmark = new AtomicLongBenchmark(getNumThreads(args));
        try {
            atomicLongBenchmark.benchmark();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
