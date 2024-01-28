package com.cg.bsappl.beans;

public class Account {
	private int accountNum;
	private double balance;
    private Customer customer;
    
	public Account(int accountNum, double balance, Customer customer) {
		super();
		this.accountNum = accountNum;
		this.balance = balance;
		this.customer = customer;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", balance=" + balance + ", customer=" + customer + "]";
	}
	
	

}
