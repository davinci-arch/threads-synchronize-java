package org.example;

import java.util.concurrent.CountDownLatch;

public class FindMinimalElement extends Thread{

    private static int counter = 0;
    private int[] array;
    private ExecuteTask task;
    private int startIdx, endIdx;
    private CountDownLatch countDownLatch;

    public FindMinimalElement(int[] array, ExecuteTask task, int startIdx, int endIdx, CountDownLatch countDownLatch) {
        this.array = array;
        this.task = task;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        int minValue = array[startIdx];
        int minValIdx = 0;
        for (int i = startIdx; i < endIdx; i++) {
            if (minValue > array[i]) {
                minValue = array[i];
                minValIdx = i;
            }
        }

        if (task.getMinValue() > minValue) {
            task.setMinValue(minValue);
            task.setIndexVal(minValIdx);
        }
        countDownLatch.countDown();

    }
}
