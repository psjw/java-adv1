package thread.start.test;

import util.MyLogger;

public class StartTest1Main {
    public static void main(String[] args) {
        CustomThread customThread = new CustomThread();
        customThread.start();
    }

    static class CustomThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                MyLogger.log("value : " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
