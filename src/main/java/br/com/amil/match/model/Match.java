package br.com.amil.match.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(of="matchId")
public class Match {

	private String matchId;
	private Set<Kill> kills = new HashSet<Kill>();
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
					gunStats.increaseKill();

					killer.getGunsMap().put(gunStats.getName(), gunStats);
				}
				
				Integer streakCount = killer.getStreakStrikes().get(killer.getStreakCount());
				if (streakCount == null)
					killer.getStreakStrikes().put(killer.getStreakCount(), 1);
				else
					killer.getStreakStrikes().put(killer.getStreakCount(), streakCount++);
				
				this.getPlayersMap().put(killer.getName(), killer);
			}
			
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
		findStreaker();
		
	}

	private void findStreaker() {
		
		int lastStrike = 0;
		
		for (PlayerStats player : playersMap.values()){
			
			if (lastStrike <= player.findMajorStreak()){
				streaker = player.getName();
				lastStrike = player.findMajorStreak();
			}
		}
	}

	public String printMatchResume(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("Match %s \nPosition  - PlayerName (Kills/Killed)", matchId));
		
		int i = 0;
		for (PlayerStats player : playersMap.values()){
			sb.append(String.format("\n\t%d - %s (%d/%d) ", ++i, player.getName(), player.getKillCount(), player.getKilledCount()));
			
			if ( i == 1){
				if (player.getGunsMap().size() > 0)
					sb.append(" WINNER'S GUN(").append( player.getGunsMap().entrySet().iterator().next().getValue().getName() ).append(")");
				if (player.getKilledCount() == 0)
					sb.append(" AWARD (No killed Winner) ");

			}
			
			if (player.getName().equals(streaker)){
				sb.append(" AWARD (Streaker)");
			}
			
			
		}
		
		return sb.toString();
	}
	
}
