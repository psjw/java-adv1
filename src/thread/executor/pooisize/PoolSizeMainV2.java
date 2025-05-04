package thread.executor.pooisize;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

public class PoolSizeMainV2 {

    public static void main(String[] args) {
        //큐 무한대로 증가
        ExecutorService es = Executors.newFixedThreadPool(2);
//        ExecutorService es =  new ThreadPoolExecutor(2, 2,
//                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        log("pool 생성");
        printState(es);

        for (int i = 0; i <= 6; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        es.close();
        log("== shutdown 완료 ==");
    }
}
