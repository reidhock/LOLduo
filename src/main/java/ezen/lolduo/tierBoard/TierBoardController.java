package ezen.lolduo.tierBoard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.summoner.Summoner.Builder;

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
	
	
	@RequestMapping(value="/Refresh")
	public @ResponseBody Map<String, Object> refresh(HttpServletRequest request, CommandMap commandMap)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Orianna.setRiotAPIKey("RGAPI-a16b07af-51cc-4b35-9518-711a408e2b57"); //롤 API 키 입력
        Orianna.setDefaultRegion(Region.KOREA); //롤 지역 서버 입력
        
        //ajax를 통해 받아온 MEM_NUM으로 encryptedID 받아옴
		commandMap.put("MEM_NUM", request.getParameter("num"));
		String summId = (String) tierBoardService.selectId(commandMap.getMap()).get("MEM_LOLID");
//		System.out.println("EncryptedID :::::::::" + summId);
		
		//받아온 EncryptedID로 롤 api에 요청해서 summoner 객체 생성
		Builder summonerBuilder = Orianna.summonerWithId(summId);
		Summoner summoner2 = summonerBuilder.get();
		LeagueEntry leagueEntry = summoner2.getLeaguePosition(Queue.RANKED_SOLO); //솔랭을 전제로 함
		
		String tier = leagueEntry.getTier().toString() + "_" + leagueEntry.getDivision().toString();
		String summName = summoner2.getName();
//		System.out.println("Summoners Tier:::::::::" + tier);
//		System.out.println("Summoners Name:::::::::" + summName);
		
		//가져온 정보(tier, Name) 업데이트
		commandMap.put("MEM_SUMMONER", summName);
		commandMap.put("MEM_TIER", tier);
		
		tierBoardService.updateNameTier(commandMap.getMap());
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		//이번 시즌 가장 많이 플레이 한 챔프 10개를 추출하는 파트
		final Summoner summoner = Summoner.named(summName).withRegion(Region.KOREA).get();
        // A MatchHistory is a lazy list, meaning it's elements only get loaded as-needed.

		final MatchHistory matchHistory = MatchHistory.forSummoner(summoner).withQueues(Queue.RANKED_SOLO).withSeasons(Season.SEASON_9).get(); //솔랭을 전제로 함
        // MatchHistory match_history = MatchHistory.forSummoner(summoner).withQueues([Queue.RANKED_SOLO_5x5]).withSeasons([Season.SEASON_7]).get();

        // Load the entire match history by iterating over all its elements so that we know how long it is.
        // Unfortunately since we are iterating over the match history and accessing the summoner's champion for each match,
        // we need to know what version of the static data the champion should have. To avoid pulling many different
        // static data versions, we will instead create a {champion_id -> champion_name} mapping and just access the champion's
        // ID from the match data (which it provides directly).
        final Map<Integer, String> championIdToNameMapping = new HashMap<>();
        for(final Champion champion : Champions.withRegion(Region.KOREA).get()) {
            championIdToNameMapping.put(champion.getId(), champion.getName());
        }
        final Map<String, Integer> playedChampions = new HashMap<>();
        for(final Match match : matchHistory) {
            final Integer championId = match.getParticipants().find(summoner).getChampion().getId();
            final String championName = championIdToNameMapping.get(championId);
            Integer count = playedChampions.get(championName);
            if(count == null) {
                count = 0;
                playedChampions.put(championName, count);
            }
            playedChampions.put(championName, playedChampions.get(championName) + 1);
        }
        System.out.println("Length of match history: " + matchHistory.size()); // 해당 시즌 총 게임판수를 긁어온다. 
        
        final LeagueEntry rankedFivesEntries = summoner.getLeaguePosition(Queue.RANKED_SOLO);  //솔랭을 전제로 함
        int totalWins = rankedFivesEntries.getWins();
        int totalLosses = rankedFivesEntries.getLosses();
        System.out.println("winwinwinwin: "+ rankedFivesEntries.getWins());//해당 시즌 총 게임의 승수를 구해오기
        System.out.println("loseloselose: "+ rankedFivesEntries.getLosses());//해당 시즌 총 게임의 패수를 구해오기
        // Print the top ten aggregated champion results
        final List<Entry<String, Integer>> entries = new ArrayList<>(playedChampions.entrySet());
        entries.sort((final Entry<String, Integer> e0, final Entry<String, Integer> e1) -> Integer.compare(e1.getValue(), e0.getValue()));

        for(int i = 0; i < 10 && i < entries.size(); i++) { // 가장 많이 플레이한 챔피언 순서대로 10개 불러오는 반복문. 
            final String championName = entries.get(i).getKey();
            final int count = entries.get(i).getValue();
            System.out.println(championName + " " + count);
        }
		return map;
	}
}
