package br.com.amil.match.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(of="name")
public class PlayerStats implements Comparable<PlayerStats> {
	
	private String name;
	private int killCount = 0;
	private int killedCount = 0;
	private Map<String, GunStats> gunsMap = new HashMap<String, GunStats>(); 
	
	public void increaseKill(){
		this.killCount ++;
	}

	public void increaseKilled(){
		this.killedCount ++;
	}
	
	@Override
	public int compareTo(PlayerStats other) {
		return this.killCount - other.killCount;
	}
	
}
