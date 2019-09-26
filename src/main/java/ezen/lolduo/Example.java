package ezen.lolduo;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class Example {
	public static void main(String[] args) {
        Orianna.setRiotAPIKey("RGAPI-2c756ac5-19b3-4a9e-99b8-1a484c1e89bd");
        Orianna.setDefaultRegion(Region.KOREA);

        Summoner summoner = Orianna.summonerNamed("협곡성").get();
        System.out.println(summoner.getName() + " is level " + summoner.getLevel() + " on the " + summoner.getRegion() + " server.");
        
        Champions champions = Orianna.getChampions();
        Champion randomChampion = champions.get((int)(Math.random() * champions.size()));
        System.out.println("He enjoys playing champions such as " + randomChampion.getName());
        System.out.println(summoner.getCoreData());
		/*
		 * League challengerLeague =
		 * Orianna.challengerLeagueInQueue(Queue.RANKED_SOLO).get(); Summoner bestNA =
		 * challengerLeague.get(0).getSummoner();
		 * System.out.println("He's not as good as " + bestNA.getName() +
		 * " at League, but probably a better Java programmer!");
		 */ 
        }
}
