/*
 * Original code from http://minddotout.wordpress.com/2013/05/11/java-8-concurrency-longadder/
 */
package com.palominolabs.benchmark;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.text.NumberFormat;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

abstract class IncrementingBenchmark {
    protected final int addCount = 100000000;
    private final int runCount = 20;

    private final int numThreads;
    private final CyclicBarrier barrier;

    private final Thread[] threads;
    private final DescriptiveStatistics statistics;

    public IncrementingBenchmark(int numThreads) {
        this.numThreads = numThreads;
        barrier = new CyclicBarrier(numThreads + 1);

        threads = new Thread[numThreads];
        statistics = new DescriptiveStatistics(runCount);
    }

    protected abstract void initializeCounter();

    protected abstract void incrementCounter();

    protected abstract void clearCounter();

    protected abstract long getCounterValue();

    /**
     * Get the number of threads, the only command line argument
     *
     * @param args command line arguments array
     * @return The number of threads to run, or explode if not provided
     */
    protected static int getNumThreads(String[] args) {
        int numThreads = 1;
        try {
            numThreads = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Pass number of threads on command line; using default (1)");
        }
        return numThreads;
    }

    private IncrementingBenchmarkThread createThread() {
        return new IncrementingBenchmarkThread(this);
    }

    public void benchmark() throws Exception {
        System.out.println("Performing " + NumberFormat.getInstance().format(addCount) + " increments with " + numThreads + " threads");

        System.out.println("Warming up (" + runCount + " rounds)");
        doRuns();

        // Clear measurements
        statistics.clear();

        System.out.println("Benchmark runs (" + runCount + " rounds)");
        doRuns();

        System.out.printf("Mean: %.0f ops/ms, stdev: %.0f ops/ms (min: %.0f, max: %.0f)\n", statistics.getMean(), statistics.getStandardDeviation(), statistics.getMin(), statistics.getMax());
    }

    private void doRuns() throws Exception {
        initializeCounter();

        for (int runNumber = 0; runNumber < runCount; runNumber++) {
            System.out.printf("Iteration %3d: ", runNumber);
            clearCounter();

            createAndStartThreads();
            long startTime = System.nanoTime();

            // Trigger start
            waitForBarrier();
            // Wait until all threads are finished
            waitForBarrier();
            // Get final value inside of timing
            long finalValue = getCounterValue();

            long endTime = System.nanoTime();
            join();

            double duration = (endTime - startTime) / 1000000.0;
            double operationsPerMillisecond = finalValue / duration;
            System.out.printf("%6.0f ops/ms (%.0f ms)\n", operationsPerMillisecond, duration);
            statistics.addValue(operationsPerMillisecond);

            Thread.sleep(500);
        }
    }

    private void createAndStartThreads() throws Exception {
        for (int i = 0; i < numThreads; i++) {
            IncrementingBenchmarkThread thread = createThread();
            threads[i] = new Thread(thread);
            threads[i].start();
        }
    }

    protected void waitForBarrier() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void join() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
