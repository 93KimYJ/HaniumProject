package kr.ac.kopo.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import kr.ac.kopo.vo.ExerciseVO;

public class ExerciseDAO {

	@Autowired
	private DataSource dataSource;
	
	public List<ExerciseVO> getExerciseData(String uid) {
		
		String sql = "SELECT * FROM EXERCISE WHERE USER_ID = ? ORDER BY E_NO DESC ";
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource);
		List<ExerciseVO> exList = template.query(sql, new BeanPropertyRowMapper<>(ExerciseVO.class), uid);
		
		return exList;
	}
	
}
























