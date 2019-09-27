package ezen.lolduo.message;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ezen.lolduo.common.CommandMap;

@Controller
public class MessageController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="messageService")
	private MessageService messageService;
	
	@RequestMapping(value="/messageList")	
	public ModelAndView messageList(CommandMap commandMap)throws Exception{
		ModelAndView mv=new ModelAndView("message/messageList");
		
		List<Map<String,Object>> list=messageService.messageList(commandMap.getMap());
		mv.addObject("list", list);
		
		return mv;
	}
}
