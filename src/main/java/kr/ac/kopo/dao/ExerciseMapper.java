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
	
	// 작명규칙
	// 행위_기간_목표_with조건_and추가조건_and추가조건...
	
	@Select("SELECT * FROM EXERCISE WHERE USER_ID = #{uid} ORDER BY E_NO DESC ")
	List<ExerciseVO> select_ExerciseDataTest(String uid);
	
	
	
	@Insert("INSERT INTO exercise (E_NO, USER_ID, TYPE, CNT) "
			+ "VALUES (COALESCE((SELECT MAX(E_NO) + 1 FROM exercise), 1), #{userId}, #{type}, #{cnt}) ")
	void insert_exerciseData(ExerciseVO vo);
	
	
	/**
	 * 일, 주, 월, 년, 전체시간 운동 동작 횟수 총 합 구하는 메서드들
	 * @param vo
	 * @return
	 */
	@Select("SELECT COALESCE(sum(cnt), 0) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE) AND END_TIME < TRUNC(SYSDATE) + 1 "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	Integer select_today_exerciseCount_withUid_andType(ExerciseVO vo);
	
	
	@Select("SELECT COALESCE(sum(cnt), 0) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE, 'IW') AND END_TIME < TRUNC(SYSDATE, 'IW') + 7 "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	Integer select_week_exerciseCount_withUid_andType(ExerciseVO vo);
	
	
	@Select("SELECT COALESCE(SUM(cnt), 0) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE, 'MONTH') AND END_TIME < ADD_MONTHS(TRUNC(SYSDATE, 'MONTH'), 1) "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	Integer select_month_exerciseCount_withUid_andType(ExerciseVO vo);
	

	@Select("SELECT COALESCE(SUM(CNT), 0) FROM EXERCISE "
			+ "WHERE TO_CHAR(END_TIME, 'YYYY') = TO_CHAR(SYSDATE, 'YYYY') "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	Integer select_year_exerciseCount_withUid_andType(ExerciseVO vo);
	
	
	@Select("SELECT COALESCE(sum(cnt), 0) FROM EXERCISE "
			+ "WHERE USER_ID = #{userId} AND TYPE = #{type} ")
	Integer select_allTime_exerciseCount_withUid_andType(ExerciseVO vo);
	//
	

	/**
	 * 일, 주, 월, 년, 전체시간 운동 세트 총 합 구하는 메서드들
	 * @param vo
	 * @return
	 */
	@Select("SELECT COALESCE(count(cnt), 0) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE) AND END_TIME < TRUNC(SYSDATE) + 1 "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	Integer select_today_exerciseTryCount_withUid_andType(ExerciseVO vo);
	
	
	@Select("SELECT COALESCE(count(cnt), 0) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE, 'IW') AND END_TIME < TRUNC(SYSDATE, 'IW') + 7 "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	Integer select_week_exerciseTryCount_withUid_andType(ExerciseVO vo);
	
	
	@Select("SELECT COALESCE(count(cnt), 0) FROM exercise "
			+ "WHERE END_TIME >= TRUNC(SYSDATE, 'MONTH') AND END_TIME < ADD_MONTHS(TRUNC(SYSDATE, 'MONTH'), 1) "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	Integer select_month_exerciseTryCount_withUid_andType(ExerciseVO vo);

	
	@Select("SELECT COALESCE(count(CNT), 0) FROM EXERCISE "
			+ "WHERE TO_CHAR(END_TIME, 'YYYY') = TO_CHAR(SYSDATE, 'YYYY') "
			+ "AND TYPE = #{type} AND USER_ID = #{userId} ")
	Integer select_year_exerciseTryCount_withUid_andType(ExerciseVO vo);
	
	
	@Select("SELECT COALESCE(count(cnt), 0) FROM EXERCISE "
			+ "WHERE USER_ID = #{userId} AND TYPE = #{type} ")
	Integer select_allTime_exerciseTryCount_withUid_andType(ExerciseVO vo);	
	//
	
	
	/**
	 * 전체 유저 세트 수 평균
	 * @return
	 */
	@Select("SELECT AVG(COUNT_PER_USER) AS CNT "
			+ "FROM ( "
			+ "    SELECT USER_ID, COUNT(*) AS COUNT_PER_USER "
			+ "    FROM EXERCISE "
			+ "    WHERE END_TIME >= TRUNC(SYSDATE, 'MONTH') AND END_TIME < ADD_MONTHS(TRUNC(SYSDATE, 'MONTH'), 1) "
			+ "    GROUP BY USER_ID "
			+ ") ")
	Integer select_month_allUserExerciseTryCountAverage();
			
	
	@Select("SELECT AVG(COUNT_PER_USER) AS CNT "
			+ "FROM ( "
			+ "    SELECT USER_ID, COUNT(*) AS COUNT_PER_USER "
			+ "    FROM EXERCISE "
			+ "    GROUP BY USER_ID "
			+ ") ")
	Integer select_allTime_allUserExerciseTryCountAverage();
	
	
	//
	
	
	
	/**
	 * 운동 데이터 레코드 가져오기
	 * eNo에서부터 cnt개 가져옴
	 * @param userId
	 * @return
	 */
	@Select("SELECT * FROM ( "
			+ "	SELECT * FROM exercise "
			+ "	WHERE user_id = #{userId} AND E_NO <= #{eNo} "
			+ "	ORDER BY END_TIME DESC "
			+ "	) "
			+ "WHERE rownum <= #{cnt} ")
	List<ExerciseVO> select_exerciseRecode_withUid_andRange(ExerciseVO vo);
	
	@Select("SELECT * FROM ( "
			+ "	SELECT * FROM exercise "
			+ "	WHERE user_id = #{userId} AND E_NO <= #{eNo} AND TYPE = #{type}"
			+ "	ORDER BY END_TIME DESC "
			+ "	) "
			+ "WHERE rownum <= #{cnt} ")
	List<ExerciseVO> select_exerciseRecode_withUid_andType_andRange(ExerciseVO vo);
	
	
	
	@Select("SELECT COALESCE(count(user_id), 0) FROM EXERCISE "
			+ "WHERE USER_ID = #{userId}")
	Integer select_allTime_exerciseTryCount_withUid(@Param("userId")String userId);
	
	
	/**
	 * 각 유저의 최고기록 top x 가져오기
	 * @param type
	 * @return
	 */
	@Select("SELECT * FROM ( "
			+ "SELECT USER_ID, COALESCE(MAX(CNT), 0 ) AS CNT FROM EXERCISE "
			+ "WHERE TYPE= #{type} GROUP BY USER_ID ORDER BY CNT DESC "
			+ ") "
			+ "WHERE rownum <= #{cnt} ")
	List<ExerciseVO> select_allTime_bestExerciseRecode_withType_andRownum(ExerciseVO vo);
	
	
	// 유저의 가장 많이 한 운동
	@Select("SELECT * FROM ( "
			+ "	SELECT user_id, TYPE, count(cnt) AS trycnt, sum(cnt) AS cnt FROM exercise "
			+ "	GROUP BY user_id, TYPE "
			+ "	ORDER BY cnt DESC ) "
			+ "WHERE USER_ID = 'idid' AND rownum <= 1 ")
	ExerciseVO select_allTime_mostExercise_withUid(@Param("userId")String userId);
	
	
	
	
	
	
	
	
}
