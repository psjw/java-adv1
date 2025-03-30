package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV4 implements BackAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV4(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int account) {
        log("거래 시작 : " + getClass().getSimpleName());
        // 잔고가 출금액보다 적으면, 진행하면 안됨

        lock.lock(); //ReentrantLock을 사용하여 lock을 걸기
        try {
            // ==임계 영역 시작==
            log("[검증 시작] 출금액 : " + account + ", 잔액  : " + balance);
            if (balance < account) {
                log("[검증실패] 출금액: " + account + ", 잔액 : " + balance);
                return false;
            }
            //잔고가 출금액 보다 많으면, 진행
            log("[검증성공] 출금액 : " + account + ", 잔액  : " + balance);
            sleep(1000); //1초 대기 -> 출금에 걸리는 시간으로 가정
            balance = balance - account;
            log("[출금완료] 출금액 : " + account + ", 잔액  : " + balance);
            // ==임계 영역 종료==
        } finally {
            lock.unlock();  //ReentrantLock을 사용하여 lock을 해제
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();  //ReentrantLock을 사용하여 lock을 걸기
        try{
            return balance;
        }finally {
            lock.unlock();  //ReentrantLock을 사용하여 lock을 해제
        }
    }
}
