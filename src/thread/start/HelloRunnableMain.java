package thread.start;

public class HelloRunnableMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        HelloRunnable runnable = new HelloRunnable();
        Thread thread = new Thread(runnable); //작업과 Thread를 분리
        thread.start();
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
