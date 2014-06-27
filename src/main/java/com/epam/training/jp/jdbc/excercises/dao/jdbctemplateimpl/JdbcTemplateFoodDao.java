package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
	private SimpleJdbcInsert ins;

	@Override
	public Food findFoodByName(String name) {
		String query = "SELECT * FROM food WHERE NAME = ?";
		Food food = (Food) getJdbcTemplate().queryForObject(query,
				new Object[] { name }, new CustomRowMapper());

		return food;
	}

	@Override
	public void updateFoodPriceByName(String name, int newPrice) {
		String update = "UPDATE food SET PRICE = ? WHERE NAME = ?";
		temp = new JdbcTemplate(getDataSource());
		temp.update(update, newPrice, name);
	}

	@Override
	public void save(List<Food> foods) {
		// String insert =
		// "INSERT INTO food SET (CALORIES, ISVEGAN, NAME, PRICE) VALUES (?,?,?,?)";

		temp = new JdbcTemplate(getDataSource());
		ins = new SimpleJdbcInsert(temp).withTableName("address");

		for (Food item : foods) {
			Map<String, Object> params = new HashMap<>();
			params.put("CALORIES", item.getCalories());
			params.put("ISVEGAN", item.isVegan());
			params.put("NAME", item.getName());
			params.put("PRICE", item.getPrice());

			ins.execute(params);
		}

	}

}
