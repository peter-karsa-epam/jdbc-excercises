package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.training.jp.jdbc.excercises.dao.AddressDao;
import com.epam.training.jp.jdbc.excercises.domain.Address;

public class JdbcTemplateAddressDao extends JdbcDaoSupport implements
		AddressDao {

	public JdbcTemplateAddressDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/*
	 * `ID` INT NOT NULL AUTO_INCREMENT, `CITY` VARCHAR(255) NOT NULL, `COUNTRY`
	 * VARCHAR(255) NOT NULL, `STREET` VARCHAR(255) NOT NULL, `ZIPCODE`
	 * VARCHAR(255) NOT NULL,
	 */

	private JdbcTemplate temp;

	@Override
	public void save(Address address) {
		String insert = "INSERT INTO address SET (?,?,?,?) VALUES (?,?,?,?)";
		
		
	}

}
