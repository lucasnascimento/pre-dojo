package br.com.amil.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Convenience Enum to validate and Extract values from lines from GameLogFile.
 * 
 * @author Lucas
 *
 */
@AllArgsConstructor
public enum LineTypeEnum {

	NEW_MATCH(".* has started", "(.*?) - New match (.*?) has started$")			//Sample: 23/04/2013 15:34:22 - New match 11348965 has started
	, KILL(	".* killed .*", "(.*?) - \\<*(.*?)\\>* killed (.*?) .*? (.*?)$") 	//Sample: 23/04/2013 15:36:04 - Roman killed Nick using M16
	, END_MATCH(".* has ended", "(.*?) - Match (.*?) has ended$"); 				//Sample: 23/04/2013 15:39:22 - Match 11348965 has ended

	@Getter @Setter
	private String regex;

	@Getter @Setter
	private String pattern;

	/**
	 * Returns EnumType from some specific line
	 * 
	 * @param lineValue
	 * @return
	 */
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

	/**
	 * Returns values from some specific line
	 * 
	 * @param lineValue
	 * @return
	 */
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
