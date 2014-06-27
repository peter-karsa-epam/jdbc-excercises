package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.training.jp.jdbc.excercises.dao.MenuDao;

public class JdbcTemplateMenuDao extends JdbcDaoSupport implements MenuDao {

	public JdbcTemplateMenuDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/*
	 * `ID` INT NOT NULL AUTO_INCREMENT, `Restaurant_ID` VARCHAR(255) NOT NULL,
	 * `FROMDATE` DATE NULL DEFAULT NULL, `TODATE` DATE NULL DEFAULT NULL,
	 */

	private JdbcTemplate temp;
	
	@Override
	public void removeMenu(int id) {
		String delete = "DELETE FROM menu WHERE ID = ?";
	}

}
