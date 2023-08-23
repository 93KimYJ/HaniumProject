package kr.ac.kopo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import kr.ac.kopo.vo.ExerciseVO;
import kr.ac.kopo.vo.UserVO;

@Mapper
public interface ExerciseMapper {
	
	@Select("SELECT * FROM EXERCISE WHERE USER_ID = #{uid} ORDER BY E_NO DESC ")
	List<ExerciseVO> selectExerciseDataTest(String uid);
	
	
	@Insert("INSERT INTO exercise (E_NO, USER_ID, TYPE, CNT) "
			+ "VALUES (COALESCE((SELECT MAX(E_NO) + 1 FROM exercise), 1), #{userId}, #{type}, #{cnt}) ")
	void insertExerciseData(ExerciseVO vo);
	
	@Select("SELECT sum(cnt) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE) AND END_TIME < TRUNC(SYSDATE) + 1 "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	String selectTodayExerciseCount(@Param("type")String type, @Param("userId")String userId);
	
	@Select("SELECT sum(cnt) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE) - 7 AND END_TIME < TRUNC(SYSDATE) + 1"
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	String selectWeekExerciseCount(@Param("type")String type, @Param("userId")String userId);
	
	@Select("SELECT sum(cnt) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE) - 30 AND END_TIME < TRUNC(SYSDATE) + 1"
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	String selectMonthExerciseCount(@Param("type")String type, @Param("userId")String userId);

}
