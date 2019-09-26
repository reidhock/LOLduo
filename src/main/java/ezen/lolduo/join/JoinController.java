package ezen.lolduo.join;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ezen.lolduo.common.CommandMap;

@Controller
public class JoinController {
	Logger log=Logger.getLogger(this.getClass());
	
	@Resource(name="joinService")
	private JoinService joinService;
	
	@RequestMapping(value="/joinForm") //회원가입 폼
	public ModelAndView joinForm(CommandMap commandMap) throws Exception{
		
		ModelAndView mv = new ModelAndView("join/joinForm");
		return mv;
	}
	@RequestMapping(value="/signUpComplete") 
	public ModelAndView signUp(CommandMap commandMap) throws Exception{
		joinService.insertUserData(commandMap.getMap());
		
		ModelAndView mv = new ModelAndView("join/signUpComplete");
		mv.addObject("msg","가입완료");
		return mv;
	}
	
	@RequestMapping(value="/idCheck")
	@ResponseBody
	public int idCheck(CommandMap commandMap) throws Exception{
		int checkResult = joinService.selectUserID(commandMap.getMap());
		
		return checkResult;
	}
}
