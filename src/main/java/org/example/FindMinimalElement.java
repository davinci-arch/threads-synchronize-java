package org.example;

public class FindMinimalElement extends Thread{

    private int[] array;
    private ExecuteTask task;
    private int startIdx, endIdx;
    // object to set an minmal value;
    public FindMinimalElement(int[] array, ExecuteTask task, int startIdx, int endIdx) {
        this.array = array;
        this.task = task;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
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

    }
}
