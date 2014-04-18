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
			currentMatch = new Match();
			currentMatch.setStartDate(newMatchValues[0]);
			currentMatch.setMatchId(newMatchValues[1]);
			break;
		case KILL:
			String[] killValues = LineTypeEnum.KILL.extractValuesFromLine(line);
			Kill kill = new Kill();
			kill.setKillDate(killValues[0]);
			kill.setKiller(killValues[1]);
			kill.setKilled(killValues[2]);
			kill.setGun(killValues[3]);
			currentMatch.getKills().add(kill);
			break;
		case END_MATCH:
			
			matches.add(currentMatch);
			break;
		default:

		}

	}

}
