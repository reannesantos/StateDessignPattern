public class Account {
	private String accountNumber;
    private double balance;
    private AccountState accountState;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountState = new ActiveState();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountState accountState) {
}


    public class ActiveState implements AccountState {
        @Override
        public void deposit(Account account, double amount) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposit successful. Current balance: " + account.getBalance());
        }

        @Override
        public void withdraw(Account account, double amount) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                System.out.println("Withdrawal successful. Current balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance for withdrawal.");
            }
        }

        @Override
        public void activate(Account account) {
            System.out.println("Account is already activated!");
        }

        @Override
        public void suspend(Account account) {
            account.setAccountState(new SuspendedState());
            System.out.println("Account is suspended!");
        }

        @Override
        public void close(Account account) {
            account.setAccountState(new ClosedState());
            System.out.println("Account is closed!");
        }
    }

    public class SuspendedState implements AccountState {
        @Override
        public void deposit(Account account, double amount) {
            System.out.println("You cannot deposit on a suspended account!");
        }

        @Override
        public void withdraw(Account account, double amount) {
            System.out.println("You cannot withdraw on a suspended account!");
        }

        @Override
        public void activate(Account account) {
            account.setAccountState(new ActiveState());
            System.out.println("Account is activated!");
        }

        @Override
        public void suspend(Account account) {
            System.out.println("Account is already suspended!");
        }

        @Override
        public void close(Account account) {
            account.setAccountState(new ClosedState());
            System.out.println("Account is closed!");
        }
    }

    public class ClosedState implements AccountState {
        @Override
        public void deposit(Account account, double amount) {
            System.out.println("You cannot deposit on a closed account!");
        }

        @Override
        public void withdraw(Account account, double amount) {
            System.out.println("You cannot withdraw on a closed account!");
        }

        @Override
        public void activate(Account account) {
            System.out.println("You cannot activate a closed account!");
        }

        @Override
        public void suspend(Account account) {
            System.out.println("You cannot suspend a closed account!");
        }

        @Override
        public void close(Account account) {
            System.out.println("Account is already closed!");
        }
    }