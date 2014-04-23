package br.com.amil.match.comparators;

import java.util.Comparator;
import java.util.Map;

/**
 * Comparator to order Streaks Maps in Streak Counter Descendant 
 * 
 * @author Lucas
 *
 */
public class StreakComparator implements Comparator<String> {

	Map<String,Integer> map;

	public StreakComparator(Map<String,Integer> map) {
		this.map = map;
	}

	@Override
	public int compare(String keyA, String keyB) {
		Integer valueA =  map.get(keyA);
		Integer valueB =  map.get(keyB);

		if (valueB > valueA)
			return 1;
		else
			return -1;
	}
}
