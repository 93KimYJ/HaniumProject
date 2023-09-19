package kr.ac.kopo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.ac.kopo.vo.UserVO;

@Mapper
public interface UserMapper {
	
	// 작명규칙
	// 행위_기간_목표_with조건 (id는 기본갑)
	@Select("SELECT * FROM HC_USER WHERE USER_ID = #{id}")
	UserVO select_userInfo_withId(String id);

	@Select("SELECT EMAIL FROM HC_USER WHERE USER_ID = #{id}")
	UserVO select_userEmail_withId(String id);
	
	@Select("SELECT USER_ID FROM HC_USER WHERE USER_ID = #{userId} AND PASSWORD = #{password}")
	String userCheck(UserVO vo);
}
