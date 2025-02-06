package thread.start;

public class DemonThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        DemonThread demonThread = new DemonThread();
        demonThread.setDaemon(true);//데몬 스레드 여부 (true : 데몬스레드 / false : 사용자 스레드)
        demonThread.start();
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    static class DemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run()");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": end()");
        }
    }
}
