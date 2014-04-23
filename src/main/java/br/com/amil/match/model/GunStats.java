package br.com.amil.match.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Domain class for Gun Statistics.
 * 
 * @author Lucas
 *
 */
@Data @EqualsAndHashCode(of="name")
public class GunStats{

	private String name;
	private int killCount = 0;
	
	public void increaseKill(){
		killCount++;
	}
	
}
