package thread.executor.pooisize;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

public class PoolSizeMainV1 {

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        //maximumPoolSize = corePoolSize + workQueue.size()
        ExecutorService es = new ThreadPoolExecutor(2, 4,
                3000, TimeUnit.MICROSECONDS, workQueue);
        printState(es);

        es.execute(new RunnableTask("task1"));

        es.execute(new RunnableTask("task2"));
        printState(es, "task2");

        es.execute(new RunnableTask("task3"));
        printState(es, "task3");

        es.execute(new RunnableTask("task4"));
        printState(es, "task4");

        es.execute(new RunnableTask("task5"));
        printState(es, "task5");

        es.execute(new RunnableTask("task6"));
        printState(es, "task6");
        try {
            es.execute(new RunnableTask("task7"));
            printState(es, "task7");
        }catch (RejectedExecutionException e){
            log("task7 실행 거절 예외 발생 : " + e);
        }

        sleep(2500);
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000);
        log("== maximumPoolSize 대기 시간 초과 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
