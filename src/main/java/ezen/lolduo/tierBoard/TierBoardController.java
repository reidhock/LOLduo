package ezen.lolduo.tierBoard;
import java.util.List;
import java.util.Map;

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
public class TierBoardController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "tierBoardService")
	private TierBoardService tierBoardService;
	
	@RequestMapping(value="/tierBoard")
	public ModelAndView openTierBoard(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("tierBoard/tierBoard");
		List<Map<String,Object>> list = tierBoardService.selectBoardList(commandMap); 
		mv.addObject("list", list);
		
		return mv;
	}
}
