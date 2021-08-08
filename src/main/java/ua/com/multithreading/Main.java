package ua.com.multithreading;

import ua.com.multithreading.service.PrimeNumberCounter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws Exception {

        //add list generation

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 5, 6, 4, 11, 23, 17, 9));

        FutureTask<Integer> left = new FutureTask<>(new PrimeNumberCounter(list.subList(0, list.size()/2)));
        FutureTask<Integer> right = new FutureTask<>(new PrimeNumberCounter(list.subList(list.size()/2, list.size())));

        Thread t1 = new Thread(left);
        Thread t2 = new Thread(right);

        t1.start();
        t2.start();

        //all additional threads should be accomplished before main thread

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        Integer leftResult = left.get();
        Integer rightResult = right.get();

        System.out.println("First thread result: " + leftResult + "\nSecond thread result " + rightResult +
                "\nGeneral count of prime numbers: " + (leftResult + rightResult));
    }
}
