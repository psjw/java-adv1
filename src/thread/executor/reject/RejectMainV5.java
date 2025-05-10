package thread.executor.reject;

import static util.MyLogger.log;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import thread.executor.RunnableTask;

public class RejectMainV5 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new MyRejectedExecutionHandler());
        //작업 요청 스레드에게 시킴 main thread가 수행
        executor.submit(new RunnableTask("task1"));

        executor.submit(new RunnableTask("task2"));
        executor.submit(new RunnableTask("task3"));

        executor.close();

    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        static AtomicInteger count = new AtomicInteger(0);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            int i = count.incrementAndGet();
            log("[경고] 거절된 누적 작업수 : " + i);
        }
    }
}
