

package com.cg.bsappl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.cg.bsappl.beans.Account;
import com.cg.bsappl.beans.Customer;
import com.cg.bsappl.beans.Transaction;

public class DatabaseUtil {
    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bsa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "vinayachalla123@";

    static {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static Account getAccountById(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Account WHERE accountNum = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int accountNum = resultSet.getInt("accountNum");
            double balance = resultSet.getDouble("balance");
            int customerId = resultSet.getInt("customerId");

            Customer customer = getCustomerById(customerId);

            return new Account(accountNum, balance, customer);
        }

        closeResources(connection, preparedStatement, resultSet);

        return null;
    }

    public static void createAccount(Account account) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Account (accountNum, balance, customerId) VALUES (?, ?, ?)");
        preparedStatement.setInt(1, account.getAccountNum());
        preparedStatement.setDouble(2, account.getBalance());
        preparedStatement.setInt(3, account.getCustomer().getCustomerId());

        preparedStatement.executeUpdate();

        closeResources(connection, preparedStatement, null);
    }

    public static int getNumberofAccounts() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS count FROM Account");
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("count");
        }

        closeResources(connection, preparedStatement, resultSet);

        return 0;
    }

    public static void updateAccount(Account updateAccount) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Account SET balance = ? WHERE accountNum = ?");
        preparedStatement.setDouble(1, updateAccount.getBalance());
        preparedStatement.setInt(2, updateAccount.getAccountNum());

        preparedStatement.executeUpdate();

        closeResources(connection, preparedStatement, null);
    }

    public static void deleteAccount(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Account WHERE accountNum = ?");
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();

        closeResources(connection, preparedStatement, null);
    }
    public static Customer getCustomerById(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE customerId = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int customerId = resultSet.getInt("customerId");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");

            return new Customer(customerId, firstName, lastName, email);
        }

        throw new SQLException("Customer not found with ID: " + id);
    }

    public static void createCustomer(Customer customer) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Customer (customerId, firstName, lastName, email) VALUES (?, ?, ?, ?)");

        preparedStatement.setInt(1, customer.getCustomerId());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getLastName());
        preparedStatement.setString(4, customer.getEmailId());

        preparedStatement.executeUpdate();

        closeResources(connection, preparedStatement, null);
    }

    public static void deleteCustomerById(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE customerId = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

        closeResources(connection, preparedStatement, null);
    }

    public static void updateCustomer(Customer updateCustomer) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Customer SET firstName = ?, lastName = ?, email = ? WHERE customerId = ?");

        preparedStatement.setString(1, updateCustomer.getFirstName());
        preparedStatement.setString(2, updateCustomer.getLastName());
        preparedStatement.setString(3, updateCustomer.getEmailId());
        preparedStatement.setInt(4, updateCustomer.getCustomerId());

        preparedStatement.executeUpdate();

        closeResources(connection, preparedStatement, null);
    }

    public static void createTransaction(Transaction transaction) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Transaction (accountId, amount, type, transactionDate) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, transaction.getAccountId());
        preparedStatement.setDouble(2, transaction.getAmount());
        preparedStatement.setString(3, transaction.getType());
        preparedStatement.setDate(4, java.sql.Date.valueOf(transaction.getTransactionDate()));

        preparedStatement.executeUpdate();

        closeResources(connection, preparedStatement, null);
    }

    public static List<Transaction> getTransactionsByAccountId(int accId) throws SQLException {
        Connection connection = getConnection();
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

        closeResources(connection, preparedStatement, resultSet);

        return transactions;
    }
    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
}

