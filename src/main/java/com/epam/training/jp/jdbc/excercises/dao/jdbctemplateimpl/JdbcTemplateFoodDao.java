package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.training.jp.jdbc.excercises.dao.FoodDao;
import com.epam.training.jp.jdbc.excercises.domain.Food;

public class JdbcTemplateFoodDao extends JdbcDaoSupport implements FoodDao {

	public JdbcTemplateFoodDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/*
	 * `ID` INT NOT NULL AUTO_INCREMENT, `CALORIES` INT NULL DEFAULT NULL,
	 * `ISVEGAN` BIT NOT NULL, `NAME` VARCHAR(255) NOT NULL, `PRICE` INT NOT
	 * NULL,
	 */

	private JdbcTemplate temp;
	
	@Override
	public Food findFoodByName(String name) {
		String query = "SELECT * FROM food WHERE NAME = ?";
		Food food = null;

		return food;
	}

	@Override
	public void updateFoodPriceByName(String name, int newPrice) {
		String update = "UPDATE food SET PRICE = ? WHERE NAME = ?";
	}

	@Override
	public void save(List<Food> foods) {
		String insert = "INSERT INTO food SET (CALORIES, ISVEGAN, NAME, PRICE) VALUES (?,?,?,?)";
		
	}

}
