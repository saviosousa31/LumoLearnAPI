package com.novacoding.lumolearn_api.LumoLearn.API.service;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	@Autowired
	private DataSource dataSource;
	
	public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }	
}
