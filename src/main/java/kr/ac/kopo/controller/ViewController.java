package kr.ac.kopo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.dao.ExerciseDAO;
import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.service.AccountService;
import kr.ac.kopo.vo.ExerciseVO;
import kr.ac.kopo.vo.UserVO;

@Controller
public class ViewController {
	
	@Autowired
	private UserVO uvo;
	
	@Autowired
	private ExerciseVO evo;

	@Autowired
	private ExerciseDAO dao;
	

	
	private final UserMapper userMapper;
	private final ExerciseMapper exerciseMapper;
	
	@Autowired
    public ViewController(UserMapper userMapper, ExerciseMapper exerciseMapper) {
        this.userMapper = userMapper;
        this.exerciseMapper = exerciseMapper;
    }
	
	@RequestMapping("/index")
	public String index(Model model) {
		// 실험을 위해 각종 변수들로 지저분함
//		List<ExerciseVO> evoList = dao.getExerciseData("idid");	
		
		List<ExerciseVO> evoList = exerciseMapper.selectExerciseDataTest("idid");
		
		String todayCount = exerciseMapper.selectTodayExerciseCount("dumbbel", "idid");
		
		model.addAttribute("exList" , evoList);
		model.addAttribute("todayCount", todayCount);
		
		System.out.println("IndexController:"+evoList);
		UserVO vo = userMapper.getUserEmailwithId("idid");
		System.out.println(vo);
		
		return "Index";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		
		return "account/SingIn";
	}
	
	@RequestMapping("/toSingUp")
	public String toSingUp(Model model) {
		return "account/SingUp";
	}
	
	@GetMapping("/toMyPage")
	public String toMyPage(Model model, String id) {
		
		evo.setUserId("idid");
		evo.setType("dumbbel");
		
		int totalCount = exerciseMapper.selectTotalExerciseCount(evo);
		
		Integer getMonth = exerciseMapper.selectMonthExerciseCount("dumbbel", "idid");
		int monthCount = getMonth == null ? 0 : getMonth; // 이러한 형태의 로직은 서비스단으로 분리할것
		
		int totalTry = exerciseMapper.selectTotalExerciseTry("idid");
		
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("monthCount", monthCount);
		model.addAttribute("totalTry", totalTry);
		
		
		return "myPage/MyPage";
	}
	
	
	
}
