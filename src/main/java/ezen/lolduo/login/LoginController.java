package ezen.lolduo.login;
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

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.summoner.Summoner.Builder;

import ezen.lolduo.common.CommandMap;
import ezen.lolduo.tierBoard.TierBoardService;

@Controller
public class LoginController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Resource(name="tierBoardService")
	private TierBoardService tierBoardService;
	
	@RequestMapping(value="/loginForm")
	public ModelAndView loginForm(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("login/loginForm");
		return mv;
	}
	@RequestMapping(value="/login")
	@ResponseBody
	public int login(CommandMap commandMap, HttpServletRequest request)throws Exception{
		int chk = loginService.loginChk(commandMap.getMap());
		if(chk==1) {
			Orianna.setRiotAPIKey("RGAPI-09562dcb-34a8-44bb-bdf2-eff6f68f6696");
	        Orianna.setDefaultRegion(Region.KOREA);
	        System.out.println("Summoners Tier:::::::::"+commandMap.get("MEM_ID"));
	        String summId = (String) loginService.selectId(commandMap.getMap()).get("MEM_LOLID");
	        int memNum = Integer.parseInt(loginService.selectId(commandMap.getMap()).get("MEM_NUM").toString());
	        Builder summonerBuilder = Orianna.summonerWithId(summId);
			Summoner summoner2 = summonerBuilder.get();
			LeagueEntry leagueEntry = summoner2.getLeaguePosition(Queue.RANKED_SOLO);
			String tier = leagueEntry.getTier().toString() + "_" + leagueEntry.getDivision().toString();
			String summName = summoner2.getName();
//			System.out.println("Summoners Tier:::::::::" + tier);
//			System.out.println("Summoners Name:::::::::" + summName);
			
			//가져온 정보(tier, Name) 업데이트
			commandMap.put("MEM_SUMMONER", summName);
			commandMap.put("MEM_TIER", tier);
			commandMap.put("MEM_NUM", memNum);
			tierBoardService.updateNameTier(commandMap.getMap());
			
	        HttpSession session = request.getSession();
			session.setAttribute("MEM_ID", commandMap.get("MEM_ID")); 
		}
		return chk;
	}
	
	@RequestMapping(value="/loginSuccess")
	public ModelAndView loginSuccess(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("main/main");
		System.out.println(commandMap.get("MEM_ID"));
		
		return mv;
	}
}
