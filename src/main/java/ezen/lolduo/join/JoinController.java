package ezen.lolduo.join;
import javax.annotation.Resource;
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
		
		//API을 써서 MEM_TIER, MEM_LOLID를 넣어라. 
		Orianna.setRiotAPIKey("RGAPI-9a34ccda-ab35-48f5-8bef-eba5338d16ec");
        Orianna.setDefaultRegion(Region.KOREA);
        String summonerName = commandMap.get("MEM_SUMMONER").toString();
        	System.out.println(summonerName);
        Summoner summoner = Orianna.summonerNamed(summonerName).get();
        String lolId = summoner.getId();
        	System.out.println("lolID="+lolId);
        final LeagueEntry summonerEntry = summoner.getLeaguePosition(Queue.RANKED_SOLO);
        String tier = (summonerEntry.getTier().toString())+"_"+(summonerEntry.getDivision().toString());
        commandMap.put("MEM_LOLID", lolId);
        commandMap.put("MEM_TIER", tier);
		
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

	@RequestMapping(value="summonerCheck") // 실제 소환사명이 롤에 존재하는 지와 사이트에 존재하는 지를 체크. 
	@ResponseBody
	public int summonerCheck(CommandMap commandMap) throws Exception{
		Orianna.setRiotAPIKey("RGAPI-9a34ccda-ab35-48f5-8bef-eba5338d16ec");
        Orianna.setDefaultRegion(Region.KOREA);
        String summonerName = commandMap.get("MEM_SUMMONER").toString();
        Summoner summoner = Orianna.summonerNamed(summonerName).get();
        int checkResult;
        if(summoner.exists()) {
        	int check = joinService.selectSummonerName(commandMap.getMap());
        		if(check==1) {
        			checkResult = 1; // 중복된 소환사명입니다. 
        		}
        		else {
        			checkResult = 2; // 사용 가능한 소환사명입니다. 
        		}
        }
        else {
        	checkResult = 0; // 존재 하지 않는 소환사 명입니다. 
        }
		return checkResult;
	}
}







