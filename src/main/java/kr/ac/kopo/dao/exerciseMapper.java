package kr.ac.kopo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.ac.kopo.vo.UserVO;

@Mapper
public interface exerciseMapper {
	@Select("SELECT EMAIL FROM HC_USER WHERE USER_ID = #{id}")
	UserVO getUserEmailwithId(String id);
	
	// 운동관련 sql문 여기서 작성하면 됨
}
