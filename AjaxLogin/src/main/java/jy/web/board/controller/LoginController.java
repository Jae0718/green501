package jy.web.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	
	@RequestMapping(value="/")
	public String loginMain() {
		return "login.jsp";
	}
	
	@RequestMapping(value="goLogin.do")
	public String goLogin(
			@RequestParam(value="pid")String pid
			) {
		return "list.jsp";
	}
}
