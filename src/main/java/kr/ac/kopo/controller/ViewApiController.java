package kr.ac.kopo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.service.AccountService;
import kr.ac.kopo.service.ExerciseDataService;
import kr.ac.kopo.vo.ExerciseVO;

@RestController
public class ViewApiController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ExerciseDataService exerciseService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ExerciseMapper exerciseMapper;
	
	ViewApiController() {
		
	}
	
	@GetMapping("/getExerciseCount")
	public ResponseEntity<Map<String, Object>> getExerciseData(@RequestParam  String type) {
		String uid = (String)session.getAttribute("uid");
		
		ExerciseVO evo = new ExerciseVO();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
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
		
		
		Integer allUserTryCnt = exerciseMapper.select_allTime_allUserExerciseTryCountAverage();
		System.out.println(allUserTryCnt);
		
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
	
	@GetMapping("/getExerciseRecode")
	public ResponseEntity<Map<String, Object>> getExerciseRecode(@RequestParam  String type) {
		String uid = (String)session.getAttribute("uid");
		List<ExerciseVO> exerciseRecode = exerciseService.getExerciseRecode(-1, 5, uid, type);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("exerciseRecode", exerciseRecode);
		
		return ResponseEntity.ok(responseMap);
	}
	
	@GetMapping("/getBestExerciseRecode")
	public ResponseEntity<Map<String, Object>> getBestExerciseRecode(@RequestParam  String type) {
		List<ExerciseVO> exerciseRecode = exerciseService.getBestExerciseRecodeWithType(type, 5);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("exerciseRecode", exerciseRecode);
		
		return ResponseEntity.ok(responseMap);
	}
}
