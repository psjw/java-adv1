package thread.control.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {
    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }


    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 1. empty
                // sleep(1); // 2. sleep
                // Thread.yield(); // 3. yield
                //CPU에서 실행해야 되는데 스케쥴링 대기큐로 들어감 -> 상태변경 안됨 -> Runnable 상태로 대기
            }
        }
    }
}
