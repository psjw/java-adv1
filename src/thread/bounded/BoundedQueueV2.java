package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
         while (queue.size() == max) { // t1: 9/10, t2: 9/10
            log("[put] 큐가 가득 참, 생산자 대기");
            sleep(1000);
        }
        //t1:
        queue.offer(data); //t2, t1: 10/10
    }

    //put을 호출해도 같은 synchronized이기 때문에 take 호출 시에도 대기함
    @Override
    public synchronized String take() {
        while(queue.isEmpty()){
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            sleep(1000);
        }
        return queue.peek();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
