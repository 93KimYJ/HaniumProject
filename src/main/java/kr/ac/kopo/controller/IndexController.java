package kr.ac.kopo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.dao.ExerciseDAO;
import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.vo.ExerciseVO;
import kr.ac.kopo.vo.UserVO;

@Controller
public class IndexController {
	
	@Autowired
	private ExerciseVO evo;

	@Autowired
	private ExerciseDAO dao;
	
	private final UserMapper userMapper;
	@Autowired
    public IndexController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
	
	@RequestMapping("/index")
	public String index(Model model) {
		List<ExerciseVO> evoList = dao.getExerciseData("idid");
		
		model.addAttribute("exList" , evoList);
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
	
}
