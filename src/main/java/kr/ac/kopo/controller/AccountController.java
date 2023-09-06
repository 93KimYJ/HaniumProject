package kr.ac.kopo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ac.kopo.dao.ExerciseMapper;
import kr.ac.kopo.dao.UserMapper;
import kr.ac.kopo.service.AccountService;
import kr.ac.kopo.vo.ExerciseVO;
import kr.ac.kopo.vo.UserVO;

@Controller
public class AccountController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserVO uvo;
	
	@Autowired
	private ExerciseVO evo;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ExerciseMapper exerciseMapper;
	
    public AccountController() {

    }
	
	@PostMapping("executeLogin")
	public String login(Model model, String uid, String password) {
		
		boolean result = accountService.userLogin(uid, password);
		
		if (result) {
			System.out.println("로그인 성공");
			session.setAttribute("uid", uid);
		} else {
			System.out.println("로그인 실패");
		}

		return "redirect:/index";
	}
	
	@PostMapping("exequteLogout")
	public String logout(Model model) {
		session.removeAttribute("uid");
		return "Index";
	}
	
	@PostMapping("executeSignUp")
	public String singUp() {
		return null;
	}
}
