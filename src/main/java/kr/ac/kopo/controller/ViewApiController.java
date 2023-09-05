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
import kr.ac.kopo.vo.ExerciseVO;

@RestController
public class ViewApiController {
	@Autowired
	private ExerciseVO evo;
	
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
			
		Map<String, Integer> responseMap = new HashMap<String, Integer>();
		
		evo.setType(type);
		evo.setUserId("idid");
		
		// count
		Integer todayCount = exerciseMapper.select_today_exerciseCount_withUid_andType(evo);
		Integer weekCount = exerciseMapper.select_week_exerciseCount_withUid_andType(evo);
		Integer monthCount = exerciseMapper.select_month_exerciseCount_withUid_andType(evo);
		Integer yearCount = exerciseMapper.select_year_exerciseCount_withUid_andType(evo);
		Integer alltimeCount = exerciseMapper.select_allTime_exerciseCount_withUid_andType(evo);
		
		// tryCnt
		Integer todayTry = exerciseMapper.select_today_exerciseTryCount_withUid_andType(evo);
		Integer weekTry = exerciseMapper.select_week_exerciseTryCount_withUid_andType(evo);
		Integer monthTry = exerciseMapper.select_month_exerciseTryCount_withUid_andType(evo);
		Integer yearTry = exerciseMapper.select_year_exerciseTryCount_withUid_andType(evo);
		Integer alltimeTry = exerciseMapper.select_allTime_exerciseTryCount_withUid_andType(evo);
		
		// 
		responseMap.put("todayCount", todayCount);
		responseMap.put("weekCount", weekCount);
		responseMap.put("monthCount", monthCount);
		responseMap.put("yearCount", yearCount);
		responseMap.put("allTimeCount", alltimeCount);
		
		responseMap.put("todayTry", todayTry);
		responseMap.put("weekTry", weekTry);
		responseMap.put("monthTry", monthTry);
		responseMap.put("yearTry", yearTry);
		responseMap.put("allTimeTry", alltimeTry);
		
		return ResponseEntity.ok(responseMap);
	}
}
