package kr.ac.kopo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.dao.ExerciseDAO;
import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.service.AccountService;
import kr.ac.kopo.vo.ExerciseVO;
import kr.ac.kopo.vo.UserVO;

@RestController
public class ApiController {
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private ExerciseDAO edao;
	
	@Autowired
	private AccountService accountService;
	
	private final ExerciseMapper exerciseMapper;
	private final UserMapper userMapper;
	
	@Autowired
	ApiController(ExerciseMapper exerciseMapper, UserMapper userMapper) {
		this.userMapper = userMapper;
		this.exerciseMapper = exerciseMapper;
	}

	
	@RequestMapping("/pyAppMapping")
	public Map<String, String> webIp() {
		
		Map<String, String> apiMapping = new HashMap<String, String>();

		String host = "http://localhost:8088";
		
		apiMapping.put("login", host+"/pyLogin?");
		apiMapping.put("dumbbellEnd", host+"/exercisePy?");
		apiMapping.put("pushupEnd", host+"/exercisePy?");
		apiMapping.put("fingerCnt", host+"/fingerCounterPy");
		
		return apiMapping;

	}        
	
	@RequestMapping("/pyLogin")
	public ResponseEntity<UserVO> pyLogin(@RequestBody UserVO vo) {
		UserVO sentData = new UserVO();
		
		System.out.println(vo.getUserId() + " : "  + vo.getPassword());
		boolean result = accountService.userLogin(vo.getUserId(), vo.getPassword());
		
		if(result) {
			sentData = vo;
		}
		
		System.out.println(sentData);
		return ResponseEntity.ok(sentData);
	}
	
	@RequestMapping("/fingerCounterPy")
	public String fingerCount(@RequestBody ExerciseVO vo, Model model) {
		System.out.println("fingerCount: " + vo.getCnt());
		
		return "Index";
	}
	
	@RequestMapping("/exercisePy")
	public String getDataTest(@RequestBody ExerciseVO apiVo, Model model) {
		System.out.println("IndexController: " + apiVo);
		exerciseMapper.insert_exerciseData(apiVo);
		
		return "Index";
	}
	
	
	

}




