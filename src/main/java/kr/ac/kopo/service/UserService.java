package kr.ac.kopo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.vo.UserVO;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void signUpUser(UserVO user) {
    	
        userMapper.signUp_user(user);
    }
}