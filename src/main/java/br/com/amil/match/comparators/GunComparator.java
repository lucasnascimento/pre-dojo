package br.com.amil.match.comparators;

import java.util.Comparator;
import java.util.Map;

import br.com.amil.match.model.GunStats;

/**
 * Comparator to order Guns Maps in Kill Counter Descendant 
 * 
 * @author Lucas
 *
 */
public class GunComparator implements Comparator<String> {

	Map<String,GunStats> map;

	public GunComparator(Map<String,GunStats> map) {
		this.map = map;
	}

	@Override
	public int compare(String keyA, String keyB) {
		GunStats valueA =  map.get(keyA);
		GunStats valueB =  map.get(keyB);

		if (valueB.getKillCount() > valueA.getKillCount())
			return 1;
		else
			return -1;
	}
}
