package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureCancelMain {
//    private static boolean myInterruptIfRunning = true;
    private static boolean myInterruptIfRunning = false; //이미 실행중인 Task는 그대로 두고 get()은 얻을 수 없음

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("future.state: " + future.state());

        //일정시간 후 취소 시도
        sleep(3000);

        // cancel() 호출
        log("future.cancel(" + myInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(myInterruptIfRunning);
        log("cancel(" + myInterruptIfRunning + ") result: " + cancelResult);

        //결과확인
        try {
            log("future.result: " + future.get());
        } catch (CancellationException e) {
            log("future는 이미 취소 되었습니다.");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        es.close();
    }

    static class MyTask implements Callable<String> {
        @Override
        public String call() {

            try {
                for (int i = 0; i < 10; i++) {
                    log("작업 중 : " + i);
                    Thread.sleep(1000); // 1초 동안 sleep
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생");
                return "Interrupted";
            }

            return "Completed";
        }
    }
}
