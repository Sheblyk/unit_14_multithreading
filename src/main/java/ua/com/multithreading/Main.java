package ua.com.multithreading;

import ua.com.multithreading.control.TaskRunner;

public class Main {
    public static void main(String[] args) throws Exception {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.start();
    }
}
