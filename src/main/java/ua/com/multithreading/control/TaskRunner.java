package ua.com.multithreading.control;

import ua.com.multithreading.service.HelloFromThread;
import ua.com.multithreading.service.PrimeNumberCounter;
import ua.com.multithreading.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TaskRunner {
    private final static String LINE = "_________________";

    public void start() {
        System.out.println(LINE + "First task" + LINE);
        taskFirst();
        System.out.println(LINE + "Second task" + LINE);
        taskSecond();
    }

    private void taskFirst() {
        HelloFromThread hello = new HelloFromThread();
        hello.helloMethod();
    }

    private void taskSecond() {

        List<Integer> list = new ArrayList<>(Util.uniqueRandom(10, 100));
        Util.printListAndMessage(list, "Generated list of unique numbers:");

        FutureTask<List<Integer>> left = new FutureTask<>(new PrimeNumberCounter(list.subList(0, list.size() / 2)));
        FutureTask<List<Integer>> right = new FutureTask<>(new PrimeNumberCounter(list.subList(list.size() / 2, list.size())));

        Thread t1 = new Thread(left);
        Thread t2 = new Thread(right);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        List<Integer> leftResult = new ArrayList<>();
        List<Integer> rightResult = new ArrayList<>();
        try {
            leftResult = left.get();
            rightResult = right.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (leftResult.size() != 0)
            Util.printListAndMessage(leftResult, "Prime numbers from left part: ");
        if (rightResult.size() != 0)
            Util.printListAndMessage(rightResult, "Prime numbers from right part: ");

        System.out.println("First thread result: " + leftResult.size() +
                "\nSecond thread result " + rightResult.size() +
                "\nGeneral count of prime numbers: " + (leftResult.size() + rightResult.size()));
    }
}
