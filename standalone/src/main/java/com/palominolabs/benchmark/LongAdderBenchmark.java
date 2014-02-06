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
    protected Long getCounterValue() {
        return counter.longValue();
    }

    @Override
    protected IncrementingBenchmarkThread createThread() {
        return new LongAdderBenchmarkThread(this);
    }

    public static void main(String[] args) {
        int numThreads = Integer.parseInt(args[0]);
        LongAdderBenchmark longAdderBenchmark = new LongAdderBenchmark(numThreads);
        try {
            longAdderBenchmark.benchmark();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class LongAdderBenchmarkThread extends IncrementingBenchmarkThread {
        LongAdderBenchmarkThread(IncrementingBenchmark controller) {
            super(controller);
        }

        @Override
        public void run() {
            controller.waitForBarrier();
            for (int i = 0; i < controller.addCount; i++) {
                controller.incrementCounter();
            }
            controller.waitForBarrier();
        }
    }
}
