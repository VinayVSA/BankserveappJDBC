// CustomerDAOImpl.java
package com.cg.bsappl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.bsappl.beans.Customer;
import com.cg.bsappl.util.DatabaseUtil;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer getCustomerdetailsById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = null;

        connection = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM Customer WHERE customerId = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int customerId = resultSet.getInt("customerId");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");

            customer = new Customer(customerId, firstName, lastName, email);
        }

        DatabaseUtil.closeResources(connection, preparedStatement, resultSet);

        return customer;
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = DatabaseUtil.getConnection();
        String sql = "INSERT INTO Customer (customerId, firstName, lastName, email) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, customer.getCustomerId());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getLastName());
        preparedStatement.setString(4, customer.getEmailId());

        preparedStatement.executeUpdate();

        DatabaseUtil.closeResources(connection, preparedStatement, null);
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = DatabaseUtil.getConnection();
        String sql = "DELETE FROM Customer WHERE customerId = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();

        DatabaseUtil.closeResources(connection, preparedStatement, null);
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = DatabaseUtil.getConnection();
        String sql = "UPDATE Customer SET firstName = ?, lastName = ?, email = ? WHERE customerId = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getEmailId());
        preparedStatement.setInt(4, customer.getCustomerId());

        preparedStatement.executeUpdate();

        DatabaseUtil.closeResources(connection, preparedStatement, null);
    }
}
