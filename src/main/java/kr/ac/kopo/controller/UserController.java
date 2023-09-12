package kr.ac.kopo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ac.kopo.service.UserService;
import kr.ac.kopo.vo.UserVO;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/SignUp")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserVO());
        return "SignUp"; // signup.jsp로 이동
    }

    @GetMapping("/")
    public String index() {
        // 홈 페이지로 이동
        return "redirect:/login"; // 혹은 실제로 표시할 홈 페이지 URL을 지정하십시오.
    }
    
    @PostMapping("/SignUp")
    public String processSignUp(UserVO user) {
        userService.signUpUser(user);
        // 회원가입 성공 후 로그인 페이지로
        return "redirect:/login";
    }
    
    
}