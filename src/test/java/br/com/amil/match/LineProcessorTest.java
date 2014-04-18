package br.com.amil.match;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LineProcessorTest {

	@Test
	public void newGameLineTest() {
		assertEquals(LineTypeEnum.NEW_MATCH, LineTypeEnum.NEW_MATCH.byLine("23/04/2013 15:34:22 - New match 11348965 has started"));
	}

	@Test
	public void killLineTest() {
		assertEquals(LineTypeEnum.KILL, LineTypeEnum.KILL.byLine("23/04/2013 15:36:04 - Roman killed Nick using M16"));
	}

	@Test
	public void endMatchLineTest() {
		assertEquals(LineTypeEnum.END_MATCH, LineTypeEnum.END_MATCH.byLine("23/04/2013 15:39:22 - Match 11348965 has ended"));
	}

	@Test (expected	= IllegalArgumentException.class)
	public void illegalLineTest() {
		LineTypeEnum.NEW_MATCH.byLine("");
	}
	
}
