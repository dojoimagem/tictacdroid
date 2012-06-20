package dojo.game.tictacdroid.tests;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import dojo.game.logic.Game;

public class GameTests {

	private Game game;
	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@Test
	public void shouldMarkOnePosition() {
		game.setMarked(2);
		assertEquals(true, game.getMarked(2));
	}
	
	@Test
	public void shouldMarkOnlyOnePosition() {
		game.setMarked(3);
		assertEquals(true, game.getMarked(3));
		assertEquals(false, game.getMarked(2));
	}
	
	@Test
	public void verifyPlayTogglePlayer() {
		game.setMarked(0);
		game.setMarked(1);
		Assert.assertEquals("X", game.getPlayerMarked(0));
		Assert.assertEquals("O", game.getPlayerMarked(1));
	}
	
	@Test(expected=Exception.class)
	public void shouldNotAcceptTwoMarkersForTheSamePositions() {
		game.setMarked(0);
		game.setMarked(0);
	}
	
	@Test
	public void shouldVerifyIfTheGameHasEnded() {
		for (int i = 0; i< 9 ; i ++) {
			game.setMarked(i);
		}
		assertEquals(true, game.hasEnded());
	}
	
	@Test
	public void shouldVerifyIfTheGameHasNotEnded() {
		game.setMarked(0);
		assertEquals(false, game.hasEnded());
	}
	
	@Test
	public void shouldDetectOneFilledRow() {
		game.setMarked(0);
		game.setMarked(4);
		game.setMarked(1);
		game.setMarked(7);
		game.setMarked(2);
		game.setMarked(8);
		assertEquals(true, game.hasEnded());
	}
	
	@Test
	public void shouldDetectSecondFilledRow(){
		game.setMarked(0);
		game.setMarked(3);
		game.setMarked(8);
		game.setMarked(4);
		game.setMarked(7);
		game.setMarked(5);
		assertEquals(true, game.hasEnded());
	}	
}
