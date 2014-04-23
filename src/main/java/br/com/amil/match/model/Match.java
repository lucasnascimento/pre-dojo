package br.com.amil.match.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(of="matchId")
public class Match {

	private String matchId;
	private Set<Kill> kills = new TreeSet<Kill>();
	private Map<String, PlayerStats> playersMap = new HashMap<String, PlayerStats>();
	
	private String streaker;
	
	private String startDate;
	private String endDate;
	
	public Match (String[] values){
		this.setStartDate(values[0]);
		this.setMatchId(values[1]);
	}
	
	public void endMatch(){
		for (Kill kill_log : this.getKills()){
			
			if (!kill_log.getKiller().equalsIgnoreCase("world")) {
				
				PlayerStats killer = this.getPlayersMap().get(
						kill_log.getKiller());
				if (killer == null) {
					killer = new PlayerStats();
					killer.setName(kill_log.getKiller());
					killer.increaseKill();

					GunStats gun = new GunStats();
					gun.setName(kill_log.getGun());
					gun.increaseKill();

					killer.getGunsMap().put(gun.getName(), gun);
				} else {
					killer.increaseKill();

					GunStats gunStats = killer.getGunsMap().get(kill_log.getGun());
					
					if (gunStats == null){
						gunStats = new GunStats();
						gunStats.setName(kill_log.getGun());
					}
					
					gunStats.increaseKill();

					killer.getGunsMap().put(gunStats.getName(), gunStats);
				}
				
				Integer streakCount = killer.getStreakStrikes().get(killer.getStreakCount());
				if (streakCount == null)
					killer.getStreakStrikes().put(killer.getStreakCount(), 1);
				else
					killer.getStreakStrikes().put(killer.getStreakCount(), ++streakCount);
				

				if (!killer.isKilleInstinct()){
					if (kill_log.getKillMinute().equalsIgnoreCase( kill_log.getKillMinute() )){
						killer.increaseMinuteKillCount();
						if (killer.getMinuteKillCount() >= 5 ){
							killer.setKilleInstinct(true);
						}
					}else{
						killer.setMinuteKillCount(0);
					}
				}				
				
				this.getPlayersMap().put(killer.getName(), killer);
			
				PlayerStats killed = this.getPlayersMap().get(kill_log.getKilled());
				if (killed == null) {
					killed = new PlayerStats();
					killed.setName(kill_log.getKilled());
					killed.increaseKilled();
				}else{
					killed.increaseKilled();
				}
				
				killed.increaseStreakCount();
				this.getPlayersMap().put(killed.getName(), killed);
				
			}
		}
		findStreaker();
	}

	private void findStreaker() {
		
		int lastStrike = 0;
		
		for (PlayerStats player : playersMap.values()){
			
			int playerMajorStreak = player.findMajorStreak();
			
			if (lastStrike <= playerMajorStreak ){
				streaker = player.getName();
				lastStrike = playerMajorStreak;
			}
		}
	}

	public String printMatchResume(){
		
		Comparator<String> playerComparator = new PlayerComparator(playersMap);
		Map<String,PlayerStats> playerMapSorted = new TreeMap<String, PlayerStats>(playerComparator);
		playerMapSorted.putAll(playersMap);

		
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("Match %s \nPosition  - PlayerName (Kills/Killed)", matchId));
		
		
		int i = 0;
		for (PlayerStats player : playerMapSorted.values()){
			sb.append(String.format("\n\t%d - %s (%d/%d) ", ++i, player.getName(), player.getKillCount(), player.getKilledCount()));
			
			if ( i == 1){
				
				Comparator<String> gunComparator = new GunComparator(player.getGunsMap());
				Map<String, GunStats> gunMapSorted = new TreeMap<String, GunStats>(gunComparator);
				gunMapSorted.putAll(player.getGunsMap());
				
				if (player.getGunsMap().size() > 0)
					sb.append(" WINNER'S GUN(").append( gunMapSorted.entrySet().iterator().next().getValue().getName() ).append(")");
				if (player.getKilledCount() == 0)
					sb.append(" AWARD (No killed Winner) ");

			}
			
			if (player.getName().equals(streaker)){
				sb.append(" AWARD (Streaker)");
			}
			
			if (player.isKilleInstinct()){
				sb.append(" AWARD (Killer Instinct 5 kill in one minute)");
			}
			
		}
		
		return sb.toString();
	}
	
}
