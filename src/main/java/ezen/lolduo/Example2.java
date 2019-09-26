package ezen.lolduo;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.summoner.Summoners;

import java.util.List;
public class Example2 {
	 public static void main(final String[] args) {

//	        final Summoner summoner = Summoner.named("협곡성").withRegion(Region.KOREA).get();
	        Orianna.setRiotAPIKey("RGAPI-2c756ac5-19b3-4a9e-99b8-1a484c1e89bd");

	        Orianna.setDefaultRegion(Region.KOREA);
	        Summoner summoner = Orianna.summonerNamed("협곡성").get();
	        System.out.println("Name: " + summoner.getName());
	        System.out.println("ID: " + summoner.getId());
	        final LeagueEntry qq = summoner.getLeaguePosition(Queue.RANKED_SOLO);
	        System.out.println("협곡성 ::"+qq.getTier());
	        System.out.println(qq.getDivision());
	        final LeagueEntry rankedFivesEntries = summoner.getLeaguePosition(Queue.RANKED_SOLO);
	        if(rankedFivesEntries.getPromos() != null) {
	            // If the summoner is in their promos, print some info
	            System.out.println("Promos progress: " + rankedFivesEntries.getPromos().getProgess());
	            System.out.println("Promos wins: " + rankedFivesEntries.getPromos().getWins());
	            System.out.println("Promos losses: " + rankedFivesEntries.getPromos().getLosses());
	            System.out.println("Games not yet played in promos: " + rankedFivesEntries.getPromos().getNotPlayed());
	            System.out.println("Number of wins required to win promos: " + rankedFivesEntries.getPromos().getWinsRequired());
	        } else {
	            System.out.println("The summoner is not in their promos.");
	        }

	        System.out.println("Name of leagues this summoner is in:");
	        final LeaguePositions positions = summoner.getLeaguePositions();
	        for(final LeagueEntry entry : positions) {
	            System.out.println(entry.getLeague().getName());
	        }
	        System.out.println("\n");

	        final List<League> leagues = summoner.getLeagues();
	        System.out.println("Name of leagues this summoner is in (called from a different endpoint):");
	        for(final League league : leagues) {
	            System.out.println(league.getName());
	        }
	        System.out.println("\n");

	        final League rankedFivesLeague = summoner.getLeague(Queue.RANKED_SOLO);
	        System.out.println("Listing all summoners in " + rankedFivesLeague.getName());
	        for(final LeagueEntry entry : rankedFivesLeague) {
	            System.out.println(entry.getSummoner().getName() + " " + entry.getLeaguePoints() + " " + rankedFivesLeague.getTier() + " " + entry.getDivision());
	        }
	        System.out.println("\n");
	        System.out.println("Challenger League name:");
	        final League challenger = League.challengerInQueue(Queue.RANKED_SOLO).withRegion(Region.NORTH_AMERICA).get();
	        System.out.println(challenger.getName());
	    }
}
