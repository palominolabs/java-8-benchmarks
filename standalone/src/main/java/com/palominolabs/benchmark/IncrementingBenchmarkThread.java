package com.palominolabs.benchmark;

abstract class IncrementingBenchmarkThread implements Runnable {
    protected IncrementingBenchmark controller;

    IncrementingBenchmarkThread(IncrementingBenchmark controller) {
        this.controller = controller;
    }
}
