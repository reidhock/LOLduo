package ezen.lolduo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping(value="/")
	public ModelAndView goMain() {
		ModelAndView mv = new ModelAndView("main/main");
		return mv;
	}
}
