package br.com.amil.match;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatchTest {

	@Test
	public void startMatchTest() {
		assertEquals("NEW_MATCH", "");
	}

	@Test
	public void addKillTest() {
		assertEquals("KILL", "");
	}

	@Test
	public void endMatchTest() {
		assertEquals("END_MATCH", "");
	}
	
}
