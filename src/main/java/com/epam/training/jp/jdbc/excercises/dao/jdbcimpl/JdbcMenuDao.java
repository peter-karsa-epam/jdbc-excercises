package com.epam.training.jp.jdbc.excercises.dao.jdbcimpl;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.epam.training.jp.jdbc.excercises.dao.MenuDao;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JdbcMenuDao extends GenericJdbcDao implements MenuDao {

	public JdbcMenuDao(DataSource dataSource) {
		super(dataSource);
	}

	/*
	 * `ID` INT NOT NULL AUTO_INCREMENT, `Restaurant_ID` VARCHAR(255) NOT NULL,
	 * `FROMDATE` DATE NULL DEFAULT NULL, `TODATE` DATE NULL DEFAULT NULL,
	 */

	@Override
	public void removeMenu(int id) {
		String delete = "DELETE FROM menu WHERE ID = ?";

		try (Connection conn = (Connection) dataSource.getConnection();
				PreparedStatement prepStat = (PreparedStatement) conn
						.prepareStatement(delete)) {
			prepStat.setInt(1, id);
			prepStat.execute();
		} catch (SQLException sqlEx) {
			throw new RuntimeException(sqlEx);
		}

	}

}
