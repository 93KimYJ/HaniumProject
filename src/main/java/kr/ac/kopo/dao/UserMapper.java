package kr.ac.kopo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.ac.kopo.vo.UserVO;

@Mapper
public interface UserMapper {


	@Select("SELECT EMAIL FROM HC_USER WHERE USER_ID = #{id}")
	UserVO getUserEmailwithId(String id);
}
