public interface Account {

    /**
     * Sets the account number for a bank account
     *
     * @param acctNum
     *            value to set the account number to
     * @precond acctNum is a correctly formatted account number
     * @postcond the account number will be set to acctNum
     */
    public void setAcctNum(String acctNum);

    /**
     * Sets the balance of the account
     *
     * @param acctBal
     *            new balance to set the account to
     * @postcond the account balance will be set to acctBal
     */
    public void setAcctBalance(double acctBal);

    /**
     * Returns the account number for this bank account
     *
     * @return the account number
     */
    public String getAcctNum();

    /**
     * Returns the account balance for this bank account
     *
     * @return the balance from the account
     */
    public double getAcctBalance();
}