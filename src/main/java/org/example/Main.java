package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int amountOfThreads;

        Scanner in = new Scanner(System.in);
        System.out.print("Write amount of threads: ");
        amountOfThreads = in.nextInt();

        ExecuteTask executeTask = new ExecuteTask(amountOfThreads);
        executeTask.execute();
    }
}