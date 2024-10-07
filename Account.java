import java.util.Random;
import java.util.Scanner;

public class Account {


    private String username;
    private String password;
    private String AccountNumber;
    private double balance = 0.0;
    private double transfer;
    private int limit;



    public Account(String username, int limit, String password, String AccountNumber ,double balance, double transfer) {
        this.username = username;
        this.limit = limit;
        this.AccountNumber = AccountNumber;
        this.password = password;
        this.balance = 0.0;
        this.transfer = 0.0;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public double getBalance() {
        return balance;
    }

    public double getTransfer() {
        return transfer;
    }

    public void setTransfer(double transfer) {
        this.transfer = transfer;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    // 審核方法
    public boolean validatePassword(String password) {
        for (int i = 0; i < 3 ; i++) {
            if (this.password.equals(password)) {
                System.out.println("歡迎登入");
                return true; // 密碼正確，結束方法
            }else{
                System.out.println("密碼錯誤，請再輸入一次");
                if (i == 2) { // 第三次輸入錯誤
                    System.out.println("帳號已鎖定，請稍後再試");
                    return false;
                }
            }
        }
            return false; // 如果三次都失敗
    }

    public void deposit(double amount){
        if (amount <= 0){
            System.out.println("存款請存正數");
        }else{
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        // 檢查提款金額是否超過單次限制
        if (amount > limit) {
            System.out.println("超出單次提款金額，請再輸入一次要提款的金額");
        } else {
            // 檢查餘額是否足夠
            if (balance >= amount) {
                balance -= amount;
                System.out.println("提款成功，您的餘額為：" + balance);
            } else {
                System.out.println("餘額不足，提款失敗");
            }
        }
    }

    public void transfer(double transferAmount, Account recipientAccount) {
        // 先檢查轉帳金額是否有效
        if (transferAmount <= 0 || transferAmount > limit) {
            System.out.println("轉帳金額不得小於零或大於單次轉帳限額");
            return;
        }

        // 檢查帳戶餘額是否足夠
        if (balance < transferAmount) {
            System.out.println("餘額不足，轉帳失敗");
            return;
        }

        // 檢查收款帳戶是否存在
        if (recipientAccount == null) {
            System.out.println("收款帳戶不存在，轉帳失敗");
            return;
        }

        // 所有條件都滿足，執行轉帳
        balance -= transferAmount;
        recipientAccount.balance += transferAmount;
        System.out.println("轉帳成功，已轉帳 " + transferAmount + " 到帳戶：" + recipientAccount);
    }

        // 帳號自動產生器
        public void AccountNumberMaker(String username) {
            int[] numbers = new int[5];
            Random random = new Random();

            // 為數組的每個位置生成一個隨機數
            for (int i = 0; i < 5; i++) {
                numbers[i] = random.nextInt(10); // 生成0-9之間的隨機數
            }

            // 拼接數字串
            StringBuilder accountNumber = new StringBuilder();
            for (int number : numbers) {
                accountNumber.append(number);
            }

            // 打印出生成的帳戶號碼
            System.out.println("帳戶號碼: " + accountNumber.toString());
        }

        // 密碼設定必須有長度限制
        public void passwordRule() {
            Scanner scanner = new Scanner(System.in);
            String password;

            while (true) {
                System.out.println("請輸入密碼 (至少10碼):");
                password = scanner.nextLine();

                if (password.length() < 10) {
                    System.out.println("密碼設置最少要十碼");
                    continue;
                }

                System.out.println("請再次輸入密碼:");
                String secondPassword = scanner.nextLine();

                if (password.equals(secondPassword)) {
                    System.out.println("設置成功");
                    break;
                } else {
                    System.out.println("設置錯誤，請重新設置");
                }
            }

            scanner.close();
        }
}

