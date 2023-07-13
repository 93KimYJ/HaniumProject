package kr.ac.kopo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.dao.ExerciseDAO;
import kr.ac.kopo.vo.ExerciseVO;

@Controller
public class IndexController {
	
	@Autowired
	private ExerciseVO evo;

	@Autowired
	private ExerciseDAO dao;
	
	@RequestMapping("/index")
	public String index(Model model) {
		List<ExerciseVO> evoList = dao.getExerciseData("idid");
		
		model.addAttribute("exList" , evoList);
		System.out.println("IndexController:"+evoList);
		
		return "MainPage";
	}
	
	@RequestMapping("/mainPage")
	public String mainPage(Model model) {
		
		return "MainPage";
	}
	
	@RequestMapping("/exercisePy")
	public String getDataTest(@RequestBody ExerciseVO apiVo, Model model) {
		System.out.println("IndexController: " + apiVo);
		dao.insertExerciseData(apiVo);
		
		return "Index";
	}
	
}
