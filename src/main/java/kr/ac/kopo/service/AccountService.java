package kr.ac.kopo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.vo.UserVO;

@Service
public class AccountService {
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private UserVO vo;
	
	private final UserMapper userMapper;
	
	@Autowired
	AccountService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public boolean userLogin(String id, String pw) {
		boolean result = false;
		
		vo.setUserId(id);
		vo.setPassword(pw);
		
		String logResult = userMapper.userCheck(vo);
		
		if(logResult != null) {
			result = true;
		}
		
		return result;
	}
}
