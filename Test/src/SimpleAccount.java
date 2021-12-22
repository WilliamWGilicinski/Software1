public class SimpleAccount implements Account {

    private String number;
    private double balance;

    public SimpleAccount() {
        this.number = "";
        this.balance = 0.0;
    }

    @Override
    public void setAcctNum(String acctNum) {
        this.number = acctNum;
    }

    @Override
    public void setAcctBalance(double acctBal) {
        this.balance = acctBal;
    }

    @Override
    public String getAcctNum() {
        return this.number;
    }

    @Override
    public double getAcctBalance() {
        return this.balance;
    }
}
