package com.cg.bsappl.dao;

import java.sql.SQLException;

import com.cg.bsappl.beans.Account;
//import com.cg.bsappl.beans.Customer;

public interface AccountDAO {

	public Account getAccountById(int Id) throws SQLException;
	
	public void createAccount(Account account) throws SQLException;
	
	public void deleteAccount(int id) throws SQLException;
	
	public void updateAccount(Account updateAccount) throws SQLException;
	
	public int getNumberofAccounts() throws SQLException;
}
