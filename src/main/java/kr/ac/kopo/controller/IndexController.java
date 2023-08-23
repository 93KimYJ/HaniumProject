package kr.ac.kopo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.dao.ExerciseDAO;
import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.vo.ExerciseVO;
import kr.ac.kopo.vo.UserVO;

@Controller
public class IndexController {
	
	@Autowired
	private UserVO uvo;
	
	@Autowired
	private ExerciseVO evo;

	@Autowired
	private ExerciseDAO dao;
	
	private final UserMapper userMapper;
	private final ExerciseMapper exerciseMapper;
	
	@Autowired
    public IndexController(UserMapper userMapper, ExerciseMapper exerciseMapper) {
        this.userMapper = userMapper;
        this.exerciseMapper = exerciseMapper;
    }
	
	@RequestMapping("/index")
	public String index(Model model) {
//		List<ExerciseVO> evoList = dao.getExerciseData("idid");
//		
		
		List<ExerciseVO> evoList = exerciseMapper.selectExerciseDataTest("idid");
		
		String todayCount = exerciseMapper.selectTodayExerciseCount("dumbbel", "idid");
		String weekCount = exerciseMapper.selectWeekExerciseCount("dumbbel", "idid");
		System.out.println(todayCount + " " + weekCount);
		
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
	
	@RequestMapping("/executeLogin")
	public String toLogin(Model model, String id, String password) {
		uvo.setUserId(id);
		uvo.setPassword(password);
		
		UserVO isLoginVO = userMapper.userCheck(uvo);
		
		if(isLoginVO != null) {		
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
		
		return "account/SingIn";
	}
	
	@RequestMapping("/toSingUp")
	public String toSingUp(Model model) {
		
		return "account/SingUp";
	}
	
}
