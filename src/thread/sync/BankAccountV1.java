package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV1 implements BackAccount {

    volatile private int balance;

    public BankAccountV1(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int account) {
        log("거래 시작 : " + getClass().getSimpleName());
        // 잔고가 출금액보다 적으면, 진행하면 안됨

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

        log("거래 종료");
        return false;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
