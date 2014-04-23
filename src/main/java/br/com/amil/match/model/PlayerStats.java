package br.com.amil.match.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import br.com.amil.match.comparators.StreakComparator;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Domain class for Player Statistics.
 * 
 * @author Lucas
 *
 */
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
	
	/**
	 * Increases Kill Counter
	 */
	public void increaseKill(){
		this.killCount ++;
	}

	/**
	 * Increases Killed Counter
	 */
	public void increaseKilled(){
		this.killedCount ++;
	}
	
	/**
	 * Increases Streak Counter
	 */
	public void increaseStreakCount(){
		int _streakCount = Integer.parseInt(streakCount);
		_streakCount++;
		this.streakCount = ""+_streakCount;
	}
	
	/**
	 * Find major Streak for this player
	 */
	public int findMajorStreak(){
		
		Comparator<String> streakComparator = new StreakComparator(streakStrikes);
		Map<String,Integer> streakStrikesSorted = new TreeMap<String, Integer>(streakComparator);
		streakStrikesSorted.putAll(streakStrikes);
		
		if (streakStrikesSorted.size() > 0 )
			return 	streakStrikesSorted.values().iterator().next();
		
		return 0;
	}
	
	/**
	 * Increases Minute Kill Counter
	 */
	public void increaseMinuteKillCount(){
		this.minuteKillCount++;
	}
	
}
