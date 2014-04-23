package br.com.amil.match;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LineTypeEnumTest {
	
	final String NEW_MATCH_LINE 	= "23/04/2013 15:34:22 - New match 11348965 has started";
	final String KILL_LINE 			= "23/04/2013 15:36:04 - Roman killed Nick using M16";
	final String WORLD_KILL_LINE	= "23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN";
	final String END_MATCH_LINE 	= "23/04/2013 15:39:22 - Match 11348965 has ended";
	
	@Test
	public void newGameLineTest() {
		assertEquals(LineTypeEnum.NEW_MATCH, LineTypeEnum.NEW_MATCH.byLine(NEW_MATCH_LINE));
	}

	@Test
	public void killLineTest() {
		assertEquals(LineTypeEnum.KILL, LineTypeEnum.KILL.byLine(KILL_LINE));
	}

	@Test
	public void endMatchLineTest() {
		assertEquals(LineTypeEnum.END_MATCH, LineTypeEnum.END_MATCH.byLine(END_MATCH_LINE));
	}

	@Test (expected	= IllegalArgumentException.class)
	public void illegalLineTest() {
		LineTypeEnum.NEW_MATCH.byLine("");
	}
	
	@Test
	public void processNewGameTest(){
		String[] processed = LineTypeEnum.NEW_MATCH.extractValuesFromLine(NEW_MATCH_LINE);
		assertEquals(2,processed.length);
		assertEquals("23/04/2013 15:34:22", processed[0]);
		assertEquals("11348965", processed[1]);
	}

	@Test
	public void processKillTest(){
		String[] processed = LineTypeEnum.KILL.extractValuesFromLine(KILL_LINE);
		assertEquals(4,processed.length);
		assertEquals("23/04/2013 15:36:04", processed[0]);
		assertEquals("Roman", processed[1]);
		assertEquals("Nick", processed[2]);
		assertEquals("M16", processed[3]);
	}
	
	@Test
	public void processWorldKillTest(){
		String[] processed = LineTypeEnum.KILL.extractValuesFromLine(WORLD_KILL_LINE);
		assertEquals(4,processed.length);
		assertEquals("23/04/2013 15:36:33", processed[0]);
		assertEquals("WORLD", processed[1]);
		assertEquals("Nick", processed[2]);
		assertEquals("DROWN", processed[3]);
	}
	
	@Test
	public void processEndGameTest(){
		String[] processed = LineTypeEnum.END_MATCH.extractValuesFromLine(END_MATCH_LINE);
		assertEquals(2,processed.length);
		assertEquals("23/04/2013 15:39:22", processed[0]);
		assertEquals("11348965", processed[1]);
	}
	
	
}
