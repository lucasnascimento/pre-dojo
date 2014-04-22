package br.com.amil.match.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(of="name")
public class GunStats implements Comparable<GunStats> {

	private String name;
	private int killCount = 0;
	
	public void increaseKill(){
		killCount++;
	}
	
	@Override
	public int compareTo(GunStats other) {
		return killCount - other.killCount;
	}
	
}
