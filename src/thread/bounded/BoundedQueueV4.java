package thread.bounded;

import static util.MyLogger.log;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueueV4 implements BoundedQueue {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();//스레드가 기다리는 대기집합

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV4(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try{
            while (queue.size() == max) { // t1: 9/10, t2: 9/10
                log("[put] 큐가 가득 참, 생산자 대기");
                try {
                    condition.await();
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //t1:
            queue.offer(data); //t2, t1: 10/10
            condition.signal();
        }finally {
            lock.unlock();
        }


    }

    //put을 호출해도 같은 synchronized이기 때문에 take 호출 시에도 대기함
    @Override
    public String take() {
        lock.lock();

        try{
            while(queue.isEmpty()){
                log("[take] 큐에 데이터가 없음, 소비자 대기");
                try {
                    condition.await();
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = queue.poll();
            log("[take] 소비자 데이터 획득, condition.signal() 호출");
            condition.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
