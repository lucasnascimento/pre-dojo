package br.com.amil.match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum LineTypeEnum {
	
	NEW_MATCH(".* has started"), 
	KILL(".* killed .*"), 
	END_MATCH(".* has ended");
	
	@Getter @Setter
	private String regex;
	
	public LineTypeEnum byLine(String pattern){
		if (pattern.matches(NEW_MATCH.regex)) {
			return NEW_MATCH;
		}else if (pattern.matches(KILL.regex)){
			return KILL;
		}else if (pattern.matches(END_MATCH.regex)){
			return END_MATCH;
		}
		throw new IllegalArgumentException();
	}
	
}
