package thread.start.test;

import util.MyLogger;

public class StartTest2Main {
    public static void main(String[] args) {
        CounterRunnable counterRunnable = new CounterRunnable();
        Thread thread = new Thread(counterRunnable, "counter");
        thread.start();
    }

    static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                MyLogger.log("value: " + i);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
