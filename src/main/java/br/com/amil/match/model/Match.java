package br.com.amil.match.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(of="matchId")
public class Match {

	private String matchId;
	private Set<Kill> kills = new HashSet<Kill>();
	
	private String startDate;
	private String endDate;

}
