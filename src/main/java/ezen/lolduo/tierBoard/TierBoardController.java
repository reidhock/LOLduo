package ezen.lolduo.tierBoard;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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


@Controller
public class TierBoardController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "tierBoardService")
	private TierBoardService tierBoardService;
	
	@RequestMapping(value="/tierBoard")
	public ModelAndView openTierBoard(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("tierBoard/tierBoard");
		List<Map<String,Object>> list = tierBoardService.selectBoardList(commandMap.getMap()); 
		mv.addObject("list", list);
		
		return mv;
	}
	
	
	@RequestMapping(value="/Refresh")
	public @ResponseBody Map<String, Object> refresh(HttpServletRequest request, CommandMap commandMap)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Orianna.setRiotAPIKey("RGAPI-a16b07af-51cc-4b35-9518-711a408e2b57");
        Orianna.setDefaultRegion(Region.KOREA);
        
        //ajax를 통해 받아온 MEM_NUM으로 encryptedID 받아옴
		commandMap.put("MEM_NUM", request.getParameter("num"));
		String summId = (String) tierBoardService.selectId(commandMap.getMap()).get("MEM_LOLID");
//		System.out.println("EncryptedID :::::::::" + summId);
		
		//받아온 EncryptedID로 롤 api에 요청해서 summoner 객체 생성
		Builder summonerBuilder = Orianna.summonerWithId(summId);
		Summoner summoner2 = summonerBuilder.get();
		LeagueEntry leagueEntry = summoner2.getLeaguePosition(Queue.RANKED_SOLO);
		
		String tier = leagueEntry.getTier().toString() + "_" + leagueEntry.getDivision().toString();
		String summName = summoner2.getName();
//		System.out.println("Summoners Tier:::::::::" + tier);
//		System.out.println("Summoners Name:::::::::" + summName);
		
		//가져온 정보(tier, Name) 업데이트
		commandMap.put("MEM_SUMMONER", summName);
		commandMap.put("MEM_TIER", tier);
		
		tierBoardService.updateNameTier(commandMap.getMap());
		
		return map;
	}
}
