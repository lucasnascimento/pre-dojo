package br.com.amil.match;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

import br.com.amil.match.model.Match;
import br.com.amil.match.model.Matches;

public class MatchTest {

	@Test
	public void loadFromFileTest() {
		InputStream is = this.getClass().getResourceAsStream("/game.log");
		Matches matches = new Matches();
		matches.loadFromFile(is);
		assertEquals(1, matches.getMatches().size());
		Match match = matches.getMatches().iterator().next();
		assertEquals(2, match.getKills().size());
		assertEquals(2, match.getPlayersMap().size());
	}
	
	@Test
	public void load2FromFileTest() {
		InputStream is = this.getClass().getResourceAsStream("/game2.log");
		Matches matches = new Matches();
		matches.loadFromFile(is);
	}


	
}
