package ua.com.multithreading.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PrimeNumberCounter implements Callable<List<Integer>> {

    private List<Integer> numbers;
    private List<Integer> primeNumbers;

    public PrimeNumberCounter(List<Integer> list) {
        numbers = list;
        primeNumbers = new ArrayList<>();
    }

    @Override
    public List<Integer> call() {
        for (Integer number : numbers) {
            if (checkIfPrime(number)) {
                primeNumbers.add(number);
            }
        }
        return primeNumbers;
    }

    private boolean checkIfPrime(Integer number) {
        if (number <= 0 || number == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
