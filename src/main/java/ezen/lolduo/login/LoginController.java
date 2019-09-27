package ezen.lolduo.login;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ezen.lolduo.common.CommandMap;

@Controller
public class LoginController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@RequestMapping(value="/loginForm")
	public ModelAndView loginForm(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("login/loginForm");
		return mv;
	}
	@RequestMapping(value="/login")
	@ResponseBody
	public int login(CommandMap commandMap,HttpServletRequest request)throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		int chk = loginService.loginChk(commandMap.getMap());
		return chk;
	}
	
	@RequestMapping(value="/loginSuccess")
	public ModelAndView loginSuccess(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("main/main");
		HttpSession session = request.getSession();
		session.setAttribute("MEM_ID", commandMap.get("MEM_ID")); 
		return mv;
	}
}
