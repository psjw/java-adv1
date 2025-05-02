package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        //ThreadPoolExecutor abc = (ThreadPoolExecutor) executorService;
        // if문에서 바로 할 수 있게 바뀜
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize();
            int active = poolExecutor.getActiveCount();
            int queued = poolExecutor.getQueue().size();
            long completedTask = poolExecutor.getCompletedTaskCount();
            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queued +
                    ", completedTasks=" + completedTask + "]");
        } else {
            log(executorService);
        }
    }
}
