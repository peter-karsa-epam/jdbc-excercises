package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.training.jp.jdbc.excercises.dao.RestaurantDao;
import com.epam.training.jp.jdbc.excercises.domain.Food;
import com.epam.training.jp.jdbc.excercises.dto.RestaurantWithAddress;

public class JdbcTemplateRestaurantDao extends JdbcDaoSupport implements
		RestaurantDao {

	public JdbcTemplateRestaurantDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/*
	 * `ID` INT NOT NULL AUTO_INCREMENT, `NAME` VARCHAR(255) NOT NULL,
	 * `ADDRESS_ID` INT NULL DEFAULT NULL,
	 */

	private JdbcTemplate temp;

	@Override
	public List<Food> getFoodsAvailable(Date date, String restaurantName) {
		String query = "";
		List<Food> foods = new ArrayList<>();

		return foods;
	}

	@Override
	public List<RestaurantWithAddress> getAllRestaurantsWithAddress() {
		String query = "";
		List<RestaurantWithAddress> restaurants = new ArrayList<>();

		return restaurants;
	}

}
