package com.cg.bsappl.beans;

import java.time.LocalDate;

public class Transaction {
    private int accountId;
    private double amount;
    private String type;
    private LocalDate transactionDate;

    public Transaction(int accountId, double amount, String type) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.transactionDate = LocalDate.now();
    }

    public int getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountId=" + accountId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate=transactionDate;
		
	}
}
