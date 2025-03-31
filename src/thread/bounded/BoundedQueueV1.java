package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        if (queue.size() == max) { // t1: 9/10, t2: 9/10
            log("[put] 큐가 가득 참, 버림: " + data);
            return;
        }
        //t1:
        queue.offer(data); //t2, t1: 10/10
    }

    //put을 호출해도 같은 synchronized이기 때문에 take 호출 시에도 대기함
    @Override
    public synchronized String take() {
        if(queue.isEmpty()){
            return null;
        }
        return queue.peek();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
