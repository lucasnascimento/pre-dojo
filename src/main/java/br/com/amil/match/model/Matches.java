package br.com.amil.match.model;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import lombok.Getter;
import br.com.amil.match.LineTypeEnum;

public class Matches {
	
	@Getter
	private Set<Match> matches = new HashSet<Match>();
	private Match currentMatch;

	public void loadFromFile(InputStream source) {
		Scanner scanner = new Scanner(source);
		while (scanner.hasNextLine()) {
			processLine(scanner.nextLine());
		}
		scanner.close();
	}

	private void processLine(String line) {

		switch (LineTypeEnum.NEW_MATCH.byLine(line)) {
		case NEW_MATCH:
			String[] newMatchValues = LineTypeEnum.NEW_MATCH.extractValuesFromLine(line);
			currentMatch = new Match(newMatchValues);
			break;
		case KILL:
			String[] killValues = LineTypeEnum.KILL.extractValuesFromLine(line);
			Kill kill = new Kill(killValues);
			currentMatch.getKills().add(kill);
			break;
		case END_MATCH:
			String[] endValues = LineTypeEnum.END_MATCH.extractValuesFromLine(line);
			currentMatch.setEndDate(endValues[0]);
			currentMatch.endMatch();
			matches.add(currentMatch);
			
			System.out.println(currentMatch.printMatchResume());
			
			break;
		}

	}

}
