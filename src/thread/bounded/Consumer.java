package thread.bounded;

import static util.MyLogger.log;

public class Consumer implements Runnable {
    private BoundedQueue queue;

    public Consumer(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[소비 시도]      ? <- " + queue);
        String data = queue.take();
        log("[소비 완료] " + data + " <- " + queue);
    }
}
