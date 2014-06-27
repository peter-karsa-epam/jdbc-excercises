package com.epam.training.jp.jdbc.excercises.dao.jdbcimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.epam.training.jp.jdbc.excercises.dao.FoodDao;
import com.epam.training.jp.jdbc.excercises.domain.Food;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JdbcFoodDao extends GenericJdbcDao implements FoodDao {

	public JdbcFoodDao(DataSource dataSource) {
		super(dataSource);
	}

	/*
	 * `ID` INT NOT NULL AUTO_INCREMENT, `CALORIES` INT NULL DEFAULT NULL,
	 * `ISVEGAN` BIT NOT NULL, `NAME` VARCHAR(255) NOT NULL, `PRICE` INT NOT
	 * NULL,
	 */

	@Override
	public Food findFoodByName(String name) {
		String query = "SELECT ID,CALORIES,ISVEGAN,PRICE FROM food WHERE NAME = ?";
		Food food = null;

		try (Connection conn = (Connection) dataSource.getConnection();
				PreparedStatement prepStat = (PreparedStatement) conn
						.prepareStatement(query)) {
			ResultSet result = prepStat.executeQuery();

			if (result.next()) {
				food = new Food();
				food.setId(result.getInt(1));
				food.setCalories(result.getInt(2));
				food.setVegan(result.getBoolean(3));
				food.setName(name);
				food.setPrice(result.getInt(4));
			}

		} catch (SQLException sqlEx) {
			throw new RuntimeException(sqlEx);
		}

		return food;
	}

	@Override
	public void updateFoodPriceByName(String name, int newPrice) {
		String update = "UPDATE food SET PRICE = ? WHERE NAME = ?";

		try (Connection conn = (Connection) dataSource.getConnection();
				PreparedStatement prepStat = (PreparedStatement) conn
						.prepareStatement(update)) {

			prepStat.setInt(1, newPrice);
			prepStat.setString(2, name);

			prepStat.executeUpdate();

		} catch (SQLException sqlEx) {
			throw new RuntimeException(sqlEx);
		}
	}

	@Override
	public void save(List<Food> foods) {
		String insert = "INSERT INTO food SET (?,?,?,?) VALUES (?,?,?,?)";

		try (Connection conn = (Connection) dataSource.getConnection();
				PreparedStatement prepStat = (PreparedStatement) conn
						.prepareStatement(insert,
								Statement.RETURN_GENERATED_KEYS)) {
			conn.setAutoCommit(false);

			for (Food item : foods) {
				prepStat.setInt(1, item.getCalories());
				prepStat.setBoolean(2, item.isVegan());
				prepStat.setString(3, item.getName());
				prepStat.setInt(4, item.getPrice());

				prepStat.executeBatch();
			}
			conn.commit();
		} catch (SQLException sqlEx) {
			throw new RuntimeException(sqlEx);
		}
	}
}
