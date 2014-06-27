package com.epam.training.jp.jdbc.excercises.dao.jdbcimpl;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.epam.training.jp.jdbc.excercises.dao.MenuFoodDao;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JdbcMenuFoodDao extends GenericJdbcDao implements MenuFoodDao {

	public JdbcMenuFoodDao(DataSource dataSource) {
		super(dataSource);
	}

	/*
	 * `Menu_ID` INT NOT NULL, `Foods_ID` INT NOT NULL,
	 */

	@Override
	public void removeMenuFoods(int menuId) {
		String delete = "DELETE FROM menu_food WHERE Menu_ID = ?";

		try (Connection conn = (Connection) dataSource.getConnection();
				PreparedStatement prepStat = (PreparedStatement) conn
						.prepareStatement(delete)) {
			prepStat.setInt(1, menuId);
			prepStat.execute();
		} catch (SQLException sqlEx) {
			throw new RuntimeException(sqlEx);
		}
	}
}
