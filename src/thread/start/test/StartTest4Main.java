package thread.start.test;

import util.MyLogger;

public class StartTest4Main {
    public static void main(String[] args) {

        PrintWork printWorkA = new PrintWork("A", 1000);
        PrintWork printWorkB = new PrintWork("B", 500);
        Thread threadA = new Thread(printWorkA, "Thread-A");
        Thread threadB = new Thread(printWorkB, "Thread-B");
        threadA.start();
        threadB.start();

    }

    public static class PrintWork implements Runnable {

        private String content;
        private long sleepMs;

        public PrintWork(String content, long sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true) {
                MyLogger.log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
