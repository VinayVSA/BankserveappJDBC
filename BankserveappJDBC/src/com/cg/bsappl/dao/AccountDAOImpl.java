package com.cg.bsappl.dao;

import com.cg.bsappl.beans.Account;
import com.cg.bsappl.beans.Customer;
import com.cg.bsappl.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public Account getAccountById(int accountId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = null;

        connection = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM Account WHERE accountNum = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, accountId);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int accountNum = resultSet.getInt("accountNum");
            double balance = resultSet.getDouble("balance");
            int customerId = resultSet.getInt("customerId");

            // You may need to retrieve Customer details from the database using customerId
            // For simplicity, we assume a method getCustomerById is available in CustomerDAO
            CustomerDAO customerDAO = new CustomerDAOImpl();
            Customer customer = customerDAO.getCustomerdetailsById(customerId);

            account = new Account(accountNum, balance, customer);
        }

        DatabaseUtil.closeResources(connection, preparedStatement, resultSet);

        return account;
    }

    @Override
    public void createAccount(Account account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = DatabaseUtil.getConnection();
        String sql = "INSERT INTO Account (accountNum, balance, customerId) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getAccountNum());
        preparedStatement.setDouble(2, account.getBalance());
        preparedStatement.setInt(3, account.getCustomer().getCustomerId());

        preparedStatement.executeUpdate();

        DatabaseUtil.closeResources(connection, preparedStatement, null);
    }

    @Override
    public void deleteAccount(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = DatabaseUtil.getConnection();
        String sql = "DELETE FROM Account WHERE accountNum = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();

        DatabaseUtil.closeResources(connection, preparedStatement, null);
    }

    @Override
    public void updateAccount(Account updateAccount) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = DatabaseUtil.getConnection();
        String sql = "UPDATE Account SET balance = ? WHERE accountNum = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, updateAccount.getBalance());
        preparedStatement.setInt(2, updateAccount.getAccountNum());

        preparedStatement.executeUpdate();

        DatabaseUtil.closeResources(connection, preparedStatement, null);
    }

    @Override
    public int getNumberofAccounts() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int numberOfAccounts = 0;

        connection = DatabaseUtil.getConnection();
        String sql = "SELECT COUNT(*) AS count FROM Account";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            numberOfAccounts = resultSet.getInt("count");
        }

        DatabaseUtil.closeResources(connection, preparedStatement, resultSet);

        return numberOfAccounts;
    }
}