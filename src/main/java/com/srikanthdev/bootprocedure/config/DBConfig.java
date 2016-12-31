package com.srikanthdev.bootprocedure.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
public class DBConfig {

	@Value("${oracle.username}")
	private String username;

	@Value("${oracle.password}")
	private String password;

	@Value("${oracle.url}")
	private String url;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Bean
	DataSource dataSource() throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setURL(url);
		dataSource.setImplicitCachingEnabled(true);
		dataSource.setFastConnectionFailoverEnabled(true);
		return dataSource;
	}

}
