package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.training.jp.jdbc.excercises.dao.MenuFoodDao;

public class JdbcTemplateMenuFoodDao extends JdbcDaoSupport implements
		MenuFoodDao {

	public JdbcTemplateMenuFoodDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/*
	 * `Menu_ID` INT NOT NULL, `Foods_ID` INT NOT NULL,
	 */
	
	private JdbcTemplate temp;

	@Override
	public void removeMenuFoods(int id) {
		String delete = "DELETE FROM menu_food WHERE ID = ?";
	}

}
