

	package com.cg.bsappl.dao;

	import com.cg.bsappl.beans.*;
	import com.cg.bsappl.exception.AccountException;
	import com.cg.bsappl.util.*;

	import java.sql.SQLException;

	public class AccountDAOImpl implements AccountDAO {

	    @Override
	    public Account getAccountById(int id) throws SQLException {
	        return DatabaseUtil.getAccountById(id);
	    }

	    @Override
	    public void createAccount(Account account) throws SQLException {
	        DatabaseUtil.createAccount(account);
	    }

	    @Override
	    public int getNumberofAccounts() throws SQLException {
	        return DatabaseUtil.getNumberofAccounts();
	    }

	    @Override
	    public void updateAccount(Account updateAccount) throws SQLException {
	        DatabaseUtil.updateAccount(updateAccount);
	    }

	    @Override
	    public void deleteAccount(int id) throws AccountException, SQLException {
	        DatabaseUtil.deleteAccount(id);
	    }
	}

