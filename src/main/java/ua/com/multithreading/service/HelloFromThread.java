package ua.com.multithreading.service;

public class HelloFromThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello from " + Thread.currentThread().getName());
    }

    public void helloMethod() {
        for (int i = 49; i >= 0; i--) {
            Thread t = new Thread(new HelloFromThread());
            t.setName(String.valueOf(i));
            t.start();
            while (t.isAlive()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
