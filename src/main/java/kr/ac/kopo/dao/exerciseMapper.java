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
	
	/**
	 * 사용자 운동 정보 추가
	 * @param vo
	 */
	@Insert("INSERT INTO exercise (E_NO, USER_ID, TYPE, CNT) "
			+ "VALUES (COALESCE((SELECT MAX(E_NO) + 1 FROM exercise), 1), #{userId}, #{type}, #{cnt}) ")
	void insertExerciseData(ExerciseVO vo);
	
	/**
	 * 선택한 타입의 오늘 운동 횟수 반환
	 * @param type
	 * @param userId
	 * @return
	 */
	@Select("SELECT sum(cnt) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE) AND END_TIME < TRUNC(SYSDATE) + 1 "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	String selectTodayExerciseCount(@Param("type")String type, @Param("userId")String userId);
	
	/**
	 * 선택한 타입의 이번 달 운동 횟수 반환
	 * @param type
	 * @param userId
	 * @return
	 */
	@Select("SELECT SUM(cnt) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE, 'MONTH') AND END_TIME < ADD_MONTHS(TRUNC(SYSDATE, 'MONTH'), 1) "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	Integer selectMonthExerciseCount(@Param("type")String type, @Param("userId")String userId);
	
	/**
	 * 선택한 타입의 운동 횟수의 총 합 반환
	 * @param vo
	 * @return
	 */
	@Select("SELECT sum(cnt) FROM EXERCISE "
			+ "WHERE USER_ID = #{userId} AND TYPE = #{type} ")
	int selectTotalExerciseCount(ExerciseVO vo);
	
	/**
	 * 지금까지 한 운동 세트의 수 반환
	 * @param userId
	 * @return
	 */
	@Select("SELECT count(user_id) FROM EXERCISE "
			+ "WHERE USER_ID = #{userId}")
	int selectTotalExerciseTry(@Param("userId")String userId);
	
	/**
	 * 선택한 타입의 운동 횟수 전체 반환
	 * @param type
	 * @param userId
	 * @return
	 */
	@Select("SELECT count(user_id) FROM EXERCISE "
			+ "WHERE USER_ID = #{userId} AND TYPE = #{type}")
	int selectExerciseTryWithType(@Param("type")String type, @Param("userId")String userId);
	// 뭔가 식별하기 힘드니 가능한 빨리 뜯어고치도록 하자
}
