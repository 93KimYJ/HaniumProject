package kr.ac.kopo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.service.AccountService;
import kr.ac.kopo.service.ExerciseDataService;
import kr.ac.kopo.service.UserService;
import kr.ac.kopo.vo.ExerciseVO;
import kr.ac.kopo.vo.UserVO;

@Controller
public class ViewController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserVO uvo;
	
	@Autowired
	private ExerciseVO evo;
	
	@Autowired
	private ExerciseDataService exerciseService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserService userService;

	  
	@Autowired
	private ExerciseMapper exerciseMapper;
	
    public ViewController() {
    	
    }
	
	@RequestMapping("/index")
	public String index(Model model) {
		String uid = (String)session.getAttribute("uid");
		uid = uid != null ? uid : "";
		
		evo.setType("dumbbel");
		evo.setUserId(uid);
		
		List<ExerciseVO> evoList = exerciseMapper.select_ExerciseDataTest(uid);
		Integer todayCount = exerciseMapper.select_today_exerciseCount_withUid_andType(evo);
		List<ExerciseVO> topList = exerciseMapper.select_allTime_topFiveExerciseCount_withType("dumbbel");
		
		model.addAttribute("exList" , evoList);
		model.addAttribute("todayCount", todayCount);
		model.addAttribute("topFiveList", topList);
		System.out.println(topList);
		
		return "Index";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		
		return "account/Login";
	}
	
/*	@RequestMapping("/toSignUp")
	public String toSingUp(Model model) {
		return "account/SignUp";
	}
	*/
	@GetMapping("/toSignUp")
	public String toSignUp(Model model) {
	    return "account/SignUp";
	    }
	
	@PostMapping("/signup")
	public String signUp(UserVO user, Model model) {
		// 회원가입 서비스 호출
		System.out.println(user);
		userService.signUpUser(user);
		System.out.println(user);
		return "redirect:/toLogin"; // 회원가입 성공 시 로그인 페이지로 이동
	}
	
	@GetMapping("/toMyPage")
	public String toMyPage(Model model) {
		ExerciseVO evo2 = new ExerciseVO();
		String uid = (String)session.getAttribute("uid");
		
		evo.setUserId(uid);
		evo.setType("dumbbel");
		

		Integer totalTry = exerciseMapper.select_allTime_exerciseTryCount_withUid(uid);
		List<ExerciseVO> exRecodeList = exerciseService.getExerciseRecode(17, 5, uid);
		
		System.out.println(exRecodeList);
		
		model.addAttribute("totalTry", totalTry);
		model.addAttribute("exRecodeList", exRecodeList);
		
		/*
		 기능
		 
		 유저 운동 통계
		 	기본 보여주기
		 		오늘, 이번주, 이번달 가장 많이 한 운동
		 			세트, 카운트
		 	선택해서 상세 보기
		 		시간별 세트, 카운트
		 
		 */
		
		return "myPage/MyPage";
	
	}
	
}
