package thread.sync;

public interface BackAccount {

    boolean withdraw(int account); //출금

    int getBalance(); //잔액조회

}
