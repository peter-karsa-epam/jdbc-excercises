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
		String query = "SELECT food.ID, food.CALORIES, food.ISVEGAN, food.NAME, food.PRICE "
				+ "FROM FOOD "
				+ "JOIN menu_food ON food.ID = menu_food.food_ID "
				+ "JOIN menu ON menu.ID = menu_food.Menu_ID "
				+ "JOIN restaurant ON restaurant.ID = menu.Restaurant_ID "
				+ "WHERE restaurant.NAME = ? AND ? BETWEEN FROMDATE AND TODATE";

		List<Food> foods = new ArrayList<>();
		Food food;

		do {
			food = (Food) getJdbcTemplate().queryForObject(query,
					new Object[] { date, restaurantName },
					new CustomRowMapper());
			foods.add(food);
		} while (food != null);
		return foods;
	}

	@Override
	public List<RestaurantWithAddress> getAllRestaurantsWithAddress() {
		String query = "SELECT restaurant.ID, restaurant.NAME, "
				+ "	address.ID, address.CITY, address.COUNTRY, address.STREET, address.ZIPCODE "
				+ "FROM restaurant "
				+ "JOIN address ON address.ID = restaurant.Address_ID";

		List<RestaurantWithAddress> restaurants = new ArrayList<>();

		return restaurants;
	}

}
