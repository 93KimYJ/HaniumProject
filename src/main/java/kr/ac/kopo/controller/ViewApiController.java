package kr.ac.kopo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.dao.ExerciseDAO;
import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.service.AccountService;

@RestController
public class ViewApiController {
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private ExerciseDAO edao;
	
	@Autowired
	private AccountService accountService;
	
	private final ExerciseMapper exerciseMapper;
	private final UserMapper userMapper;
	
	@Autowired
	ViewApiController(ExerciseMapper exerciseMapper, UserMapper userMapper) {
		this.userMapper = userMapper;
		this.exerciseMapper = exerciseMapper;
	}
	
	@GetMapping("/getExerciseCount")
	public ResponseEntity<Map<String, Integer>> ajaxTest(@RequestParam  String type) {
			
		Map<String, Integer> exCountMap = new HashMap<String, Integer>();
		
		System.out.println(type);
		int tryNum = exerciseMapper.selectExerciseTryWithType(type, "idid");
		exCountMap.put(type, tryNum);
		
		return ResponseEntity.ok(exCountMap);
	}
}
