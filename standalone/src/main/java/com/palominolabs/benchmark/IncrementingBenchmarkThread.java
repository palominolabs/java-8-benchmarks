package com.palominolabs.benchmark;

class IncrementingBenchmarkThread implements Runnable {
    protected IncrementingBenchmark controller;

    IncrementingBenchmarkThread(IncrementingBenchmark controller) {
        this.controller = controller;
    }

    public void run() {
        controller.waitForBarrier();
        for (int i = 0; i < controller.addCount; i++) {
            controller.incrementCounter();
        }
        controller.waitForBarrier();
    }
}
