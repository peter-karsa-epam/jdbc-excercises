package com.epam.training.jp.jdbc.excercises.dao.jdbcimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.epam.training.jp.jdbc.excercises.dao.RestaurantDao;
import com.epam.training.jp.jdbc.excercises.domain.Address;
import com.epam.training.jp.jdbc.excercises.domain.Food;
import com.epam.training.jp.jdbc.excercises.domain.Restaurant;
import com.epam.training.jp.jdbc.excercises.dto.RestaurantWithAddress;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JdbcRestaurantDao extends GenericJdbcDao implements RestaurantDao {

	public JdbcRestaurantDao(DataSource dataSource) {
		super(dataSource);
	}

	/*
	 * `ID` INT NOT NULL AUTO_INCREMENT, `NAME` VARCHAR(255) NOT NULL,
	 * `ADDRESS_ID` INT NULL DEFAULT NULL,
	 */

	@Override
	public List<Food> getFoodsAvailable(Date date, String restaurantName) {
		String query = "SELECT food.ID, food.CALORIES, food.ISVEGAN, food.NAME, food.PRICE "
				+ "FROM FOOD "
				+ "JOIN menu_food ON food.ID = menu_food.food_ID "
				+ "JOIN menu ON menu.ID = menu_food.Menu_ID "
				+ "JOIN restaurant ON restaurant.ID = menu.Restaurant_ID "
				+ "WHERE restaurant.NAME = ? AND ? BETWEEN FROMDATE AND TODATE";

		List<Food> foods = null;
		try (Connection conn = (Connection) dataSource.getConnection();
				PreparedStatement prepStat = (PreparedStatement) conn
						.prepareStatement(query)) {
			prepStat.setString(1, restaurantName);
			prepStat.setDate(2, new java.sql.Date(date.getTime()));

			ResultSet rs = prepStat.executeQuery();
			foods = new ArrayList<>();
			Food food;

			while (rs.next()) {
				food = new Food();
				food.setId(rs.getInt(1));
				food.setCalories(rs.getInt(2));
				food.setVegan(rs.getBoolean(3));
				food.setName(rs.getString(4));
				food.setPrice(rs.getInt(5));
				foods.add(food);
			}

			rs.close();
		} catch (SQLException sqlEx) {
			throw new RuntimeException(sqlEx);
		}

		return foods;
	}

	// nem lett kész időre
	@Override
	public List<RestaurantWithAddress> getAllRestaurantsWithAddress() {
		String sql = "SELECT restaurant.ID, restaurant.NAME, "
				+ "	address.ID, address.CITY, address.COUNTRY, address.STREET, address.ZIPCODE "
				+ "FROM restaurant "
				+ "JOIN address ON address.ID = restaurant.Address_ID";

		try (Connection conn = (Connection) dataSource.getConnection();
				PreparedStatement prepStat = (PreparedStatement) conn
						.prepareStatement(sql)) {
			List<RestaurantWithAddress> restaurantWithAddresses = new ArrayList<>();
			try (ResultSet rs = prepStat.executeQuery()) {
				Restaurant restaurant;
				Address address;
				while (rs.next()) {
					restaurant = new Restaurant();
					restaurant.setId(rs.getInt("restaurant.ID"));
					restaurant.setName(rs.getString("restaurant.NAME"));
					address = new Address();
					address.setCity(rs.getString("address.CITY"));
					address.setCountry(rs.getString("address.COUNTRY"));
					address.setStreet(rs.getString("address.STREET"));
					address.setZipCode(rs.getString("address.ZIPCODE"));
					restaurantWithAddresses.add(new RestaurantWithAddress(
							restaurant, address));
				}
			}
			return restaurantWithAddresses;
		} catch (SQLException sqlEx) {
			throw new RuntimeException(sqlEx);
		}

	}

}
