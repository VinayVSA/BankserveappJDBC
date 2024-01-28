package com.cg.bsappl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bsappl.beans.Transaction;
import com.cg.bsappl.util.DatabaseUtil;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public void addTransaction(Transaction transaction) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Transaction (accountId, amount, type, transactionDate) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, transaction.getAccountId());
        preparedStatement.setDouble(2, transaction.getAmount());
        preparedStatement.setString(3, transaction.getType());
        preparedStatement.setDate(4, java.sql.Date.valueOf(transaction.getTransactionDate()));

        preparedStatement.executeUpdate();

        DatabaseUtil.closeResources(connection, preparedStatement, null);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accId) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM Transaction WHERE accountId = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, accId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int accountId = resultSet.getInt("accountId");
            double amount = resultSet.getDouble("amount");
            String type = resultSet.getString("type");
            LocalDate transactionDate = resultSet.getDate("transactionDate").toLocalDate();

            Transaction transaction = new Transaction(accountId, amount, type);
            transaction.setTransactionDate(transactionDate);
            transactions.add(transaction);
        }

        DatabaseUtil.closeResources(connection, preparedStatement, resultSet);

        return transactions;
    }
}
