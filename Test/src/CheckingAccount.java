public class CheckingAccount extends SimpleAccount {

    private double deposit;
    private double fee;

    public CheckingAccount() {
        this.deposit = 0.0;
        this.fee = 0.0;
    }

    public void setDeposit(double monthlyDep) {
        this.deposit = monthlyDep;
    }

    public void setFee(double monthlyFee) {
        this.fee = monthlyFee;
    }

    public double getDeposit() {
        return this.deposit;
    }

    public double getFee() {
        return this.fee;
    }
}