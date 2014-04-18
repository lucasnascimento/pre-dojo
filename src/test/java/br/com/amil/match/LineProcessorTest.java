package br.com.amil.match;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LineProcessorTest {

	@Test
	public void newGameLineTest() {
		assertEquals("NEW_MATCH","23/04/2013 15:34:22 - New match 11348965 has started");
	}

	@Test
	public void killLineTest() {
		assertEquals("KILL","23/04/2013 15:36:04 - Roman killed Nick using M16");
	}

	@Test
	public void andMatchLineTest() {
		assertEquals("END_MATCH","23/04/2013 15:39:22 - Match 11348965 has ended");
	}

}
