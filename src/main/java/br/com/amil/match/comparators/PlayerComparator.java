package br.com.amil.match.comparators;

import java.util.Comparator;
import java.util.Map;

import br.com.amil.match.model.PlayerStats;

/**
 * Comparator to order Players Maps in Kill Counter Descendant 
 * 
 * @author Lucas
 *
 */
public class PlayerComparator implements Comparator<String> {

	Map<String,PlayerStats> map;

	public PlayerComparator(Map<String,PlayerStats> map) {
		this.map = map;
	}

	@Override
	public int compare(String keyA, String keyB) {
		PlayerStats valueA =  map.get(keyA);
		PlayerStats valueB =  map.get(keyB);

		if (valueB.getKillCount() > valueA.getKillCount())
			return 1;
		else
			return -1;
	}
}
