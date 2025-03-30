package thread.sync;

public interface BankAccount {

    boolean withdraw(int account); //출금

    int getBalance(); //잔액조회

}
