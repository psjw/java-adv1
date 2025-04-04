package thread.bounded;

import static util.MyLogger.log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoundedQueueV6_2 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data);
        log("저장 시도 결과 " + result);

    }

    //put을 호출해도 같은 synchronized이기 때문에 take 호출 시에도 대기함
    @Override
    public String take() {
            return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
