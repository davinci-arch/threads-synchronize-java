package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ExecuteTask {

    private int amountOfThreads;
    private int[] array = new int[1_000];
    private FindMinimalElement[] threads;
    private volatile int minValue = Integer.MAX_VALUE;
    private volatile int indexVal = 0;
    public ExecuteTask(int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
        threads = new FindMinimalElement[amountOfThreads];
    }

    public void execute() {
        generateArray();
        System.out.println(Arrays.toString(array));
        int[] indexesParts = indexesParts();
        System.out.println(Arrays.toString(indexesParts));
        for (int i = 0; i < amountOfThreads; i++) {
            threads[i] = new FindMinimalElement(array,this, indexesParts[i], indexesParts[i+1]);
            threads[i].start();

        }

        boolean allDone = false;
        while (!allDone) {
            for (int i = 0; i < amountOfThreads; i++) {
                if (!threads[i].isAlive()) {
                    allDone = true;
                } else {
                    allDone = false;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Min value: " + minValue);
        System.out.println("Index of min value: " + indexVal);
    }

    private int[] indexesParts() {
        int part = array.length / amountOfThreads;
        int[] indexes = new int[amountOfThreads+1];

        int idx = 0;
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = idx;
            idx += part;
            if (i == indexes.length - 1) {
                indexes[i] = array.length;
            }
        }

        return indexes;
    }

    private void generateArray() {

        Random ranVal = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = ranVal.nextInt(1, 1000);
        }
        array[ranVal.nextInt(0,array.length)] = -1;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setIndexVal(int indexVal) {
        this.indexVal = indexVal;
    }

    public int getMinValue() {
        return minValue;
    }
}
