package br.com.amil.match.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(of="name")
public class PlayerStats {

	private String name;
	private int killCount = 0;
	private int killedCount = 0;
	private Map<String, GunStats> gunsMap = new HashMap<String, GunStats>(); 
	
	private String streakCount = "0";
	private Map<String,Integer> streakStrikes = new HashMap<String, Integer>();
	
	private boolean killeInstinct;
	private String minuteKill = "";
	private int minuteKillCount = 0;
	
	public void increaseKill(){
		this.killCount ++;
	}

	public void increaseKilled(){
		this.killedCount ++;
	}
	
	public void increaseStreakCount(){
		int _streakCount = Integer.parseInt(streakCount);
		_streakCount++;
		this.streakCount = ""+_streakCount;
	}
	
	public int findMajorStreak(){
		
		Comparator<String> streakComparator = new StreakComparator(streakStrikes);
		Map<String,Integer> streakStrikesSorted = new TreeMap<String, Integer>(streakComparator);
		streakStrikesSorted.putAll(streakStrikes);
		
		if (streakStrikesSorted.size() > 0 )
			return 	streakStrikesSorted.values().iterator().next();
		
		return 0;
	}
	
	public void increaseMinuteKillCount(){
		this.minuteKillCount++;
	}
	
}
