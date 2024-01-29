package com.cg.bsappl.dao;


import java.sql.SQLException;

import java.util.List;

import com.cg.bsappl.beans.Transaction;
import com.cg.bsappl.util.DatabaseUtil;

public class TransactionDAOImpl implements TransactionDAO {

	 @Override
	    public void addTransaction(Transaction transaction) throws SQLException {
	        DatabaseUtil.createTransaction(transaction);
	    }

	    @Override
	    public List<Transaction> getTransactionsByAccountId(int accId) throws SQLException {
	        return DatabaseUtil.getTransactionsByAccountId(accId);
	    }
}
