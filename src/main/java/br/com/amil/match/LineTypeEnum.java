package br.com.amil.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum LineTypeEnum {

	NEW_MATCH(".* has started", "(.*?) - New match (.*?) has started$")
	, KILL(	".* killed .*", "(.*?) - \\<*(.*?)\\>* killed (.*?) .*? (.*?)$")
	, END_MATCH(".* has ended", "(.*?) - Match (.*?) has ended$");

	@Getter
	@Setter
	private String regex;

	@Getter
	@Setter
	private String pattern;

	public LineTypeEnum byLine(String lineValue) {
		if (lineValue.matches(NEW_MATCH.regex)) {
			return NEW_MATCH;
		} else if (lineValue.matches(KILL.regex)) {
			return KILL;
		} else if (lineValue.matches(END_MATCH.regex)) {
			return END_MATCH;
		}
		throw new IllegalArgumentException();
	}

	public String[] extractValuesFromLine(String lineValue){
		Pattern pattern = Pattern.compile(this.pattern);
		Matcher matcher = pattern.matcher(lineValue);
		if(matcher.find()){
			String[] result = new String[matcher.groupCount()];
			for(int x=0;x<matcher.groupCount();x++) 
				result[x] = matcher.group(x+1);
			return  result;	
		}
		throw new IllegalArgumentException();
	}
}
