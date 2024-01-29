// CustomerDAOImpl.java
package com.cg.bsappl.dao;


import java.sql.SQLException;

import com.cg.bsappl.beans.Customer;
import com.cg.bsappl.util.DatabaseUtil;

public class CustomerDAOImpl implements CustomerDAO {

	 @Override
	    public Customer getCustomerdetailsById(int id) throws SQLException {
	        return DatabaseUtil.getCustomerById(id);
	    }

	    @Override
	    public void addCustomer(Customer customer) throws SQLException {
	        DatabaseUtil.createCustomer(customer);
	    }

	    @Override
	    public void deleteCustomer(int id) throws SQLException {
	        DatabaseUtil.deleteCustomerById(id);
	    }

	    @Override
	    public void updateCustomer(Customer customer) throws SQLException {
	        DatabaseUtil.updateCustomer(customer);
	    }
}
