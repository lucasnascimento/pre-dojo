package br.com.amil.match.model;

import java.util.Comparator;
import java.util.Map;

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
